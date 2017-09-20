/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.ss.formula.functions.Roman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.IndianaRobotDao;
import com.xmniao.xmn.core.fresh.entity.IndianaRobot;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.service.BLiveAnchorImageService;
import com.xmniao.xmn.core.live_anchor.util.NickNameUtils;
import com.xmniao.xmn.core.util.SougouLexiconUtil;
import com.xmniao.xmn.core.util.WordLibrary;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：IndianaRobotService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年3月1日 下午3:51:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class IndianaRobotService extends BaseService<IndianaRobot>{

	@Autowired
	private IndianaRobotDao indianaRobotDao;
	
	@Autowired
	private BLiveAnchorImageService liveImageService;
	
	@Override
	protected BaseDao getBaseDao() {
		return indianaRobotDao;
	}

	/**
	 * 方法描述：添加机器人
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月2日上午9:52:57 <br/>
	 * @param indianaRobot
	 */
	public void addRobot(IndianaRobot indianaRobot) {
		ArrayList<IndianaRobot> list = new ArrayList<>();
		BLiveAnchorImage imageParam= new BLiveAnchorImage();
		imageParam.setImageType(LiveConstant.IMAGETYPE_ROBOT);//相册类型  1 主播  2 机器人
		imageParam.setLimit(LiveConstant.PAGE_LIMIT_NO);
		List<BLiveAnchorImage> imageList = liveImageService.getList(imageParam);
		Integer imageCount=imageList.size();
		Long uidSise=indianaRobotDao.countUrs();
		Random random=new Random();
		List<WordLibrary> wordLibrary;
		try {
			wordLibrary = SougouLexiconUtil.getWordLibrary();
		} catch (Exception e) {
			log.info("读取搜狗词库时出现异常",e);
			throw new RuntimeException();
		}
		for (int i=0;i<indianaRobot.getAddNum();i++) {
			IndianaRobot robot = new IndianaRobot();
			String name = wordLibrary.get(random.nextInt(wordLibrary.size())).getWord();
			robot.setUname(name);
			robot.setNname(name);
			robot.setAvatar(imageList.get(random.nextInt(imageCount)).getAnchorImage());
			robot.setSex(RandomUtils.nextInt(1, 3));
			Map<String,Object> urs= indianaRobotDao.selectNextLong(RandomUtils.nextLong(1, uidSise));
			robot.setUid((Integer)urs.get("uid"));
			String[] concernLen = indianaRobot.getConcernLen().split(";");
			int concernNum = RandomUtils.nextInt(new Integer(concernLen[0]), new Integer(concernLen[1])+1);
			robot.setConcernNum(concernNum);
			String[] fansLen = indianaRobot.getFansLen().split(";");
			int fansNum = RandomUtils.nextInt(new Integer(fansLen[0]), new Integer(fansLen[1])+1);
			robot.setFansNum(fansNum);
			String[] ranNoLen = indianaRobot.getRankNoLen().split(";");
			int rankNo = RandomUtils.nextInt(new Integer(ranNoLen[0]), new Integer(ranNoLen[1])+1);
			robot.setLevelsId(rankNo);
			String[] storeLen = indianaRobot.getStoreLen().split(";");
			int storeNum = RandomUtils.nextInt(new Integer(storeLen[0]), new Integer(storeLen[1])+1);
			robot.setStoreNum(storeNum);
			String[] conditionLen = indianaRobot.getConditionLen().split(";");
			int conditionNum = RandomUtils.nextInt(new Integer(conditionLen[0]), new Integer(conditionLen[1])+1);
			robot.setConditionNum(conditionNum);
			robot.setCreateTime(new Date());
			robot.setUpdateTime(new Date());
			list.add(robot);
		}
		indianaRobotDao.addBatch(list);
	}

	/**
	 * 方法描述：删除全部机器人
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月2日下午4:46:15 <br/>
	 */
	public void deleteAll() {
		indianaRobotDao.deleteAll();
	}

}
