/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.service.CkeditorUpdateService;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;
import com.xmniao.xmn.core.live_anchor.service.TFansCouponIssueRefService;
import com.xmniao.xmn.core.live_anchor.service.TLiveCouponService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveCouponController
 * 
 * 类描述： 直播粉丝券Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-26 下午5:42:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播粉丝券管理")
@Controller
@RequestMapping(value = "liveCoupon/manage")
public class LiveCouponController extends BaseController {
	
	/**
	 * 注入直播粉丝券服务
	 */
	@Autowired
	private TLiveCouponService liveCouponService;
	
	/**
	 * 注入直播粉丝券与抵用券配置关系服务
	 */
	@Autowired
	private TFansCouponIssueRefService fansCouponService;
	
	/**
	 * 注入ckeditService服务
	 */
	@Autowired
	private CkeditorUpdateService ckeditorUpdateService;
	
	/**
	 * 跳转到粉丝券列表页
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "live_anchor/couponManage";
	}
	
	/**
	 * 加载粉丝券数据
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TLiveCoupon liveCoupon) {
		Pageable<TLiveCoupon> pageable = new Pageable<TLiveCoupon>(liveCoupon);
		Long count=0l;
		List<TLiveCoupon> list = liveCouponService.getList(liveCoupon);
		count = liveCouponService.count(liveCoupon);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 跳转到添加粉丝券页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add/init")
	@RequestToken(createToken=true,tokenName="addToken")
	public String addInit() {
		return "live_anchor/couponEdit";
	}
	
	/**
	 * 添加粉丝券
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "add")
	@RequestToken(removeToken=true,tokenName="addToken")
	@ResponseBody
	public Resultable add(TLiveCoupon coupon) {
		Resultable result=new Resultable();
		try {
			result=liveCouponService.saveInfo(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败");
		}
		return result;
	}
	
	/**
	 * 跳转到添加粉丝券页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "update/init")
	public String updateInit(TLiveCoupon liveCoupon,Model model) {
		Integer cid = liveCoupon.getCid();
		if(cid!=null){
			TLiveCoupon coupon = liveCouponService.selectByPrimaryKey(cid);
			List<TFansCouponIssueRef> voucherList = fansCouponService.getVoucherList(liveCoupon);
			if(voucherList!=null && voucherList.size()>0){
				coupon.setVoucherList(voucherList);
			}
			model.addAttribute("coupon",coupon);
		}
		return "live_anchor/couponEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新粉丝券信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-29下午5:05:22 <br/>
	 * @param liveCoupon
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Object update(TLiveCoupon liveCoupon){
		Resultable result=new Resultable();
		try {
			result=liveCouponService.updateInfo(liveCoupon);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
			result.setSuccess(false);
			result.setMsg("更新失败!");
		}
		return result;
	}
	

	/**
	 * 
	 * 方法描述：富文本编辑器文件上传 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6下午9:33:21 <br/>
	 * @param filedata
	 * @param request
	 * @param response
	 */
	@RequestMapping(value ={"add/ckeditorUpload","update/ckeditorUpload"} , method = { RequestMethod.POST })
	public void uploadFile3(@RequestParam("upload") MultipartFile filedata,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("文件上传Controller3");
		ckeditorUpdateService.ckeditorUpdate(filedata, request, response);
	}
	
	/**
	 * 
	 * 方法描述：批量更新粉丝券上线状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateStatusBatch")
	@ResponseBody
	public Object updateStatusBatch(@RequestParam("cids") String cids,@RequestParam("status") String status){
		Resultable result=new Resultable();
		result=liveCouponService.updateStatusBatch(cids,status);
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：批量更新粉丝券推荐状态<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-23下午2:11:30 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="updateRecomBatch")
	@ResponseBody
	public Object updateRecomBatch(@RequestParam("cids") String cids,@RequestParam("isRecom") String isRecom){
		Resultable result=new Resultable();
		result=liveCouponService.updateRecomBatch(cids,isRecom);
		return result;
	}
}
