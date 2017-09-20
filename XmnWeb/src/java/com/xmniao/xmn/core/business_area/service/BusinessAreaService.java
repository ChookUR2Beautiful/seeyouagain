package com.xmniao.xmn.core.business_area.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_area.dao.BusinessAreaDao;
import com.xmniao.xmn.core.business_area.entity.BusinessArea;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;


/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessAreaService
 * 
 * 类描述： 合作商区域分布
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2014年11月12日17时37分19秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BusinessAreaService extends BaseService<BusinessArea> {

	@Autowired
	private  BusinessAreaDao businessAreaDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return businessAreaDao;
	}
	
	/**
	 * 统计所有合作商信息
	 */
	public List<BusinessArea> getList(BusinessArea businessArea){
		List<BusinessArea>  businessAreaList=businessAreaDao.getList(businessArea);
		

		for (BusinessArea area : businessAreaList) {
			//合作商数
			BusinessArea areaTjointCount = businessAreaDao.getAreaTjointCount(area);//cityNameid
			if(null != areaTjointCount && areaTjointCount.getJointCount()!=null ){
				area.setJointCount(areaTjointCount.getJointCount());//合作商数
			}
									
			//商家中 已签约数 审核中 未验证
			BusinessArea areaTsellerCount=businessAreaDao.getAreaTSellerCount(area);
			if(null != areaTsellerCount){
				if(areaTsellerCount.getSellerSignCount()!=null){
					area.setSellerSignCount(areaTsellerCount.getSellerSignCount());//已签约数
				}
				if(areaTsellerCount.getSellerAuditCount()!=null){
					area.setSellerAuditCount(areaTsellerCount.getSellerAuditCount());//审核中	
				}
				if(areaTsellerCount.getSellerNoValidationCount()!=null){
					area.setSellerNoValidationCount(areaTsellerCount.getSellerNoValidationCount());//未验证
				}
			}
			
			
		
			//合作商员工签到数
			BusinessArea areaTregisterRecordCount=businessAreaDao.getAreaTregisterRecordCount(area);
			if(null != areaTregisterRecordCount && areaTregisterRecordCount.getRegisterRecordCount()!=null){
				area.setRegisterRecordCount(areaTregisterRecordCount.getRegisterRecordCount());//签到数
			}
			
						
		}
				
		return businessAreaList;
	} 
	
	/**
	 * 条数
	 */
	public Long count(BusinessArea businessArea){
		Long count=businessAreaDao.count(businessArea);
		return count;		
	}
	
	/**
	 * 统计每个城市下的合作商
	 * @param businessArea
	 * @return
	 */
/*	public List<BusinessArea> getAreaTjointCount(BusinessArea businessArea){
		List<BusinessArea>  getAreaTjointCount=businessAreaDao.getAreaTjointCount(businessArea);
		return getAreaTjointCount;
	}*/
	
	
	
	/**
	 * 更加城市id查询详情
	 * @param id
	 * @return
	 */
	public List<BusinessArea> getBusinessAreaByid(Long id){
		List<BusinessArea> businessAreaList=businessAreaDao.getBusinessAreaByid(id);
		
		for (BusinessArea area : businessAreaList) {
			//合作商数
			BusinessArea areaTjointCount = businessAreaDao.getAreaTjointCount(area);//cityNameid
			area.setJointCount(areaTjointCount.getJointCount());//合作商数
						
			//商家中 已签约数 审核中 未验证
			BusinessArea areaTsellerCount=businessAreaDao.getAreaTSellerCount(area);
			area.setSellerSignCount(areaTsellerCount.getSellerSignCount());//已签约数
			area.setSellerAuditCount(areaTsellerCount.getSellerAuditCount());//审核中
			area.setSellerNoValidationCount(areaTsellerCount.getSellerNoValidationCount());//未验证
			
			//合作商员工签到数
			BusinessArea areaTregisterRecordCount=businessAreaDao.getAreaTregisterRecordCount(area);
			area.setRegisterRecordCount(areaTregisterRecordCount.getRegisterRecordCount());//签到数
						
		}
		return businessAreaList;
	}
	//区域信息列表
	public List<BusinessArea> areaInfo(BusinessArea area){
		
		return businessAreaDao.areaInfo(area);
	}
	//区域数据查询 用于分页
	public Long areaInfoCount(BusinessArea area){
		return  businessAreaDao.areaInfoCount(area);
	}
	//商圈信息查询
	public List<BusinessArea> businessInfo(BusinessArea area){
			return businessAreaDao.businessInfo(area);
	}
	//商圈数据查询用于分页
	public Long businessInfoCount(BusinessArea area){
		return  businessAreaDao.businessInfoCount(area);
	} 
	//合作商信息查询
	public List<BusinessArea> cooperatorInfo(BusinessArea area){
		return businessAreaDao.cooperatorInfo(area);
	}
	//合作商数据查询用于分页
	public Long cooperatorInfoCount(BusinessArea area){
		return  businessAreaDao.cooperatorInfoCount(area);
	}
	//已签约商家信息查询
	public List<BusinessArea> businessSignedInfo(BusinessArea area){
		return businessAreaDao.businessSignedInfo(area);
	}
	//已签约商家数据查询用于分页
	public Long businessSignedInfoCount(BusinessArea area){
		return  businessAreaDao.businessSignedInfoCount(area);
	} 
}
