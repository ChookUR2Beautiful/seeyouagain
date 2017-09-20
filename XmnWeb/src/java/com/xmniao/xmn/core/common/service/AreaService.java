package com.xmniao.xmn.core.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseEvent;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.dao.BusinessDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.entity.TBusiness;
import com.xmniao.xmn.core.common.util.commonConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.system_settings.entity.TAuthorityArea;
import com.xmniao.xmn.core.system_settings.entity.TRoleArea;
import com.xmniao.xmn.core.system_settings.service.RoleAreaService;
import com.xmniao.xmn.core.util.ResultUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AuthorityAreaHandler;
import com.xmniao.xmn.core.util.handler.AuthorityHandler;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AreaService
 * 
 * 类描述： 区域
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月12日17时37分19秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class AreaService extends BaseService<TArea> {
	
	/**
	 * 区域是否更新缓存key
	 */
	public final static String AREA_IS_UPDATE_KEY = "area_is_update_key_";
	
	private  String areas;
	//private Timer timer = null;
	
	@Autowired
	private RoleAreaService roleAreaService;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private BusinessDao businessDao;
	
	/**
	 * 注入redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private AuthorityHandler authorityHandler = AuthorityHandler.getAuthorityHanlde();
	private AuthorityAreaHandler authorityAreaHandler = AuthorityAreaHandler.getInstance();
	
	@Override
	protected BaseDao getBaseDao() {
		return areaDao;
	}
	

	
	
	public List<TArea> selectProvince(){
		
		List<TArea> list = areaDao.selectProvince();
		return list;
	}
	
	
	public List<TArea> selectCity(int areaId){
		List<TArea> citylist = areaDao.selectCity(areaId);
		return citylist;	
	}
	
	public List<TArea> selectArea(int areaId){
		List<TArea> arealist = areaDao.selectArea(areaId);
		return arealist;	
	}
	
	public TArea findAreaInfo(int areaId){
		TArea area = areaDao.findAreaInfo(areaId);
		return  area;
	}
	
	public List<TArea> findBrother(int areaId){
		List<TArea> brothers = areaDao.findBrother(areaId);
		return  brothers;
	}

	@PostConstruct
	public void init(){
		updateAreas(null);
	}

	/**
	 * 获取区域信息 
	 * 1 : 如果当前用户需要限制区域权限则 进行相应处理
	 * 2 : 如果不需要直接返回所有区域
	 * @param request
	 * @param area
	 * @return
	 */
	public String getAll(HttpServletRequest request,TArea area,String src) {
		try{
		//String userAgent = request.getHeader("userAgent");
		String referer = null;
		//获取来源页地址
		if(StringUtils.hasLength(src)){
			referer = src;
		}else{
			referer = request.getHeader("Referer");
		}
		if(StringUtils.hasLength(referer)){
			
			//获取域名
			String contextPath = request.getContextPath();
			//截取来源页 获得requestUrl
			String  requestUrl= new String (referer.substring(referer.indexOf(contextPath)+contextPath.length(), referer.length()));
			//判断是否有查询字段
			int indexOf = requestUrl.indexOf('?');
			/**
			 * 有查询字段 则从查询字段开始处往后查找 “.” 出现的下标 
			 * 没有查询字段则直接查找 “.” 最后一次出现的下标
			 */
			indexOf = indexOf != -1 ?requestUrl.lastIndexOf('.',indexOf) : requestUrl.lastIndexOf('.');
			//截取 获得请求映射
			requestUrl = requestUrl.substring(0,indexOf);
			/**
			 * 根据从 requestUrl 从访问权限中获取权限资源编号
			 */
			@SuppressWarnings("unchecked")
			List<String> accessScope = ((List<String>)request.getSession().getAttribute("accessScope"));
			Long uid=null;
			for (int i = 0; i < accessScope.size(); i++) {
				String v = accessScope.get(i);
				if(requestUrl.startsWith(v,1)){
					uid = authorityHandler.getUrlById(v) ;
					break;
				}
			}
			//url有权限
			if(null != uid){
				//获取角色编号
				Long rid  =  ResultUtil.getCurrentUser(request).getRoleId();
				//根据权限编号获取区域权限编号 
				Long auid = getAuthorityArea(uid);
				/**
				 * 1:如果auid为空说明referer是第二级页面  
				 * 2:如果auid不为空说明referer是第一级页面  
				 */
				if(null == auid){
					//根据二级页面权限编号获取其父权限编号
					uid = authorityHandler.getIdByAuthorityFid(uid);
					//根据权限编号获取区域权限编号
					auid = getAuthorityArea(uid);
					//如果auid继续为空 则当前请求不需要限制权限
					if(null != auid){
						return getLd(rid,auid);
					}
				}else{
					return getLd(rid,auid);
				}
				
			}else{
				return "无权限!";
			}
		}else{
			return "非法请求!";
		}
		}catch(Exception e){}
		return areas;
	}
	
	/**
	 * 根据权限编号获取子权限编号
	 * @param uid
	 * @return
	 */
	private Long getAuthorityArea(Long uid){
		List<TAuthorityArea> list = authorityAreaHandler.getFidBySubAuthorityArea(uid);
		//获取第一个区域权限编号id
		if(list!=null && !list.isEmpty()){
			return list.get(0).getAuthorityId();
		}
		return null;
	}
	
	
	
	public void updateAreas(TArea area){
		if(area==null){
			area = new TArea();
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(TArea.class, "areaId","title","pid");
		areas = JSON.toJSONString(areaDao.getLdAll(area), filter);
	};

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addBatch(List<TArea> list) {
		Integer count  = areaDao.addBatch(list);
		fireEvent(BaseEvent.AREA_EVENT, areaDao);
		return count;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void add(TArea t) {
		areaDao.add(t);
		updateAreaRedisVersion();
		fireEvent(BaseEvent.AREA_EVENT, areaDao);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer delete(Object[] objects) {
		Integer count  = areaDao.delete(objects);
		fireEvent(BaseEvent.AREA_EVENT, areaDao);
		return count;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer update(TArea t) {
		Integer count  = areaDao.update(t);
		if(count>0){
			updateAreaRedisVersion();
		}
		
		fireEvent(BaseEvent.AREA_EVENT, areaDao);
		return count;
	}
	
	

	/**
	 * 方法描述：更新区域数据版本号 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-13下午8:41:59 <br/>
	 */
	private void updateAreaRedisVersion() {
		String version = redisTemplate.opsForValue().get(AREA_IS_UPDATE_KEY);
		if(StringUtil.isBlank(version)){
			redisTemplate.opsForValue().set(AREA_IS_UPDATE_KEY, "version0");
		}else{
			String versionStr = version.replace("version", "");
			Long valueNum = Long.valueOf(versionStr);
			redisTemplate.opsForValue().set(AREA_IS_UPDATE_KEY, "version"+(++valueNum));
		}
	}




	/**
	 * 区域下商圈列表
	 * @param id
	 * @return
	 */
	public List<TArea> getAreaInBusinessList(TArea area){
		return areaDao.getAreaInBusinessList(area);
	}
	
	/**
	 * 区域下商圈列表数量
	 * @param id
	 * @return
	 */
	public Long getAreaInBusinessListCount(Long id){
		return areaDao.getAreaInBusinessListCount(id);
	}
	
	/**
	 * 根据id获取区域信息 包括所有父区域信息
	 * @param id
	 * @return
	 */
	public TArea getObjectById(Long id){
		return areaDao.getObjectById(id);
	}
	
	
	public List<TArea> getObjectByIds(String[] ids){
		return areaDao.getObjectByIds(ids);
	}
	
	/**
	 * 根据权限编号与角色编号查询区域权限信息
	 * @param rid
	 * @param auId
	 * @return
	 */
	private String getLd(Long rid,Long auId){
		TRoleArea roleArea = new TRoleArea();
		roleArea.setRoleId(rid);
		roleArea.setAuthorityId(auId);
		List<TRoleArea> roleAreas= roleAreaService.getList(roleArea);
		if(roleAreas!=null && !roleAreas.isEmpty()){
			roleArea = roleAreas.get(0);
			StringBuilder sb = new StringBuilder();
			sb.append(roleArea.getProvince());
			sb.append(",");
			sb.append(roleArea.getArea());
			sb.append(",");
			sb.append(roleArea.getCity());
			sb.append(",");
			String[] aid= StringUtils.paresDuplicateRemovalToArray(sb.toString(), ",");
			if(aid!=null && aid.length>0){
				List<TArea> areaList = getObjectByIds(aid);
				if(areaList!=null && !areaList.isEmpty()){
					SimplePropertyPreFilter filter = new SimplePropertyPreFilter(TArea.class, "areaId","title","pid");
					return JSON.toJSONString(areaList, filter);
				}
			}
		}
		return areas;
	}



	/**
	 * 开通或者关闭区域
	 * @param tArea
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateStatus(TArea tArea) {
		try{
			TBusiness business = getTBusiness(tArea);
			areaDao.update(tArea);  //更新城市状态
			areaDao.updateAreaStatus(tArea); //更新城市下的区域状态
			businessDao.updateIsOpen(business);  //更新区域下的商圈状态
		}catch(Exception e){
			throw new ApplicationException("更新区域状态异常", e, new Object[] { tArea}, new String[] { "区域编号", tArea.getAreaId().toString(), "状态更新", "状态更新" });
		}
	}

	/**
	 * 区域关闭后对应的商圈也要关闭
	 * @param tArea
	 * @return
	 */
	private TBusiness getTBusiness(TArea tArea) {
		TBusiness business = new TBusiness();
		Integer status = tArea.getStatus();
		if(commonConstants.IS_LOCK == status){
			business.setIs_open(0);  //未开通
		}else if(commonConstants.IS_OPEN  == status){
			business.setIs_open(1);  //已开通
		}
		business.setAreaId(tArea.getAreaId());
		return business;
	}



	/**
	 * 根据条件查询区域管理中的商圈信息
	 * @param area
	 * @return
	 */
	public Pageable<TArea> getBussinessInAreaListPageable(TArea area) {
		Pageable<TArea> pageable = new Pageable<TArea>(area);
		pageable.setContent(areaDao.getBussinessInAreaListContent(area));
		pageable.setTotal(areaDao.getBussinessInAreaListTotal(area));
		return pageable;
	}




	/**
	 * 方法描述：获取市下的区和没有区的市
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月20日下午3:49:49 <br/>
	 * @param cityIds
	 * @return
	 */
	public List<TArea> getTownsByCityIds(List<String> cityIds) {
		ArrayList<TArea> result = new ArrayList<>();
		List<TArea> notTown = areaDao.getCityWhereNotTown(cityIds);
		List<TArea> townsByCityIds = areaDao.getTownsByCityIds(cityIds);
		result.addAll(notTown);
		result.addAll(townsByCityIds);
		return result;
	}
}
