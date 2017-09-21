/**
 * 
 */
package com.xmniao.service.quartz;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.HttpUtil;
import com.xmniao.common.Signature;
import com.xmniao.dao.order.BillActivityDao;
import com.xmniao.dao.order.IndianaBoutDao;
import com.xmniao.dao.order.IndianaDduonumDao;
import com.xmniao.dao.order.IndianaRobotDao;
import com.xmniao.domain.order.BillActivity;
import com.xmniao.domain.order.IndianaBout;
import com.xmniao.domain.order.IndianaDduonum;
import com.xmniao.domain.order.IndianaRobot;
import com.xmniao.util.Constant;
import com.xmniao.util.PayIDGenerate;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaJob
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月27日 上午10:26:47
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

/*  @RunWith(SpringJUnit4ClassRunner.class)
   
  @ContextConfiguration({ "file:conf/busine-base.xml" })
  
  @Transactional
 */
public class IndianaLotteryQuertzService {

	/**
	 * 初始化日志类
	 */
	private final Logger log = Logger.getLogger(IndianaLotteryQuertzService.class);

	@Resource(name = "returnDepositServerUrl")
	private String returnDepositServerUrl;

	@Resource(name = "freshKey")
	private String freshKey;

	@Autowired
	private IndianaDduonumDao indianaDduonumDao;

	@Autowired
	private BillActivityDao billActivityDao;

	@Autowired
	private IndianaBoutDao indianaBoutDao;

	@Autowired
	private IndianaRobotDao indianaRobotDao;

	@Autowired
	private FreshActivityQuertzService activityQuertzService;

	/**
	 * 
	 * 方法描述：活动快结束时,用机器人参与 创建人：jianming 创建时间：2017年3月3日 上午11:49:43
	 */
	
	 /* @Test
	  
	  @Rollback(false)*/
	 
