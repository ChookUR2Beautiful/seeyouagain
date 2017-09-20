package com.xmniao.xmn.core.business_cooperation.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：JointDao
 * 
 * @类描述： 合作商
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日11时21分24秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface JointDao extends BaseDao<TJoint>{
	/**
	 * 更具合作商id删除管理员信息
	 * @param objects
	 * @return
	 */
	public int deleteStaffByJointId(Object[] objects);
	/**
	 * 根据市id查询省id
	 * @param city
	 * @return
	 */
	@DataSource("slave")
	public Long getProvinceId(Long city);
	
	/**
	 * 根据商户区域id查询对应的合作商信息
	 * @return
	 */
	@DataSource("slave")
	public List<TJoint> getJointInfoByArea(String area);
	
	/**
	 * 查联系手机号码
	 * @param phoneid
	 * @return
	 */
	@DataSource("slave")
	public Long getcheckPhoneid(String phoneid);
	
	/**
	 * 获取下拉列表
	 * @param joint
	 * @return
	 */
	@DataSource("slave")
	public List<TJoint> getSelect(TJoint joint);
	
	/**
	 * 获取合作商信息
	 * @Title: getJointInfo 
	 * @Description:
	 */
	@DataSource("slave")
	public TJoint getJointInfo(TJoint joint);
	
	/**
	 * 修改合作商SAAs信息
	 * @Title: modifyJonitSaasInfo 
	 * @Description:
	 */
	@DataSource("slave")
	public int modifyJonitSaasInfo(TJoint joint);
	
}
