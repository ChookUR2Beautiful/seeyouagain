package com.xmniao.xmn.core.business_cooperation.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.MongoBaseService;
import com.xmniao.xmn.core.business_cooperation.dao.JointDao;
import com.xmniao.xmn.core.business_cooperation.dao.StaffDao;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.entity.TJointLandmark;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.synthesizeService.SynthesizeService;
import com.xmniao.xmn.core.thrift.service.synthesizeService.Wallet;
import com.xmniao.xmn.core.util.NMD5;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：JointService
 * 
 * 类描述： 合作商
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时21分24秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class JointService extends BaseService<TJoint> {
	/**
	 * 添加日志记录方法
	 */
	protected final Logger log = Logger.getLogger(getClass());

	@Autowired
	private StaffService staffService;
	
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;

	@Autowired
	private StaffDao staffDao;

	@Autowired
	private JointDao jointDao;
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private JointLandmarkService jointLandmarkService;
	
	@Autowired
	private MongoBaseService mongoBaseService;
	
	@Autowired
	private AreaService areaService;

	@Override
	protected BaseDao<TJoint> getBaseDao() {
		return jointDao;
	}

	
	public Integer saveTJoint(TJoint joint) {
		jointDao.addReturnId(joint);
		fireLoginEvent(new String[]{"合作商编号",String.valueOf(joint.getJointid()),"合作商添加"});
		SynthesizeService.Client client = (SynthesizeService.Client)(synthesizeServiceClient.getClient());
		Map<String,String> paramMap=new HashMap<String,String>();
 		try {
 			this.putParamsMap(paramMap, joint);
			log.info("添加合作商钱包开始，uid：" + String.valueOf(joint.getJointid()) + ",userType:3,password:" + paramMap.get("password") + ",name:" + joint.getCorporate());
			Map<String,String> resultMap=client.addWalletMap(paramMap);
			log.info("添加合作商钱包结束，返回值：" + resultMap.get("state"));
			fireLoginEvent(new String[]{"合作商编号",String.valueOf(joint.getJointid()),"添加钱包","添加钱包"},1);
		} catch (Exception e) {
			log.error("添加合作商失败", e);
			throw new ApplicationException("添加合作商异常", e, new Object[]{joint});
		} finally{
			synthesizeServiceClient.returnCon();
		}
 		return joint.getJointid();
	}
   public void putParamsMap(Map<String,String> paramMap,TJoint joint){
	   //String phoneid = joint.getPhoneid();
	   //String password = phoneid.substring(phoneid.length() - 6, phoneid.length());
	   paramMap.put("uId", String.valueOf(joint.getJointid()));
	   paramMap.put("userType","3");
	   paramMap.put("password", "");//密码默认为空
	   paramMap.put("phoneNumber", String.valueOf(joint.getAccount()));
	   paramMap.put("sellerName", String.valueOf(joint.getCorporate())); 
   }
	
	/**
	 * 获取合作商信息
	 */
	public void getJoinInfo(String jointid, ModelAndView model) {
		TJoint joint = getObject(new Long(jointid));
		TStaff staff = staffDao.getStaffByJointid(new Long(jointid));
		this.log.info(staff);
		
		/* 根据商家id查询合作商家经纬度信息 */
		Integer intJointid = Integer.parseInt(jointid);
		TJointLandmark jointLandmark = jointLandmarkService
				.getJointLandmarkByid(intJointid.longValue());
		model.addObject("jointLandmarkList",jointLandmark);
		model.addObject("joint", joint);
		model.addObject("staff", staff);
	}

	/**
	 * 更新管理员信息
	 * 
	 * @param sellerApply
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int addAndUpdateStaff(TStaff staff) {
		int row = 0;
		if (null != staff) {
			if (staff.getStaffid() == null) {
				if(staff.getPassword() != null){
					staff.setPassword(NMD5.Encode(staff.getPassword()));
					staff.setSdate(new Date());
					try{
						staffService.add(staff);
					}catch(Exception e){
						throw new ApplicationException("添加合作商时添加管理员信息异常", e, new Object[]{staff});
					}
				}
				row++;
			} else {
				if(staff.getPassword() != null){
					//编辑初始化时候默认显示000000，如果前台穿过来的为此值则不不更新密码
					if (staff.getPassword().equals("000000")) {
						staff.setPassword(null);
					} else{
						staff.setPassword(NMD5.Encode(staff.getPassword()));//md5加密
					}
				}
				try{
					staffService.update(staff);
				}catch(Exception e){
					throw new ApplicationException("添加合作商时更新管理员信息异常", e, new Object[]{staff});
				}
				this.log.info("修改成功");
			}
		}
		return row;
	}

	/**
	 * 删除商家信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteJoinInfo(String jointid) {
		int row = 0;
		if (null != jointid) {
			row = delete(jointid.split(","));
			jointDao.deleteStaffByJointId(jointid.split(","));
		}
		return row;
	}
	/**
	 * 添加合作商
	 * @param joint
	 */
	/*@Transactional(propagation = Propagation.REQUIRED)
	public void addAndUpdateJoin(TJoint joint){
   		TStaff staff = new TStaff();
   		//1、如果null != joint.getJointid()则表示添加
		// 2、否则表示更新
		if(null != joint){
			
			Integer jointid = joint.getJointid();
			String area = joint.getArea();
			//设置 TStaff 对象的属性值
			setTStaffParams(staff, joint);
			
			staff.setJointid(jointid);
			
			if(null != area){
				joint.setArea(area.replaceAll(",", ";"));
			}
			if(null != jointid){ //修改
				try{
					update(joint);
				}catch(Exception e){
					throw new ApplicationException("修改合作商", e, new Object[]{joint}, new String[]{"合作商编号", jointid.toString(), "修改合作商", "修改"});
				}
			}else{  //添加
				Date date = new Date();
				joint.setSdate(date);
				joint.setEdate(date);
				try{
					add(joint);
				}catch(Exception e){
					throw new ApplicationException("添加合作商", e, new Object[]{joint}, new String[]{"法人姓名", joint.getLegalperson(), "添加合作商", "添加"});
				}
			    jointid = super.getMaxId().intValue();
				staff.setJointid(jointid);
			}
		  
			//添加或者更新管理员信息
			try{
				addAndUpdateStaff(staff);
			}catch(Exception e){
				log.info("添加合作商时管理员信息异常: " + e);
				throw new ApplicationException("添加合作商时管理员信息异常", e, new Object[]{staff}, new Object[]{"管理员编号", staff.getCategory().toString(), "添加合作商时管理员信息", "添加"});
			}
			//设置商家合作商
			if(null == joint.getJointid()){
				Integer staffidTemp = staff.getStaffid();
				setSellerJoint(area, jointid, staffidTemp != null ? staffidTemp : staffDao.getMaxId().intValue());
			}
		}
	}*/
	/**
	 * 添加合作商
	 * @param joint
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addAndUpdateJoin(TJoint joint){
		boolean flag = isAddOrUpdate(joint);
		try{
			Integer jointid = joint.getJointid();
			TStaff staff = setTStaffParams(joint);
			if(!flag){ //修改
				update(joint);
				updateStaff(staff);  //修改管理员信息
			}else{  //添加
				joint.setStocknum(joint.getSaasnum());//设置库存量
				String area = joint.getArea();
				setTJointParams(joint, area);
				jointid = saveTJoint(joint);
				staff.setJointid(jointid);
				int staffid = addStaff(staff);  //添加管理员信息
				setSellerJoint(area, jointid, staffid); //设置商家合作商
			}
			return jointid;
		}catch(Exception e){
			throw getExceptionObject(flag, joint, e);
		}
	}
	
	/**
	 * 添加员工信息
	 * @param staff
	 * @return
	 */
	private Integer addStaff(TStaff staff){
		try{
			if(staff.getPassword() != null){
				staff.setPassword(NMD5.Encode(staff.getPassword()));
				staff.setSdate(new Date());
			}
			staffDao.addReturnId(staff);
			this.log.info("员工信息添加成功");
		}catch(Exception e){
			this.log.info("员工信息添加失败: " + e);
			throw new ApplicationException("添加合作商时添加管理员信息异常", e, new Object[]{staff});
		}
		return staff.getStaffid();
	}
	
	/**
	 * 修改员工信息
	 * @param staff
	 */
	private void updateStaff(TStaff staff){
	
		try{
			//未修改密码
			if("000000".equals(staff.getPassword())){
				staff.setPassword(null);
			}
			staffService.update(staff);
			this.log.info("员工信息修改成功");
		}catch(Exception e){
			this.log.info("员工信息修改失败: " + e);
			throw new ApplicationException("修改合作商时更新管理员信息异常", e, new Object[]{staff});
		}
	}
	
	//为 TJoint 对象 赋值
	private void setTJointParams(TJoint joint, String area){
		Date date = new Date();
		joint.setSdate(date);
		joint.setEdate(date);
		if(null != area){
			joint.setArea(area.replaceAll(",", ";"));
		}
	}
	
	/**
	 * 判断是添加还是更新
	 * @param joint
	 * @return
	 */
	private boolean isAddOrUpdate(TJoint joint){
		boolean flag=true;
		if(null !=joint.getJointid()){
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 获取异常对象
	 * @param joint
	 * @param e
	 * @return
	 */
	private ApplicationException getExceptionObject(boolean flag, TJoint joint, Exception e){
		if(!flag){  //修改
			return new ApplicationException("修改合作商", e, new Object[]{joint}, new String[]{"合作商编号", joint.getJointid().toString(), "修改", "修改"});
		}else{ //添加
			return new ApplicationException("添加合作商", e, new Object[]{joint}, new String[]{"公司名称", joint.getCorporate(), "添加", "添加"});
		}
	}
	
	
	/**
	 * 为 TStaff 对象设置属性值
	 * @param staff
	 * @param joint
	 */
	private TStaff setTStaffParams(TJoint joint){
		TStaff staff = new TStaff();
		//默认为管理员
		staff.setCategory(2);
		//默认总分账金额
		staff.setAmount(0.0);
		//默认提成折扣 
		staff.setBaseagio(0.0);
		//默认|0=启用|1=停用
		staff.setStatus(0);
		
		staff.setPhoneid(joint.getAccount());
		staff.setPassword(joint.getPassword());
		staff.setFullname(joint.getFullname());
		staff.setNickname(joint.getNickname());
		//headUrl
		staff.setHeadurl(joint.getHeadurl());
		staff.setImei(joint.getImei());
		staff.setJointid(joint.getJointid());
		staff.setStaffid(joint.getStaffid());
		staff.setSex(joint.getSex());
		
		return staff;
	}
	
	
	
	/**
	 * 设置商家合作商
	 * @param areas
	 * @param jointid
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void setSellerJoint(String areas, Integer jointid, Integer staffid){
		if(areas != null && !areas.equals("")){
			List<TSeller> sellers = sellerDao.getSellerByAreas(areas.split(";"));
			for(TSeller seller : sellers){
				seller.setJointid(jointid);
				seller.setStaffid(staffid);
				sellerDao.update(seller);
			}
		}
	}

	public Long getcheckPhoneid(String phoneid){
		Long numphoneid= jointDao.getcheckPhoneid(phoneid);
		return numphoneid;
	}

	/**
	 * 获取下拉列表
	 * @param joint
	 * @return
	 */
	public List<TJoint> getSelect(TJoint joint) {
		return jointDao.getSelect(joint);
	}
	
	/**
	 * 获取钱包信息
	 * @param param
	 */
	public Map<String, String> getWallet(String uId, String userType){
		Map<String, String> param = new HashMap<String, String>(6);
		param.put("uId", uId);
		param.put("userType", userType);
		param.put("cPage", String.valueOf(SellerConstants.C_PAGE));
		param.put("pageSize", String.valueOf(SellerConstants.PAGE_SIZE));
		SynthesizeService.Client client = (SynthesizeService.Client)(synthesizeServiceClient.getClient());
		List<Map<String, String>> data = null;
		try {
			Wallet wallet = client.getWalletList(param);
			data = wallet.getWalletList();
		} catch (Exception e) {
			log.error("获取钱包信息失败！", e);
		} finally{
			synthesizeServiceClient.returnCon();
		}
		if(data.size() == 1){
			return data.get(0);
		}else{
			return null;
		}
		
	}
	
	/**
	 * @Description: 更新合作商经纬度.
	 * @Param:jointLandmark
	 * @return:int
	 * @author:lifeng
	 * @time:2016年7月5日上午10:04:43
	 */
	@Transactional
	public int updateJointLandmark(TJointLandmark jointLandmark) {
		jointLandmark.setSellerType(1);//1代表合作商
		int num = 0;
		TJointLandmark jointId = new TJointLandmark();
		jointId.setJointid(jointLandmark.getJointid());
		Long count = jointLandmarkService.count(jointId);
		if (count > 0 && jointLandmark.getLid() != null) {
			num = jointLandmarkService.update(jointLandmark);
			String[] jointLandmarkInfo = { "合作商编号",
					jointLandmark.getJointid().toString(), "更新合作商经纬度",
					"更新" };
			jointLandmarkService.fireLoginEvent(jointLandmarkInfo);
		} else {
			jointLandmarkService.add(jointLandmark);
			String[] jointLandmarkInfo = { "合作商编号",
					jointLandmark.getJointid().toString(), "添加合作商经纬度", "添加" };
			jointLandmarkService.fireLoginEvent(jointLandmarkInfo);
		}
		//TJoint s = getObject(jointLandmark.getJointid().longValue());
		//insertOrUpdateMongo(jointLandmark.getJointid());
		return num;
	}
	
	/**
	 * @Description: 查询合作商列表
	 * @Param:joint
	 * @return:list<TJoint>
	 * @author:lifeng
	 * @time:2016年7月25日下午17:40:43
	 */
	public List<TJoint> getList(TJoint joint){
		List<TJoint> list = jointDao.getList(joint);
		for (TJoint tJoint : list) {
			String area = tJoint.getArea();
			String[] cityIds = area.split(";");
			StringBuilder sb = new StringBuilder();
			if(cityIds.length > 0){
				List<TArea> areas = areaService.getObjectByIds(cityIds);
				if(areas != null){
					for (int i=0;i< areas.size(); i++) {
						if(i!=0 && areas.get(i)!=null ){
							sb.append(",");
						}
						sb.append(areas.get(i).getTitle());
					}
				}
			}
			tJoint.setAreaTitle(sb.toString());
		}
		return list;
	}
	
}
