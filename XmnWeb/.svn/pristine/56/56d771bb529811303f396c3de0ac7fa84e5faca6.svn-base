package com.xmniao.xmn.core.business_cooperation.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.entity.TJointLandmark;
import com.xmniao.xmn.core.business_cooperation.entity.TStaff;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
import com.xmniao.xmn.core.business_cooperation.service.StaffService;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.common.dao.AreaDao;
import com.xmniao.xmn.core.common.entity.TArea;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.HashUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.AreaHandler;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：JointController
 * 
 * 类描述： 合作商
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年11月19日11时22分14秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="合作商管理")
@Controller
@RequestMapping(value = "business_cooperation/joint")
public class JointController extends BaseController {

	@Autowired
	private JointService jointService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private AreaDao areaDao;// 区域areaDao

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "business_cooperation/jointList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TJoint joint) {
		Pageable<TJoint> pageable = new Pageable<TJoint>(joint);
		pageable.setContent(jointService.getList(joint));
		pageable.setTotal(jointService.count(joint));
		return pageable;
	}
	
	/**
	 * 导出列表
	 * @param joint
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TJoint joint, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		joint.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", jointService.getList(joint));
		doExport(request, response, "business_cooperation/joint.xls", params);
	}
	

	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="合作商删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("jointid") String jointid) {
		Resultable resultable = null;
		try {
			Integer resultNum = PartnerConstants.RESULTNUM_INIT;
			if(null != jointid){
				resultNum = jointService.deleteJoinInfo(jointid);
			}
			if (resultNum >PartnerConstants.RESULTNUM) {
				this.log.info("删除成功");
				resultable = new Resultable(true, "操作成功");
				String[] s={"合作商编号",jointid,"删除","删除"};
				jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//写入日志记录表
			}
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"合作商编号",jointid,"删除","删除"};
			jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);//写入日志记录表
		} finally {
			return resultable;
		}
	}

	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	@RequestToken(createToken=true,tokenName="jointToken")
	public ModelAndView addInit() {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/addJoint");
		Map<Integer , List<TArea>> areaList = AreaHandler.getAreaHandler().getArea();
		modelAndView.addObject("areaList", JSON.toJSONString(areaList));
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}

	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商添加")
	@RequestMapping(value = "/add")
	@RequestToken(removeToken=true,tokenName="jointToken")
	@ResponseBody
	public Object add(TJoint joint) {
		Resultable resultable = null;
		String word = joint.getCorporate();
		try {
			if(!checkArea(joint)){//合作商的区域不存在其他合作商
				
				Integer jointid = jointService.addAndUpdateJoin(joint);
				
				resultable = new Resultable(true, "操作成功");
				resultable.setData(jointid);		
				
				this.log.info("添加成功");
				recodeLog(word, PartnerConstants.FIRELOGIN_NUMA);
				
				//封装经纬度信息
				TJointLandmark jointLandmark = new TJointLandmark();
				jointLandmark.setJointid(joint.getJointid());
				jointLandmark.setLongitude(joint.getLongitude());
				jointLandmark.setLatitude(joint.getLatitude());
				// latitude 纬度 longitude 经度(计算HASH值)
				String geohashs = "";
				try {
					geohashs = HashUtil.getInstance().getGeoHash(
							jointLandmark.getLatitude(),
							jointLandmark.getLongitude());
				} catch (Exception e) {
					this.log.error("经纬度格式不正确", e);
					resultable = new Resultable(false, "经纬度格式不正确");
					// e.printStackTrace();
					return resultable;
				}
				jointLandmark.setGeohash(geohashs);
				jointLandmark.setSdate(new Date());
				jointService.updateJointLandmark(jointLandmark);
				//
			}else{
				throw new Exception("合作商的区域中有存在其他合作商的区域");
			}
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			jointService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("添加合作商异常", e, new Object[]{joint}).getMessage()), 0);
		} 
		return resultable;
	}
	
	/**
	 * 记录添加合作商日志
	 * @param world
	 */
	private void recodeLog(String word, int flag){
		String str = "";
		if (word.length() <= PartnerConstants.WORD_LENGTH){
			str = word;
		}else{
			str = word.substring(PartnerConstants.RESULTNUM_INIT, PartnerConstants.WORD_LENGTH)+"...";
		}
		String[] s={"合作商",str,"新增"};
		jointService.fireLoginEvent(s,flag);//写入 日志记录表
	}
	
	/**
	 * 验证合作商的区域是否已经存在其他合作商
	 * @param joint
	 * @return
	 * 
	 * @author zhang'zhiwen
	 */
	private boolean checkArea(TJoint joint){
		boolean result = false;
		for(String areaId :joint.getArea().split(",")){
			if(areaDao.checkArea(areaId)>0){//合作商存在
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	@RequestToken(createToken=true,tokenName="jointToken,jointLandmarkToken")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("jointid") String jointid) {
		ModelAndView modelAndView = new ModelAndView("business_cooperation/addJoint");
		modelAndView.addObject("isType", "update");
		try {
			jointService.getJoinInfo(jointid, modelAndView);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="合作商修改")
	@RequestMapping(value = "/update")
	@RequestToken(removeToken=true,tokenName="jointToken")
	@ResponseBody
	public Object update(TJoint joint) {
		Resultable resultable = null;
		Integer joinid = joint.getJointid();
		try {
			if(null != joint && null != joinid ){
				jointService.addAndUpdateJoin(joint);
			}
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
			recordUpdateLog(joinid, PartnerConstants.FIRELOGIN_NUMA);
			
			//封装经纬度信息
			TJointLandmark jointLandmark = new TJointLandmark();
			jointLandmark.setJointid(joint.getJointid());
			jointLandmark.setLid(joint.getLid());
			jointLandmark.setLongitude(joint.getLongitude());
			jointLandmark.setLatitude(joint.getLatitude());
			// latitude 纬度 longitude 经度(计算HASH值)
			String geohashs = "";
			try {
				geohashs = HashUtil.getInstance().getGeoHash(
						jointLandmark.getLatitude(),
						jointLandmark.getLongitude());
			} catch (Exception e) {
				this.log.error("经纬度格式不正确", e);
				resultable = new Resultable(false, "经纬度格式不正确");
				return resultable;
			}
			jointLandmark.setGeohash(geohashs);
			jointLandmark.setSdate(new Date());
			jointService.updateJointLandmark(jointLandmark);
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			jointService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("修改合作商异常", e, new Object[]{joint}).getMessage()), 0);
		}
		return resultable;
	}
	
	private void recordUpdateLog(Integer joinid, int flag){
		String[] s={"合作商编号",String.valueOf(joinid),"修改","修改"};
		jointService.fireLoginEvent(s, flag);
	}
	
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/add/Staff")
	@ResponseBody
	public Object addStaff(TStaff staff) {
		Resultable resultable = null;
		try {
			staff.setSdate(new Date());
			jointService.addAndUpdateStaff(staff);
			this.log.info("添加成功");
			Long staffid = staffService.getMaxId();
			resultable = new Resultable(true, "操作成功");
			resultable.setData(staffid);
			String[] s={"合作商编号",String.valueOf(staff.getJointid()),"修改","修改"};
			jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"合作商编号",String.valueOf(staff.getJointid()),"修改","修改"};
			jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);//添加到日志记录表
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 
	 * 更改会员信息
	 * 
	 * @author：zhou'sheng
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/Staff")
	@ResponseBody
	public Object updateStaff(TStaff staff) {
		Resultable resultable = null;
		try {
			jointService.addAndUpdateStaff(staff);
			resultable = new Resultable(true, "操作成功");
			if(staff.getStaffid() == null){
				Long staffid = staffService.getMaxId();
				resultable.setData(staffid);
			}else{
				resultable.setData(staff.getStaffid());
			}
			String[] s={"会员编号",String.valueOf(staff.getStaffid()),"修改","修改"};
			jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMA);//添加到日志记录表
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
			String[] s={"会员编号",String.valueOf(staff.getStaffid()),"修改","修改"};
			jointService.fireLoginEvent(s,PartnerConstants.FIRELOGIN_NUMB);//添加到日志记录表
		} finally {
			return resultable;
		}
	}
	
	
	/**
	 * 校验合作上联系手机号唯一性
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "init/checkPhoneid")
	public Long checkPhone(@RequestParam("phoneid") String phoneid){	
		Long  num = jointService.getcheckPhoneid(phoneid);
		return num;
	}
	
	/**
	 * 
	 * 查询合作商列表(搜索条件下拉框使用)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "jointList")
	@ResponseBody
	public Object jointList(TJoint joint) {
		Pageable<TJoint> pageable = new Pageable<TJoint>(joint);
		pageable.setContent(jointService.getSelect(joint));
		return pageable;
	}
	
	/**
	 * 获取钱包
	 * @param joint
	 * @return
	 */
	@RequestMapping(value = "getWallet")
	public ModelAndView getWalletList(@RequestParam("jointid") String jointid) {
		ModelAndView mv = new ModelAndView("business_cooperation/viewWallet");
		mv.addObject("wallet", jointService.getWallet(jointid, String.valueOf(PartnerConstants.WALLET_USERTYPE_USER)));
		return mv;
	}
	
	/**
	 * @Description: 添加合作商经纬度信息
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月28日下午5:21:19
	 *//*
	@ResponseBody
	@RequestMapping("/add/jointLandmark")
	public Object addSellerLandmark(TJointLandmark jointLandmark) {
		Resultable resultable = null;
		try {
			// latitude 纬度 longitude 经度(计算HASH值)
			String geohashs = "";
			try {
				geohashs = HashUtil.getInstance().getGeoHash(
						jointLandmark.getLatitude(),
						jointLandmark.getLongitude());
			} catch (Exception e) {
				this.log.error("经纬度格式不正确", e);
				resultable = new Resultable(false, "经纬度格式不正确");
				// e.printStackTrace();
				return resultable;
			}
			jointLandmark.setGeohash(geohashs);
			jointLandmark.setSdate(new Date());
			int res = jointService.updateJointLandmark(jointLandmark);
			this.log.info("添加成功!");
			resultable = new Resultable(true, "经纬度信息添加成功！");
			if (res != 0) {
				resultable.setData(res);
			}

		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}*/

	
	/**
	 * @Description: 更新合作商经纬度信息
	 * @Param:jointLandmark
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月27日下午3:56:06
	 */
	@RequestLogging(name="更新经纬度")
	@ResponseBody
	@RequestMapping("/update/jointLandmark")
	@RequestToken(removeToken=true,tokenName="jointLandmarkToken")
	public Object updateSellerLandmark(TJointLandmark jointLandmark) {
		Resultable resultable = null;
		try {
			// latitude 纬度 longitude 经度(计算HASH值)
			String geohashs = "";
			try {
				geohashs = HashUtil.getInstance().getGeoHash(
						jointLandmark.getLatitude(),
						jointLandmark.getLongitude());
			} catch (Exception e) {
				this.log.error("经纬度格式不正确", e);
				resultable = new Resultable(false, "经纬度格式不正确");
				return resultable;
			}
			jointLandmark.setGeohash(geohashs);
			jointLandmark.setSdate(new Date());
			jointLandmark.setSellerType(1);//1,经销商
			jointService.updateJointLandmark(jointLandmark);
			this.log.info("操作成功!");
			resultable = new Resultable(true, "操作成功!");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} 
		return resultable;
		
	}


}