package com.xmniao.xmn.core.verification.cotroller;


import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.verification.entity.SomeInfoBean;
import com.xmniao.xmn.core.verification.entity.Urs;
import com.xmniao.xmn.core.verification.entity.VerifyResponseBean;
import com.xmniao.xmn.core.verification.entity.Wallet;
import com.xmniao.xmn.core.verification.service.BillService;
import com.xmniao.xmn.core.verification.service.UrsService;
import com.xmniao.xmn.core.verification.service.WalletService;


@RequestMapping(value="data/")
@Controller
public class DataController {
	
	@Autowired 
	private UrsService ursService;
	@Autowired 
	private WalletService walletService;
	@Autowired 
	private BillService billService;
	
	/**
	 * 测试3数据查询效率
	 * @param uname
	 * @param uid
	 * @param bid
	 * @return
	 */
	@RequestMapping(value="someInfo")
	@ResponseBody	//直接返回内容，web中配置为json格式  不加注解则返回jsp页面
	public SomeInfoBean getSomeInfo(@RequestParam("uname" ) String uname,
			@RequestParam("uid" ) Integer uid,@RequestParam("bid" ) Long bid){
		Long start = 	System.currentTimeMillis();
		//用户表
		Urs u =	ursService.getUrsByUname(uname);
		//钱包
		Wallet w = 	walletService.getWalletByUid(uid);
		//订单
		VerifyResponseBean v = billService.getBillById(bid);
		SomeInfoBean sb = new SomeInfoBean();
		sb.setEmail(u.getEmail());
		sb.setNname(u.getNname());
		sb.setRegip(u.getRegip());
		sb.setUname(uname);
		sb.setAccount(w.getAccount());
		sb.setBalance(w.getBalance());
		sb.setAmount(w.getAmount());
		sb.setSellerAmount(w.getSellerAmount());
		sb.setVnname(v.getNname());
		sb.setSellername(v.getSellername());
		sb.setMoney(BigDecimal.valueOf(v.getMoney()));
		sb.setSdate(v.getSdate());
		Long end = 	System.currentTimeMillis();
		System.out.println("消耗用时毫秒："+(end-start));
		return sb;
//		return "person";
	}
	
	
	/**
	 * 测试session共享  
	 * @param req
	 * @return
	 */
	@RequestMapping(value="session")
	@ResponseBody
	public String getSessionId(HttpServletRequest req){
		return req.getSession().getId();
	}
	
}