	public void joinRobot() {
		log.info("开始扫描将要结束的夺宝活动------------");
		try {
			List<IndianaBout> indianaBouts = indianaBoutDao.getNotFinishAtTime();
			if (!indianaBouts.isEmpty()) {
				List<IndianaRobot> indianaRobots = indianaRobotDao.getList();
				Calendar todayStart = Calendar.getInstance();
				todayStart.set(Calendar.HOUR, 9);
				todayStart.set(Calendar.MINUTE, 0);
				todayStart.set(Calendar.SECOND, 0);
				todayStart.set(Calendar.MILLISECOND, 0);
				Date date1 = todayStart.getTime();
				todayStart.set(Calendar.HOUR, 23);
				todayStart.set(Calendar.MINUTE, 0);
				todayStart.set(Calendar.SECOND, 0);
				todayStart.set(Calendar.MILLISECOND, 0);
				Date date2 = todayStart.getTime();
				Random random = new Random();
				for (IndianaBout indianaBout : indianaBouts) {
					// 机器抽取机器人凑够剩余份数
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ss HH:mm:ss:SSS");
					Integer residue = indianaBout.getPoint() - indianaBout.getSaleNum(); // 剩余份数
					// 找出本场最后一个编号
					IndianaDduonum lastNum = indianaDduonumDao.getLastNum(indianaBout.getId());
					Long num = lastNum == null ? indianaBout.getId() * 1000000 : lastNum.getId();
					if (residue > 10000) {
						residue = 10000;
					}
					List<IndianaDduonum> indianaDduonums = new LinkedList<>();
					while (residue > 0) {
						IndianaRobot indianaRobot = indianaRobots.get(random.nextInt(indianaRobots.size()));
						Integer point;
						if (residue > 5) {
							point = RandomUtils.nextInt(3, 6); // 3-5份
						} else {
							point = residue;
						}
						for (int i = 0; i < point; i++) {
							long nextLong = RandomUtils.nextLong(date1.getTime(), date2.getTime());
							IndianaDduonum indianaDduonum = new IndianaDduonum();
							indianaDduonum.setId(++num);
							indianaDduonum.setBoutId(indianaBout.getId());
							indianaDduonum.setCreateTime(dateFormat.format(new Date(nextLong)));
							indianaDduonum.setNname(indianaRobot.getNname());
							indianaDduonum.setUid(indianaRobot.getId());
							indianaDduonum.setPhone(indianaRobot.getPhone());
							indianaDduonum.setType(Constant.INDIANA_ROBOT_TYPE);
							indianaDduonums.add(indianaDduonum);
						}
						residue -= point;
					}
					int j = indianaDduonumDao.addBatch(indianaDduonums);
					indianaBoutDao.updateSaleNum(j, indianaBout.getId());
					if (j + indianaBout.getSaleNum() >= indianaBout.getPoint()) {
						indianaBoutDao.updateIndianaState(Constant.INDIANA_FINISH_STATUS, indianaBout.getActivityId());
					}
				}

			}
		} catch (Exception e) {
			log.info("执行一元夺宝机器人补全方法时发生异常", e);
			throw new RuntimeException(e);
		}
	}

	
/*	  @Test
	  
	  @Rollback(false)
	 */
	public void decideWinner() {
		log.info("开始扫描一元夺宝购买份数完毕的场次------------");
		// 扫描已经售完的期
		try {
			List<IndianaBout> sellOut = indianaBoutDao.getSellOut();
			for (IndianaBout indianaBout : sellOut) {
				// 查询是否有机器人购买
				List<IndianaDduonum> indianaDduonums = indianaDduonumDao.getByBoutIsNotReal(indianaBout.getId());
				IndianaDduonum winnerNum = indianaDduonumDao.getHasWinnerNum(indianaBout.getId()); // 查询预设中奖号码
				if (winnerNum != null) {
					setUserWinnerNum(indianaBout, winnerNum);
				} else {
					if (indianaDduonums == null || indianaDduonums.size() == 0) {
						// 用户真实购买
						Long count = indianaDduonumDao.countUid(indianaBout.getId());
						Random random = new Random();
						if (count == 0) {
							log.info("夺宝活动购买数量数据有误:  id=" + indianaBout.getId(), new RuntimeException());
							indianaBout.setStatus(3);
							indianaBoutDao.updateByPrimaryKey(indianaBout);
							continue;
						}
						try {
							List<IndianaDduonum> list = indianaDduonumDao.getLastFifty();
							Long sum = 0L;
							for (IndianaDduonum indianaDduonum : list) {
								String createTime = indianaDduonum.getCreateTime();
								String replace = createTime.replace(":", "");
								Integer integer = new Integer(replace.substring(11));
								sum += integer;
							}
							IndianaDduonum firstNum = indianaDduonumDao.getFirstNum(indianaBout.getId());
							Long winnerId = firstNum.getId() + (sum % indianaBout.getPoint()); // 计算出中奖号码
							IndianaDduonum winner = indianaDduonumDao.checkWinnerId(winnerId, indianaBout.getId());
							if (winner == null) {
								throw new RuntimeException("计算出中奖号码不存在");
							}
							setUserWinnerNum(indianaBout, winner);
						} catch (Exception e) {
							log.info("计算中奖号码时出现异常", e);
							int nextInt = random.nextInt(count.intValue()); // 中奖用户
							IndianaDduonum duoNum = indianaDduonumDao.getWinnerUid(indianaBout.getId(), nextInt);
							setUserWinnerNum(indianaBout, duoNum);
						}
					} else {
						// 机器人中奖吧
						Long count = indianaDduonumDao.countRobot(indianaBout.getId());
						Random random = new Random();
						int nextInt = random.nextInt(count.intValue()); // 中奖机器人
						IndianaDduonum duoNum = indianaDduonumDao.getWinnerRobot(indianaBout.getId(), nextInt);
						indianaBout.setUid(duoNum.getUid());
						indianaBout.setName(duoNum.getNname());
						indianaBout.setPhone(duoNum.getPhone());
						indianaBout.setGiveType(Constant.INDIANA_ROBOT_GIVE_TYPE);
						indianaBout.setLuckynum(duoNum.getId());
						indianaBout.setStatus(Constant.INDIANA_ROBOT_STATUS_TYPE);
						indianaBout.setUpdateTime(new Date());
						indianaBout.setEndTime(new Date());
						Long veces = indianaDduonumDao.countWinnerVeces(duoNum.getBoutId(), null, duoNum.getUid());
						indianaBout.setVeces(veces.intValue());
						indianaBoutDao.updateByPrimaryKey(indianaBout);
						// 退还库存
						activityQuertzService.updateActivityProductAndGroup(1 + indianaBout.getBoutResidue(),
								indianaBout.getCodeId(), indianaBout.getPvIds());
						indianaBoutDao.clearResidue(indianaBout.getActivityId());
					}
				}
				// 生成发货订单
				BillActivity billActivity = new BillActivity();
				billActivity.setId(PayIDGenerate.createPayId());
				billActivity.setActivityType(Constant.INDIANA_BILL_TYPE);
				billActivity.setActivityId(indianaBout.getId().longValue());
				billActivity.setCreateTime(new Date());
				billActivity.setProductCodeId(indianaBout.getCodeId().toString());
				billActivity.setProductNum(1);
				billActivity.setState(Constant.BILL_ACTIVITY_STATE);
				billActivity.setUpdateTime(new Date());
				billActivity.setReceivingName(indianaBout.getName());
				billActivity.setReceivingPhone(indianaBout.getPhone());
				billActivity.setUserId(indianaBout.getUid() != null ? indianaBout.getUid().toString()
						: indianaBout.getRobotId().toString());
				billActivity.setUserPhone(indianaBout.getPhone());
				billActivity.setUserName(indianaBout.getName());
				billActivity.setAmountReceived(indianaBout.getPointPrice().multiply(new BigDecimal(indianaBout.getVeces())));
				billActivity.setProductBreviary(indianaBout.getBreviary());
				billActivity.setProductPrice(indianaBout.getBasePrice());
				billActivity.setProductName(indianaBout.getPname());
				billActivity.setProductPvIds(indianaBout.getPvIds());
				billActivity.setProductPvValue(indianaBout.getPvValue());
				billActivity.setUserType(indianaBout.getGiveType());
				billActivity.setActivityName(indianaBout.getTitle());
				billActivityDao.insertSelective(billActivity);
				// 生成下一期
				/*
				 * if (indianaBout.getActivityEndTime().getTime()-new
				 * Date().getTime() > 600000 && indianaBout.getBoutResidue() >
				 * 0) { // 距离结束时间还有10分钟以上并且剩余场数大于0 IndianaBout newBout = new
				 * IndianaBout();
				 * newBout.setActivityId(indianaBout.getActivityId());
				 * newBout.setBeginTime(new Date());
				 * newBout.setBoutTh(indianaBout.getBoutTh() + 1);
				 * newBout.setCodeId(indianaBout.getCodeId());
				 * newBout.setCreateTime(new Date()); newBout.setUpdateTime(new
				 * Date()); indianaBoutDao.add(newBout);
				 * indianaBoutDao.updateLastBout(indianaBout); }
				 */
			}
		} catch (Exception e) {
			log.info("执行一元夺宝开奖时发生异常", e);
			throw new RuntimeException(e);
		}
	}

