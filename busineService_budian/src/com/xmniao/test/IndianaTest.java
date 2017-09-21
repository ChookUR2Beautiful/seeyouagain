package com.xmniao.test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.DateUtil;
import com.xmniao.service.live.LiveOrderServiceImpl;
import com.xmniao.service.quartz.IndianaLotteryQuertzService;
import com.xmniao.service.quartz.LiveSalaryQuertzService;
import com.xmniao.service.xmer.SaasOrderServiceImpl;
import com.xmniao.thrift.busine.common.FailureException;

  @RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({ "file:conf/busine-base.xml" })

//@Transactional

public class IndianaTest {
	  
	  @Autowired
	 private IndianaLotteryQuertzService indianaLotteryQuertzService;
	  
	  @Autowired
	  private LiveSalaryQuertzService liveSalaryQuertzService;
	  
	  @Resource(name="LiveOrderServiceImpl")
	  private LiveOrderServiceImpl liveOrderServiceImpl;
	  
	  @Resource(name="saasOrderServiceImpl")
	  private SaasOrderServiceImpl saasOrderServiceImpl;

	/*@Test
	@Rollback(false)*/
	public void test() throws FailureException {
		//indianaLotteryQuertzService.joinRobot();
		//indianaLotteryQuertzService.decideWinner();
		//liveSalaryQuertzService.exected();
		Map<String,String> map=new HashMap<>();
		map.put("anchorId","1742");
		map.put("updateTime","201703");
		Map<String, String> liveSalary = liveOrderServiceImpl.uploadLiveSalary(map);
		System.out.println(liveSalary.get("msg"));
	}
	
	@Test
	public void modifySellerOrderInfo() throws com.xmniao.thrift.ledger.FailureException, TException{
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("bid", "021705111743489554");
		hashMap.put("status", "1");
		hashMap.put("rtype", "1");
		hashMap.put("payType", "1000013");
		hashMap.put("zdate", "2017-05-11 18:15:15");
		hashMap.put("payId", "1705111815058905");
		hashMap.put("payCode", "4004922001201705110437770761");
		Map<String, String> modifySellerOrderInfo = saasOrderServiceImpl.modifySellerOrderInfo(hashMap);
		System.out.println("============="+modifySellerOrderInfo+"==============");
	}
	
	 
	public static void main(String[] args) throws ParseException {
		Integer year = Integer.valueOf("201612".substring(0, 4));
		Integer month = Integer.valueOf("201612".substring(4, 6));
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, month - 1);
		String date1 = DateUtil.dateFormat(c.getTime(), DateUtil.Y_M_D);
		Date beginDate = DateUtil.convertStringToDate(DateUtil.Y_M_D,date1);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		String date2 = DateUtil.dateFormat(c.getTime(), DateUtil.Y_M_D);
		Date endDate = DateUtil.convertStringToDate(DateUtil.Y_M_D,date2);
		System.out.println(DateUtil.dateFormat(beginDate,DateUtil.YYMMDD));
		System.out.println(DateUtil.dateFormat(endDate,DateUtil.YYMMDD));
	}
	
	
	

}
