package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.common.entity.City;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.fresh.dao.PostageTemplateDao;
import com.xmniao.xmn.core.fresh.entity.PostageTemplate;
import com.xmniao.xmn.core.fresh.entity.TPostageFreeRule;
import com.xmniao.xmn.core.fresh.entity.TPostageRule;
import com.xmniao.xmn.core.util.DateHelper;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.JsonUtil;

/**
 * @ClassName:PostageTemplateService
 * @Description:运费模板Service
 * @author hls
 * @date:2016年6月20日下午4:17:40
 */
@Service
public class PostageTemplateService extends BaseService<PostageTemplate> {
	@Autowired
	private PostageTemplateDao postageTemplateDao;
	
	@Autowired
	private AreaService areaService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return postageTemplateDao;
	}

	/**
	 * @Title:getPostageTemplateList
	 * @Description:查询运费模板列表
	 * @param postageTemplate
	 * @return List<PostageTemplate>
	 * @throw
	 */
	public List<PostageTemplate> getPostageTemplateList(
			PostageTemplate postageTemplate) {
		List<PostageTemplate> postageTemplateList = new ArrayList<PostageTemplate>();
		postageTemplateList = postageTemplateDao
				.getPostageTemplateList(postageTemplate);
		/**
		 * 查询邮费模板(模板和包邮条件)
		 */
		if (null != postageTemplateList && 0 != postageTemplateList.size()) {
			StringBuilder sb = new StringBuilder();
			int tid = 0;
			for (int i = 0; i < postageTemplateList.size(); i++) {
				tid = postageTemplateList.get(i).getTid();
				sb = sb.append(tid + ",");
			}

			Object[] sbStr = sb.toString().split(",");
			/**
			 * 指定地区邮费规则
			 */
			List<PostageTemplate> postageRuleList = postageTemplateDao
					.getPostageRuleList(sbStr);

			for (int j = 0; j < postageRuleList.size(); j++) {
				for (int k = 0; k < postageTemplateList.size(); k++) {
					if (postageRuleList.get(j).getTid() == postageTemplateList
							.get(k).getTid()) {
						postageTemplateList.get(k).setPostageRuleList(
								postageRuleList.get(j).getPostageRuleList());
					}
				}
			}
		}
		
		for (PostageTemplate postageTemplate2 : postageTemplateList) {
			List<TPostageRule> postageRuleList = postageTemplateDao.getPostageRuleListByTid(postageTemplate2.getTid());
			List<TPostageFreeRule> postageFreeRuleList = postageTemplateDao.getPostageFreeRuleList(postageTemplate2.getTid());
			if(postageRuleList.size()>0){
				TPostageRule tpr = null;
				for (int i=0;i<postageRuleList.size();i++) {
					tpr = postageRuleList.get(0);
					tpr.setArea_r("全国");
				}
				postageRuleList.set(0, tpr);
				fillCitys(postageRuleList);
				fillFreeCitys(postageFreeRuleList);
				trimArea_r(postageRuleList);
				trimArea_f(postageFreeRuleList);
				postageTemplate2.setPostageRuleList(postageRuleList);
				postageTemplate2.setPostageFreeRuleList(postageFreeRuleList);
				//拼接包邮的标准
				String str = "";
				for (TPostageFreeRule tPostageFreeRule : postageFreeRuleList) {
					if(tPostageFreeRule != null && StringUtils.isNotBlank(tPostageFreeRule.getArea())){
						str += tPostageFreeRule.getArea_f() + "满" +tPostageFreeRule.getAmount()+"元以上,"+ tPostageFreeRule.getWeight() +"kg内包邮" + ";";
					}
				}
				postageTemplate2.setFreeRule(str.length()==0?null:str);
			}
			
		}
		return postageTemplateList;
	}

	/**
	 * @Title:getPostageTemplateCount
	 * @Description:查询运费模板列表总数
	 * @param postageTemplate
	 * @return Long
	 * @throw
	 */
	public Long getPostageTemplateCount(PostageTemplate postageTemplate) {
		return postageTemplateDao.getPostageTemplateCount(postageTemplate);
	};

	/**
	 * @Title:getPostageTemplate
	 * @Description:根据模板ID查询模板详情（包含指定包邮地区和包邮条件）用于修改
	 * @param id
	 * @return PostageTemplate
	 * @throw
	 */
	public PostageTemplate getPostageTemplate(Integer tid) {
		PostageTemplate postageTemplate = postageTemplateDao
				.getPostageTemplate(tid);
		//查询城市的区域,封装城市的名字
		List<TPostageRule> postageRuleList = postageTemplate.getPostageRuleList();
		fillCitys(postageRuleList);
		List<TPostageFreeRule> postageFreeRuleList = postageTemplateDao
				.getPostageFreeRuleList(tid);
		fillFreeCitys(postageFreeRuleList);
		if (postageFreeRuleList != null && !postageFreeRuleList.isEmpty() && postageFreeRuleList.get(0).getAmount()!=null) {
			postageTemplate.setPostageFreeRuleList(postageFreeRuleList);
		}
		//封装默认的运费的数据,用户显示
		if(postageRuleList != null && postageRuleList.size()>0){
			postageTemplate.setPostageRuleSize(postageRuleList.size());
			if(postageRuleList.get(0)!= null)postageTemplate.settPostageRule(postageRuleList.get(0));
		}
		return postageTemplate;
	};

	/**
	 * @Title:updatePostageTemplate
	 * @Description:修改运费模板
	 * @param postageTemplate
	 *            void
	 * @throw
	 */
	@Transactional
	public void updatePostageTemplate(PostageTemplate postageTemplate) {
		try {
			// 获取当前系统时间作为修改时间
			String ddate = DateHelper.getDateFormatter();
			postageTemplate.setUdate(DateUtil.smartFormat(ddate));
			/**
			 * 更新指定地区邮费
			 */
			// 1.获取原来数据
			// 2.对比前后指定地区邮费是否有区别
			// 3.有差别则删除原有数据，添加新数据
			int tid = postageTemplate.getPostageRuleList().get(0).getTid();
			List<TPostageRule> oldePostageRuleList = postageTemplateDao
					.getCityPostageRuleList(tid);
			List<TPostageRule> postageRuleList = postageTemplate
					.getPostageRuleList();
			Collections.sort(oldePostageRuleList);
			Collections.sort(postageRuleList);
			if (oldePostageRuleList.toString().equals(
					postageRuleList.toString())) {
				System.out.println("指定地区邮费没有修改过");
			} else {
				postageTemplateDao.delCityPostageRuleList(tid);
				/**
				 * add by lifeng 20160714
				 * 保存默认的运费的模板，保存城市编号为全国的城市
				 */
				if(postageRuleList.size() > 0){
					TPostageRule tpr = postageRuleList.get(0);
					tpr.setArea(getCityAreaIds());
					tpr.setIsdefault(1);
					postageRuleList.set(0, tpr);
				}
				// 批量添加指定地区邮费 并返回 插入id
				for (TPostageRule pr : postageRuleList) {
					pr.setTid(tid);
				}
				postageTemplateDao.addTPostageRule(postageRuleList);
			}
			/**
			 * 更新包邮条件
			 */
			List<TPostageFreeRule> oldPostageFreeRuleList = postageTemplateDao
					.getPostageFreeRuleList(tid);
			List<TPostageFreeRule> postageFreeRuleList = postageTemplate
					.getPostageFreeRuleList();
			Iterator<TPostageFreeRule> it = postageFreeRuleList.iterator();
			while (it.hasNext()) {
				TPostageFreeRule pf = it.next();
				if (pf.getArea() == null && pf.getAmount() == null) {
					System.out.println("删除的无效数据，删除");
					it.remove();
				}
			}
			Collections.sort(oldPostageFreeRuleList);
			Collections.sort(postageFreeRuleList);
			System.out.println("o" + oldPostageFreeRuleList.toString());
			System.out.println("n" + postageFreeRuleList.toString());
			if (oldPostageFreeRuleList.toString().equals(
					postageFreeRuleList.toString())) {
				System.out.println("包邮条件没有修改过");
			} else {
				postageTemplateDao.delPostageFreeRuleList(tid);
				// 批量添加包邮条件 并返回 插入id
				for (TPostageFreeRule pf : postageFreeRuleList) {
					pf.setTid(tid);
				}
				postageTemplateDao.addTPostageFreeRule(postageFreeRuleList);
			}
			/**
			 * 更新模板名称
			 */
			postageTemplate.setTid(tid);
			postageTemplateDao.updatePostageTemplate(postageTemplate);
			/**
			 * 每次更改操作都更新默认模板的udate为最新时间，使之排在最前面
			 */
			PostageTemplate def = new PostageTemplate();
			def.setUdate(DateUtil.smartFormat(ddate));
			postageTemplateDao.updatedefaultTemplateUdate(def);
		} catch (Exception e) {
			this.log.error("修改运费模板异常：", e);
			throw new ApplicationException("修改运费模板异常", e,
					new Object[] { postageTemplate });
		}
	};

	/**
	 * @Title:addPostageTemplate
	 * @Description:新增运费模板
	 * @param postageTemplate
	 *            void
	 * @throw
	 */
	@Transactional
	public void addPostageTemplate(PostageTemplate postageTemplate) {
		try {
			// 获取当前系统时间作为添加时间
			String ddate = DateHelper.getDateFormatter();
			postageTemplate.setUdate(DateUtil.smartFormat(ddate));
			/**
			 * add by lifeng 20160714
			 * 保存默认的运费的模板，保存城市编号为全国的城市
			 */
			List<TPostageRule> tprList = postageTemplate.getPostageRuleList();
			if(tprList.size() > 0){
				TPostageRule tpr = tprList.get(0);
				tpr.setArea(getCityAreaIds());
				tpr.setIsdefault(1);
				tprList.set(0, tpr);
			}
			postageTemplate.setPostageRuleList(tprList);
			// 添加运费模板 并返回 插入id
			postageTemplateDao.addPostageTemplateReturnId(postageTemplate);
			Integer tid = postageTemplate.getTid();
			// 批量添加指定地区邮费 并返回 插入id
			List<TPostageRule> postageRuleList = postageTemplate
					.getPostageRuleList();
			for (TPostageRule pr : postageRuleList) {
				pr.setTid(tid);
			}
			postageTemplateDao.addTPostageRule(postageRuleList);
			// 批量添加包邮条件 并返回 插入id
			List<TPostageFreeRule> postageFreeRuleList = postageTemplate
					.getPostageFreeRuleList();
			for (TPostageFreeRule pf : postageFreeRuleList) {
				pf.setTid(tid);
			}
			postageTemplateDao.addTPostageFreeRule(postageFreeRuleList);
			/**
			 * 每次更改操作都更新默认模板的udate为最新时间，使之排在最前面
			 */
			PostageTemplate def = new PostageTemplate();
			def.setUdate(DateUtil.smartFormat(ddate));
			postageTemplateDao.updatedefaultTemplateUdate(def);
		} catch (Exception e) {
			this.log.error("添加费模板异常：", e);
			throw new ApplicationException("添加运费模板异常", e,
					new Object[] { postageTemplate });
		}
	};

	/**
	 * @Title:deletePostageTemplate
	 * @Description:根据id删除运费模板
	 * @param postageTemplate
	 *            void
	 * @throw
	 */
	@Transactional
	public void deletePostageTemplate(PostageTemplate postageTemplate) {
		try {
			Integer tid = postageTemplate.getTid();
			if (!"".equals(tid)) {// id存在才删除
				postageTemplateDao.delPostageTemplate(tid);
				postageTemplateDao.delCityPostageRuleList(tid);
				postageTemplateDao.delPostageFreeRuleList(tid);
			}
		} catch (Exception e) {
			this.log.error("删除运费模板异常：", e);
			throw new ApplicationException("删除运费模板异常", e, postageTemplate);
		}
	}

	/**
	 * @Title:addCopyPostageTemplate
	 * @Description:复制运费模板
	 * @param postageTemplate
	 * @return Integer
	 * @throw
	 */
	public Object addCopyPostageTemplate(PostageTemplate postageTemplate) {
		try {
			Map<Object, Object> tidAndFlag = new HashMap<>();// 用于返回结果
			Integer tid = postageTemplate.getTid();// 得到被复制模板tid
			String copyTitle = postageTemplate.getCopyTitle();// 得到复制模板的名称
			// 查询运费模板名称和指定地区邮费
			PostageTemplate titleAndPostage = postageTemplateDao
					.getPostageTemplate(tid);
			// 查询指定地区包邮条件
			List<TPostageFreeRule> postageFreeRuleList = postageTemplateDao
					.getPostageFreeRuleList(tid);
			if (postageFreeRuleList != null && !postageFreeRuleList.isEmpty()) {
				titleAndPostage.setPostageFreeRuleList(postageFreeRuleList);
			}
			/**
			 * 新增拷贝数据
			 */
			// 获取当前系统时间作为添加时间
			String ddate = DateHelper.getDateFormatter();
			titleAndPostage.setUdate(DateUtil.smartFormat(ddate));
			// 命名复制的模板名称
			byte[] buff = copyTitle.getBytes();
			int f = buff.length;
			if (f > 30) {// 模板名称大于22个字节（12个汉字）则不能复制
				tidAndFlag.put("flag", false);
				return JsonUtil.toJSONString(tidAndFlag);
			}
			titleAndPostage.setTitle(copyTitle);
			// 添加运费模板 并返回 插入id
			postageTemplateDao.addPostageTemplateReturnId(titleAndPostage);
			Integer addCopTid = titleAndPostage.getTid();
			// 批量添加指定地区邮费 并返回 插入id
			List<TPostageRule> postageRuleList = titleAndPostage
					.getPostageRuleList();
			for (TPostageRule pr : postageRuleList) {
				pr.setTid(addCopTid);
				pr.setArea(pr.getArea_r());
			}
			postageTemplateDao.addTPostageRule(postageRuleList);
			// 批量添加包邮条件 并返回 插入id
			List<TPostageFreeRule> copyPostageFreeRuleList = titleAndPostage
					.getPostageFreeRuleList();
			for (TPostageFreeRule pf : copyPostageFreeRuleList) {
				pf.setTid(addCopTid);
			}
			postageTemplateDao.addTPostageFreeRule(copyPostageFreeRuleList);
			/**
			 * 每次更改操作都更新默认模板的udate为最新时间，使之排在最前面
			 */
			PostageTemplate def = new PostageTemplate();
			def.setUdate(DateUtil.smartFormat(ddate));
			postageTemplateDao.updatedefaultTemplateUdate(def);
			tidAndFlag.put("tid", addCopTid);
			tidAndFlag.put("flag", true);
			return JsonUtil.toJSONString(tidAndFlag);
		} catch (Exception e) {
			this.log.error("复制运费模板异常：", e);
			throw new ApplicationException("复制运费模板异常", e, postageTemplate);
		}
	}

	/**
	 * @Title:initArea
	 * @Description:区域初始化
	 * @return List<TArea>
	 * @throw
	 */
	public List<TArea> initArea() {
		// 省
		List<TArea> provinceList = new ArrayList<TArea>();
		provinceList = postageTemplateDao.getProvinceList();
		if (null != provinceList && 0 != provinceList.size()) {
			StringBuilder sb = new StringBuilder();
			int pid = 0;
			for (int p = 0; p < provinceList.size(); p++) {
				pid = provinceList.get(p).getAreaId();
				sb = sb.append(pid + ",");
			}
			Object[] sbStr = sb.toString().split(",");
			// 市
			List<City> cityList = new ArrayList<City>();
			cityList = postageTemplateDao.getCityList(sbStr);
			if (null != cityList && 0 != cityList.size()) {
				for (int i = 0; i <provinceList.size(); i++) {
					List<City> cList = new ArrayList<City>();
					for (int c = 0; c < cityList.size(); c++) {
						if(provinceList.get(i).getAreaId().toString().equals(cityList.get(c).getPareaId().toString())){
							cList.add(cityList.get(c));
						}
					}
					provinceList.get(i).setCityList(cList);
				}
			}
		}
		return provinceList;
	}
	
	/**
	 * @Description: 查询所有城市的编号
	 * @return:String
	 * @author:lifeng
	 * @time:2016年7月14日下午3:13:56
	 */
	public String getCityAreaIds(){
		List<String> cityAreaIds = postageTemplateDao.getCityAreaIds();
		return cityAreaIds.toString().replace("[", "").replace("]", "");
	}
	
	//根据城市的编号查询城市
	public void fillCitys(List<TPostageRule> postageRuleList){
		if(postageRuleList != null && postageRuleList.size()>0){
			for (TPostageRule tPostageRule : postageRuleList) {
				String area = tPostageRule.getArea();
				if(area != null && !area.equals("")){
					String[] arrAreaIds = area.replace(" ", "").split(",");
					StringBuilder sb = new StringBuilder();
					List<TArea> areas = areaService.getObjectByIds(arrAreaIds);
					for (int i=0;i< areas.size(); i++) {
						if(i!=0){
							sb.append(",");
						}
						sb.append(areas.get(i).getTitle());
					}
					tPostageRule.setArea_r(sb.toString());
				}
			}
		}
	}
	
	public void fillFreeCitys(List<TPostageFreeRule> postageFreeRuleList){
		if(postageFreeRuleList != null && postageFreeRuleList.size()>0){
			for (TPostageFreeRule tPostageFreeRule : postageFreeRuleList) {
				String area = tPostageFreeRule.getArea();
				if(area != null && !area.equals("")){
					String[] arrAreaIds = area.split(",");
					StringBuilder sb = new StringBuilder();
					List<TArea> areas = areaService.getObjectByIds(arrAreaIds);
					for (int i=0;i< areas.size(); i++) {
						if(i!=0){
							sb.append(",");
						}
						sb.append(areas.get(i).getTitle());
					}
					tPostageFreeRule.setArea_f(sb.toString());
				}
			}
		}
	}
	
	public List<TPostageFreeRule> trimArea_f(List<TPostageFreeRule> postageFreeRuleList){
		if(postageFreeRuleList != null && postageFreeRuleList.size()>0){
			for (TPostageFreeRule tPostageFreeRule : postageFreeRuleList) {
				String area_fs = tPostageFreeRule.getArea_f();
				if(StringUtils.isNotBlank(area_fs)){
					if(!area_fs.equals("全国")){
						String[] split = area_fs.split(",");
						String[] newSplit = new String[11];
						String newArea_fs = "";
						if(split.length > 10){
							for(int i = 0;i<10;i++){
								newSplit[i] = split[i];
							}
							newSplit[10] = "等" + split.length + "个城市";
							newArea_fs = Arrays.toString(newSplit).replace("[", "").replace("]", "");
						}else{
							newArea_fs = Arrays.toString(split).replace("[", "").replace("]", "");
						}
						tPostageFreeRule.setArea_f(newArea_fs);
					}
				}
			}
		}
		return postageFreeRuleList;
	}
	
	public List<TPostageRule> trimArea_r(List<TPostageRule> postageRuleList){
		if(postageRuleList != null && postageRuleList.size()>0){
			for (TPostageRule tPostageRule : postageRuleList) {
				String area_rs = tPostageRule.getArea_r();
				if(StringUtils.isNotBlank(area_rs)){
					if(!area_rs.equals("全国")){
						String[] split = area_rs.split(",");
						String[] newSplit = new String[11];
						String newArea_rs = "";
						if(split.length > 10){
							for(int i = 0;i<10;i++){
								newSplit[i] = split[i];
							}
							newSplit[10] = "等" + split.length + "个城市";
							newArea_rs = Arrays.toString(newSplit).replace("[", "").replace("]", "");
						}else{
							newArea_rs = Arrays.toString(split).replace("[", "").replace("]", "");
						}
						tPostageRule.setArea_r(newArea_rs);
					}
				}
			}
		}
		return postageRuleList;
	}

}
