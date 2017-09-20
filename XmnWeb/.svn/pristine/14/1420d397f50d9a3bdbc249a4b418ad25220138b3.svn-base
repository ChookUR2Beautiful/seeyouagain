package com.xmniao.xmn.core.business_area.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_area.entity.BusinessArea;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：AreaDao
 * 
 * @类描述：合作商区域分布
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月12日17时37分19秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface BusinessAreaDao extends BaseDao<BusinessArea>{
	
	@Override
	@DataSource("slave")
	public List<BusinessArea> getList(BusinessArea businessArea); 
	@Override
	@DataSource("slave")
	public Long count(BusinessArea businessArea);
	//统计每个城市下的合作商
	@DataSource("slave")
	public BusinessArea getAreaTjointCount(BusinessArea businessArea);
	//统计每个城市下商家数（根据商家状态）
	@DataSource("slave")
	public BusinessArea getAreaTSellerCount(BusinessArea businessArea);
	//统计合作商下的员工签到数
	@DataSource("slave")
	public BusinessArea getAreaTregisterRecordCount(BusinessArea businessArea);
	//根据城市id查询
	@DataSource("slave")
	public List<BusinessArea> getBusinessAreaByid(Long id);
	//区域显示
	@DataSource("slave")
	public List<BusinessArea> areaInfo(BusinessArea area);
	//统计区域显示的数据量
	@DataSource("slave")
	public Long areaInfoCount(BusinessArea businessArea);
	//商圈显示
	@DataSource("slave")
	public List<BusinessArea> businessInfo(BusinessArea area);
	//统计商圈显示的 数据量用于分页
	@DataSource("slave")
	public Long businessInfoCount (BusinessArea area);
	//合作商显示
	@DataSource("slave")
	public List<BusinessArea> cooperatorInfo(BusinessArea area);
	//合作商数据量统计 用于分页
	@DataSource("slave")
	public Long cooperatorInfoCount (BusinessArea area);
	//已签约商家显示
	@DataSource("slave")
	public List<BusinessArea> businessSignedInfo(BusinessArea area);
	//已签约商家数据量统计 用于分页
	@DataSource("slave")
	public Long businessSignedInfoCount (BusinessArea area);
}
