package com.xmniao.xmn.core.billmanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.dao.AllBillBargainDao;
import com.xmniao.xmn.core.billmanagerment.entity.BillBargain;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.billmanagerment.service.BillBargainService;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
import com.xmniao.xmn.core.common.service.AreaService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllBillController
 * 
 * 类描述： 查询所有爆品订单
 * 
 * 创建人： hls
 * 
 * 创建时间：2016年5月5日14时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value = "billmanagerment/allbillbargain")
public class BillBargainController extends BaseController{

	@Autowired
	private AllBillService allBillService;
	@Autowired
	private JointService jointService;
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private AllBillBargainDao billBargainDao;
	
	@Autowired
	private BillBargainService billBargainService;
	
	
	/**
	 * @Title:init
	 * @Description:init(初始化列表页面)
	 * @return String
	 * @throw
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/billbargainList";
	}

	/**
	 * @Title:list
	 * @Description:获取爆品订单列表
	 * @param bill
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(BillBargain billbargain) {
		this.log.info("BillBargainController-->list billbargain=" + billbargain);
		Pageable<BillBargain> pageable = new Pageable<BillBargain>(billbargain);
        pageable.setContent(billBargainService.getBillBargainList(billbargain));
        pageable.setTotal(billBargainService.billBargainCount(billbargain));
        return pageable;
	}
	
	/**
	 * @Title:view
	 * @Description:查看页面初始化
	 * @param request
	 * @param id
	 * @return ModelAndView
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "view/init")
	public ModelAndView view(HttpServletRequest request,String bid) {
		ModelAndView modelAndView = new ModelAndView("billmanagerment/bilbargainlDetail");
		try {
			BillBargain billbargain = billBargainService.getBillBargain(bid.trim());
			modelAndView.addObject("billbargain", billbargain);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}
	
	/**
	 * @Title:export
	 * @Description:导出爆品订单列表
	 * @param billbargain
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException void
	 * @throw
	 */
	@RequestMapping(value = "export")
	public void export(BillBargain billbargain, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", billBargainService.exportBillBargainList(billbargain));
		doExport(request, response, "billmanagerment/allbargainBill.xls", params);
	}
	/**
	 * @Title:billbarginRefund
	 * @Description:已分账订单退款
	 * @param billBargain
	 * @return Object
	 * @throw
	 */
  /*  @SuppressWarnings("finally")
	@RequestMapping(value = "/refund")
    @ResponseBody
    public Object billbarginRefund(BillBargain billBargain) {
    	Resultable resultable = null;
    	Map<String, String> resultMap = new HashMap<String, String>();
    	String msg = "";
    	String statecode = "";
        try {
        	resultMap = billBargainService.billbarginRefund(billBargain);
        	msg = resultMap.get("Msg");
        	statecode = resultMap.get("code");
        	resultable = new Resultable(true, msg,statecode);
            this.log.info("爆品订单退款成功");
        } catch (Exception e) {
        	resultable = new Resultable(false, "退款异常");
            this.log.error("爆品订单退款异常", e);
        } finally{
      		return resultable;
        }
      
    }*/
}