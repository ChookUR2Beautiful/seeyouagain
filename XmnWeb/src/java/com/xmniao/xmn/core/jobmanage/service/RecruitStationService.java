package com.xmniao.xmn.core.jobmanage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.jobmanage.dao.RecruitInfoDao;
import com.xmniao.xmn.core.jobmanage.dao.RecruitStationDao;
import com.xmniao.xmn.core.jobmanage.dao.RecruitTagDao;
import com.xmniao.xmn.core.jobmanage.dao.TagEntityDao;
import com.xmniao.xmn.core.jobmanage.entity.RecruitInfo;
import com.xmniao.xmn.core.jobmanage.entity.RecruitStation;
import com.xmniao.xmn.core.jobmanage.entity.RecruitTag;
import com.xmniao.xmn.core.jobmanage.entity.TagEntity;
import com.xmniao.xmn.core.util.PageConstant;

/**
 * 项目名称： XmnWeb
 * 类名称： StationService.java
 * 类描述：招聘岗位信息列表Service
 * 创建人： lifeng
 * 创建时间： 2016年5月30日下午3:27:49
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@Service
public class RecruitStationService extends BaseService<RecruitStation>{
	
	@Autowired
	private RecruitStationDao recruitStationDao;
	
	@Autowired
	private RecruitInfoDao recruitInfoDao;
	
	@Autowired
	private RecruitTagDao recruitTagDao;
	
	@Autowired
	private TagEntityDao tagEntityDao;
	
	@Autowired
	private AreaService areaService;

	@Override
	protected BaseDao<RecruitStation> getBaseDao() {
		return recruitStationDao;
	}

	/**
	 * @Description: 根据状态查询列表
	 * @Param:
	 * @return:List<RecruitStation>
	 * @author:lifeng
	 * @time:2016年6月2日上午11:03:08
	 */
	public List<RecruitStation> getRecruitStationList(RecruitStation recruitStation) {
		List<RecruitStation> recruitStationList = new ArrayList<>();
		
		/**
		 * 如果查询条件中包含店铺名称（商家名称），增加查询的条件recruitIds[]
		 */
		Integer[] recruitIds = null;// 招聘信息表的id集合（查询条件）
		RecruitInfo recruitInfoParam = new RecruitInfo();
		String sellerName = recruitStation.getSellerName();
		String contact = recruitStation.getContact();
		if(StringUtils.isNotBlank(sellerName) || StringUtils.isNotBlank(contact)){
			if(StringUtils.isNotBlank(sellerName)){
				recruitInfoParam.setSellerName(sellerName);
			}
			if(StringUtils.isNotBlank(contact)){
				recruitInfoParam.setContact(contact);
			}
			// 条件查询招聘的信息
			List<RecruitInfo> recruitInfoList = recruitInfoDao.getRecruitInfoList(recruitInfoParam);
			recruitIds = new Integer[recruitInfoList.size()];
			for (int i = 0;i<recruitInfoList.size(); i++) {
				recruitIds[i] = recruitInfoList.get(i).getId();//招聘信息表id
			}
		}
		if(recruitIds != null && recruitIds.length>0){
			recruitStation.setRecruitIds(recruitIds);
		}
		//
		
		List<RecruitStation> rsList = recruitStationDao.getRecruitStationList(recruitStation);
		for (RecruitStation rs : rsList) {
			/** 招聘信息 */
			if(rs.getRecruitId()!=null){
				RecruitInfo ri = recruitInfoDao.getRecruitInfoById(rs.getRecruitId());
				if(ri != null){
					rs.setSellerName(ri.getSellerName());//--店铺名称（商家名称）
					rs.setPhone(ri.getPhone());//--联系人
					rs.setContact(ri.getContact());//--联系电话
				}
			}
			/** 招聘标签（岗位要求）*/
			TagEntity te = new TagEntity();
			te.setPojoId(rs.getRecruitStationId());
			te.setEntityType(TagEntity.RECRUIT_STATION_REQUIRE);//查询类型为1的实体，查询的为招聘岗位 岗位要求
			if(te!=null){
				List<TagEntity> tagEntityList = tagEntityDao.getListByParam(te);
				StringBuilder stationRequireStr = new StringBuilder();
				if(tagEntityList != null){
					for (TagEntity tEntity : tagEntityList) {
						Integer tagId = tEntity.getTagId();
						if(tagId != null){
							RecruitTag rt = new RecruitTag();
							rt.setId(tEntity.getTagId());//标签id
							RecruitTag rTagBy = recruitTagDao.getRecruitTagByParam(rt);// 查询岗位要求
							if(rTagBy != null){
								stationRequireStr.append(rTagBy.getName() + ";");
							}
						}
					}
					rs.setStationRequire(stationRequireStr.toString());//设置是岗位要求的值到stationRequire中
				}
			}
			/** 封装省份及城市名称 */
			if(StringUtils.isNotBlank(rs.getProvince())){
				TArea provinceInfo = areaService.findAreaInfo(Integer.parseInt(rs.getProvince()));
				if(provinceInfo!=null){
					String province = provinceInfo.getTitle();
					if(StringUtils.isNotBlank(province)){
						rs.setProvinceName(province);
					}
				}
			}
			if(StringUtils.isNotBlank(rs.getCity())){
				TArea cityInfo = areaService.findAreaInfo(Integer.parseInt(rs.getCity()));
				if(cityInfo!=null){
					String city = cityInfo.getTitle();
					if(StringUtils.isNotBlank(city)){
						rs.setCityName(city);
					}
				}
			}
			//添加每一个RecruitStation对象到集合recruitStationList中
			recruitStationList.add(rs);
		}
 		return recruitStationList;
	}
	
	/**
	 * @Description: 条件查询总记录数
	 * @Param:
	 * @return:Long
	 * @author:lifeng
	 * @time:2016年6月12日下午2:26:24
	 */
	public Long getCountByParam(RecruitStation recruitStation){
		return recruitStationDao.getCountByParam(recruitStation);
	}
	
	/**
	 * @Description: 根据id查询
	 * @Param:id
	 * @return:RecruitStation
	 * @author:lifeng
	 * @time:2016年5月31日上午10:12:55
	 */
	public RecruitStation getRecruitStationById(Integer id){
		RecruitStation recruitStation = recruitStationDao.getRecruitStationById(id);
		if(recruitStation.getRecruitId()!=null){
			RecruitInfo ri = recruitInfoDao.getRecruitInfoById(recruitStation.getRecruitId());
			/** 招聘信息 */
			if(ri != null){
				recruitStation.setSellerName(ri.getSellerName());//--店铺名称（商家名称）
				recruitStation.setPhone(ri.getPhone());//--联系人
				recruitStation.setContact(ri.getContact());//--联系电话
			}
			/** 招聘标签（岗位要求）*/
			TagEntity te = new TagEntity();
			te.setPojoId(recruitStation.getRecruitStationId());
			te.setEntityType(TagEntity.RECRUIT_STATION_REQUIRE);//查询类型为1的实体，查询的为招聘岗位 岗位要求
			if(te!=null){
				List<TagEntity> tagEntityList = tagEntityDao.getListByParam(te);
				List<RecruitTag> stationRequireList = new ArrayList<>();//岗位要求（招聘标签list集合）
				if(tagEntityList != null){
					for (TagEntity tEntity : tagEntityList) {
						Integer tagId = tEntity.getTagId();
						if(tagId != null){
							RecruitTag rt = new RecruitTag();
							rt.setId(tEntity.getTagId());//标签id
							RecruitTag rTagBy = recruitTagDao.getRecruitTagByParam(rt);// 查询岗位要求
							if(rTagBy != null){
								stationRequireList.add(rTagBy);
							}
						}
					}
					recruitStation.setStationRequireList(stationRequireList);//设置是岗位要求的值到stationRequire中
				}
			}
		}
		return recruitStation;
	}

	/**
	 * @Description: 根据id修改招聘岗位信息
	 * @Param:recruitStation
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月1日下午3:19:48
	 */
	public Integer updateByRecruitStationId(RecruitStation recruitStation) {
		try {
			// 保存岗位信息
			recruitStation.setUpdateDate(new Date());
			Integer res = recruitStationDao.updateByRecruitStationId(recruitStation);
			
			//删除原有的岗位要求
			tagEntityDao.deleteTagEntityByPojoId(recruitStation.getRecruitStationId());
			
			/**
			 * 保存招聘岗位信息，保存数据到中间表tag_entity中
			 */
			List<TagEntity> tagEntities = new ArrayList<>();
			TagEntity tagEntity = null;
			List<String> tagIds = recruitStation.getTagIds();
			if(tagIds != null){
				for (int i=0;i<tagIds.size();i++) {
					if(tagIds.get(i)!=null){
						tagEntity = new TagEntity();
						tagEntity.setPojoId(recruitStation.getRecruitStationId());//设置实体id
						// 判断没有重复的值
						List<TagEntity> tagEntitys = tagEntityDao.getListByParam(tagEntity);
						boolean flag = true;
						if(tagEntitys != null){
							for (TagEntity tagEntity2 : tagEntitys) {
								Integer tagIdNew = Integer.parseInt(tagIds.get(i));
								if(tagEntity2.getTagId() == tagIdNew){
									flag = false;
									break;
								}
							}
						}
						//如果原来已不包含该标签类型，就添加
						if(flag){
							tagEntity.setTagId(Integer.parseInt(tagIds.get(i)));//设置标签id
						}
						tagEntity.setEntityType(TagEntity.RECRUIT_STATION_REQUIRE);//设置标签类型为招聘岗位要求
						tagEntity.setUpdateDate(new Date());//设置当前修改的时间为系统的时间
						tagEntities.add(tagEntity);
					}
				}
				if(tagEntity.getTagId()!= null && !tagEntity.getTagId().equals("")){
					tagEntityDao.saveTagEntities(tagEntities);
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("修改信息异常",e, new Object[]{recruitStation});
		}
	}

	/**
	 * @Description: 根据id删除招聘岗位（把这条记录的状态修改为1）
	 * @Param:id
	 * @return:Integer
	 * @author:lifeng
	 * @time:2016年6月1日下午3:22:44
	 */
	public Integer deleteById(String id) {
		int intId = Integer.parseInt(id);
		Integer res = recruitStationDao.deleteById(intId);
		return res == null ? 1 : 0;
	}

	/**
	 * @Description: 获取招聘岗位列表
	 * @return:岗位列表
	 * @author:lifeng
	 * @time:2016年6月4日下午3:58:06
	 */
	public List<RecruitTag> getRecruitTagList(RecruitTag recruitTag) {
		recruitTag.setOrder(PageConstant.NOT_ORDER);//不排序
		recruitTag.setLimit(SellerConstants.PAGE_LIMIT_NO);//不分页
		List<RecruitTag> list = recruitTagDao.getRecruitTagList(recruitTag);
		return list;
	}
}
