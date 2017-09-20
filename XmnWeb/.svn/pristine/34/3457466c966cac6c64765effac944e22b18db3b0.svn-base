package com.xmniao.xmn.core.business_cooperation.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_cooperation.dao.JointDao;
import com.xmniao.xmn.core.business_cooperation.dao.SalesmanManagementDao;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.NMD5;

/**
 *@ClassName:salesmanManagementService
 *@Description:合作商业务员管理service层
 *@author hls
 *@date:2016年3月14日下午8:55:29
 */
@Service
public class SalesmanManagementService extends BaseService<TJoint> {
	/**
	 * 添加日志记录方法
	 */
	protected final Logger log = Logger.getLogger(getClass());

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private SalesmanManagementDao salesmanManagementDao;
	
	@Autowired
	private JointDao jointDao;
	
	@Override
	protected BaseDao<TStaff> getBaseDao() {
		return salesmanManagementDao;
	}
	
	/**
	 * @Title:getSalesman
	 * @Description:根据合作商编号或者合作商名称查询业务员
	 * @param joint
	 * @return List<TStaff>
	 * @throw
	 */
	public List<TStaff> getSalesman(TJoint joint) {
		return salesmanManagementDao.getSalesman(joint);
	}
	/**
	 * @Title:getSalesmanCount
	 * @Description:根据合作商编号或者合作商名称查询业务员记录条数
	 * @param joint
	 * @return Long
	 * @throw
	 */
	public Long getSalesmanCount(TJoint joint) {
		return salesmanManagementDao.getSalesmanCount(joint);
	}
	
	/**
	 * @Title:addSalesman
	 * @Description:添加业务员信息
	 * @param staff
	 * @return Integer
	 * @throw
	 */
	public Integer addSalesman(TStaff staff){
		try{
			if(staff.getPassword() != null){
				staff.setPassword(NMD5.Encode(staff.getPassword()));
				staff.setSdate(new Date());
			}
			salesmanManagementDao.addReturnId(staff);
			this.log.info("业务员信息添加成功");
		}catch(Exception e){
			this.log.info("业务员信息添加失败: " + e);
			throw new ApplicationException("添加合作商业务员信息异常", e, new Object[]{staff});
		}
		return staff.getStaffid();
	}
	
	/**
	 * @Title:updateSalesman
	 * @Description:修改业务员信息
	 * @param staff void
	 * @throw
	 */
	public void updateSalesman(TStaff staff){
	
		try{
			salesmanManagementDao.update(staff);
			this.log.info("业务员信息修改成功");
		}catch(Exception e){
			this.log.info("业务员信息修改失败: " + e);
			throw new ApplicationException("修改合作商业务员信息异常", e, new Object[]{staff});
		}
	}
	/**
	 * @Title:getExceptionObject
	 * @Description:获取异常对象
	 * @param flag
	 * @param joint
	 * @param e
	 * @return ApplicationException
	 * @throw
	 */
	public ApplicationException getExceptionObject(boolean flag, TJoint joint, Exception e){
		if(!flag){  //修改
			return new ApplicationException("修改合作商", e, new Object[]{joint}, new String[]{"合作商编号", joint.getJointid().toString(), "修改", "修改"});
		}else{ //添加
			return new ApplicationException("添加合作商", e, new Object[]{joint}, new String[]{"公司名称", joint.getCorporate(), "添加", "添加"});
		}
	}
	/**
	 * @Title:getSalesInfo
	 * @Description:根据合作商业务员Id查询业务员信息用于初始化业务员修改页面
	 * @param jointid
	 * @param model void
	 * @throw
	 */
	public void getSalesInfo(String staffid, ModelAndView model) {
		TStaff staff = salesmanManagementDao.getSalesmanById(new Long(staffid));
		this.log.info(staff);
		model.addObject("staff", staff);
	}
	/**
	 * @Title:getSelect
	 * @Description:获取合作商编号和名称用于初始化下拉框
	 * @param joint
	 * @return List<TJoint>
	 * @throw
	 */
	public List<TJoint> getJointList() {
		return salesmanManagementDao.getJointList();
	}
	/**
	 * @Title:vailStaff
	 * @Description:根据业务员账号查询业务员表是否有记录
	 * @param staff
	 * @return Integer
	 * @throw
	 */
	public boolean vailStaff(TStaff staff){
		//获取添加的业务员账号
		String phoneid = staff.getPhoneid();
		int idcount = salesmanManagementDao.vailStaff(new Long(phoneid));
		if(idcount > 0){
			return false;
		}
		return true;
	}
}