	private void setUserWinnerNum(IndianaBout indianaBout, IndianaDduonum winner) {
		indianaBout.setUid(winner.getUid());
		indianaBout.setName(winner.getNname());
		indianaBout.setPhone(winner.getPhone());
		indianaBout.setGiveType(Constant.INDIANA_USER_GIVE_TYPE);
		indianaBout.setLuckynum(winner.getId());
		indianaBout.setStatus(Constant.INDIANA_USER_STATUS_TYPE);
		indianaBout.setUpdateTime(new Date());
		indianaBout.setEndTime(new Date());
		Long veces = indianaDduonumDao.countWinnerVeces(winner.getBoutId(), null, winner.getUid());
		indianaBout.setVeces(veces.intValue());
		indianaBoutDao.updateByPrimaryKey(indianaBout);
	}

	/* @Test */
	public void doRefund() {
		log.info("开始扫描夺宝终止活动...");
		List<Map<String, Object>> list = indianaDduonumDao.getEndOrder();
		List<Map<String, Object>> orders = new LinkedList<>();
		try {
			if (!list.isEmpty()) {
				for (Map<String, Object> map : list) {
					Map<String, String> param = new HashMap<>();
					param.put("orderNumber", map.get("order_no").toString());
					BigDecimal pointPrice = (BigDecimal) map.get("point_price");
					Long count = (Long) map.get("count");
					param.put("amount", pointPrice.multiply(new BigDecimal(count)).toString());
					param.put("randomNumber", new Integer(RandomUtils.nextInt(100, 200)).toString());
					// 调用支付服务接口
					String sign = Signature.sign(param, freshKey);
					param.put("sign", sign);
					HttpResponse post = HttpUtil.getInstance().post(returnDepositServerUrl, param);
					HttpEntity entity = post.getEntity();
					String string = EntityUtils.toString(entity);
					Map<String, Object> parseObject = JSON.parseObject(string, HashMap.class);
					if ("200".equals(parseObject.get("state").toString())) {
						orders.add(map);
					} else {
						log.info("退还鸟币失败:调用退还鸟币接口状态为" + parseObject.get("state") + "   信息:" + parseObject.get("msg")
								+ "    订单号:" + param.get("orderNumber"));
					}
				}
				if (!orders.isEmpty()) {
					indianaDduonumDao.updateStatusRefund(orders);
				}
			}
		} catch (Exception e) {
			log.info("执行终止活动退款任务出现异常:map=" + orders, e);
			try {
				if(!orders.isEmpty()){
					indianaDduonumDao.updateStatusRefund(orders);
				}
			} catch (Exception e1) {
				log.info("执行更改夺宝活动状态时出现异常: map=" + orders, e1);
			}
		}

	}

}
