/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.TLiveRobotDao;
import com.xmniao.xmn.core.live_anchor.entity.AndroidAvatarForm;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRobot;
import com.xmniao.xmn.core.live_anchor.util.NickNameUtils;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveRobotService
 *
 * 类描述：直播机器人Service
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-24下午8:03:06
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TLiveRobotService extends BaseService<TLiveRobot> {
	
	protected static int OFFSET = 100;//单次最多导入数据行数
	
	@Autowired
	private TLiveRobotDao liveRobotDao;
	
	@Autowired
	private TLiveAnchorService liverService;
	
	/**
	 * 注入直播相册服务
	 */
	@Autowired
	private BLiveAnchorImageService liveImageService;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveRobotDao;
	}
	
	
	/**
	 * 
	 * 方法描述：通过ID删除机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午7:55:53
	 * @param id
	 * @return
	 */
    public int deleteById(Integer id){
    	return liveRobotDao.deleteById(id);
    }

	/**
	 * 
	 * 方法描述：通过ID查询机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午7:56:17
	 * @param id
	 * @return
	 */
    public TLiveRobot selectById(Integer id){
    	return liveRobotDao.selectById(id);
    }
	
	
    /**
	 * 方法描述：批量添加机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午8:15:13
	 * @param liveAnchor
	 * @return
	 */
	public Integer addBatch(List<TLiveRobot> list){
		return liveRobotDao.addBatch(list);
	}


	/**
	 * 方法描述：保存机器人信息
	 * 创建人： huang'tao
	 * 创建时间：2016-8-24下午8:15:13
	 * @param liveAnchor
	 * @return
	 */
	public int saveAndroidInfo(BLiver liveAnchor) {
		long startTime = System.currentTimeMillis();
		BLiver anchorParam=new BLiver();
		anchorParam.setUtype(1);//1 主播 2 普通用户
		Long anchorCount = liverService.count(anchorParam);
		
		BLiveAnchorImage imageParam= new BLiveAnchorImage();
		imageParam.setImageType(LiveConstant.IMAGETYPE_ROBOT);//相册类型  1 主播  2 机器人
		imageParam.setLimit(LiveConstant.PAGE_LIMIT_NO);
		List<BLiveAnchorImage> imageList = liveImageService.getList(imageParam);
		Integer imageCount=imageList.size();
		
		Integer androidNum = liveAnchor.getAndroidNum();
		Random random=new Random();
		List<TLiveRobot> robotList=new ArrayList<TLiveRobot>(androidNum);
		for(int i=0;i<androidNum;i++){
			TLiveRobot robot=new TLiveRobot();
			robot.setRobotName(NickNameUtils.getNickName());
			robot.setAvatar(imageList.get(random.nextInt(imageCount)).getAnchorImage());
			robot.setSex(random.nextInt(2)+1);
			robot.setConcernNums(random.nextInt(anchorCount.intValue())+1);
			robot.setGiveGiftsNums(random.nextInt(200)+1);
			robot.setRankNo(random.nextInt(10)+1);
			robot.setStatus(1);//是否启用  1 启用  0 未启用
			robot.setCreateTime(new Date());
			robot.setUpdateTime(new Date());
			robotList.add(robot);
		}
		long middleTime = System.currentTimeMillis();
		log.debug("设置机器人信息耗时：" + (middleTime-startTime));
		// 拆分批量插入
		int addBatchCount=androidNum/OFFSET;
		int remainder = androidNum % OFFSET;
		if(addBatchCount>0){
			for(int j=0;j<addBatchCount;j++){
				List<TLiveRobot> subList = robotList.subList(OFFSET*j, OFFSET*(j+1));
				addBatch(subList);
			}
		}
		
		if(remainder > 0){
			List<TLiveRobot> subList = robotList.subList(OFFSET*addBatchCount,OFFSET*addBatchCount + remainder );
			addBatch(subList);
		}
		
		
//		Integer count = addBatch(robotList);
		long endTime = System.currentTimeMillis();
		log.debug("生成"+androidNum+"个机器人，耗时："+(endTime-startTime));
		return androidNum;
	}

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： huang'tao
	 * 创建时间：2016-8-26下午2:03:28
	 * @param androidAvatarForm
	 * @return
	 */
	public Resultable addAvatar(AndroidAvatarForm androidAvatarForm) {
		Integer count=0;
		Resultable result=new Resultable();
		try {
			List<BLiveAnchorImage> robotAvatars = androidAvatarForm.getRobotAvatars();
			List<BLiveAnchorImage> imageList=new ArrayList<BLiveAnchorImage>();
			if(robotAvatars!=null && robotAvatars.size()>0){
				for(BLiveAnchorImage robotAvatar:robotAvatars){
					if(StringUtil.isBlank(robotAvatar.getAnchorImage())){
						continue;
					}
					robotAvatar.setStatus(1);//默认 1启用   0停用
					robotAvatar.setCreateTime(new Date());
					robotAvatar.setUpdateTime(new Date());
					imageList.add(robotAvatar);
				}
			}
			if(imageList!=null && imageList.size()>0){
				count = liveImageService.addBatch(imageList);
			}
			if(count>0){
				result.setSuccess(true);
				result.setMsg("成功添加"+count+"个头像!");
			}else{
				result.setSuccess(false);
				result.setMsg("请上传头像!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("添加机器人头像失败！"+e.getMessage(), e);
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}
		return result;
	}

	/**
	 * 方法描述：批量添加头像
	 * 创建人： huang'tao
	 * 创建时间：2016-8-26下午2:03:28
	 * @param androidAvatarForm
	 * @return
	 */
	public Resultable addBatchAvatar(AndroidAvatarForm androidAvatarForm) {
		Integer count=0;
		Resultable result=new Resultable();
		try {
			String relativePath = androidAvatarForm.getRelativePath();
			String[] images = relativePath.split(";");
			List<BLiveAnchorImage> imageList=new ArrayList<BLiveAnchorImage>();
			if(images!=null && images.length>0){
				for(String anchorImage:images){
					BLiveAnchorImage robotAvatar=new BLiveAnchorImage();
					if(StringUtil.isBlank(relativePath)){
						continue;
					}
					robotAvatar.setAnchorImage(anchorImage);
					robotAvatar.setImageType(LiveConstant.IMAGETYPE_ROBOT);
					robotAvatar.setStatus(1);//默认 1启用   0停用
					robotAvatar.setCreateTime(new Date());
					robotAvatar.setUpdateTime(new Date());
					imageList.add(robotAvatar);
				}
			}
			if(imageList!=null && imageList.size()>0){
				count = liveImageService.addBatch(imageList);
			}
			if(count>0){
				result.setSuccess(true);
				result.setMsg("成功添加"+count+"个头像!");
			}else{
				result.setSuccess(false);
				result.setMsg("请上传头像!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("添加机器人头像失败！"+e.getMessage(), e);
			result.setSuccess(false);
			result.setMsg("添加失败!");
		}
		return result;
	}

	/**
	 * 获取房间机器人数量
	 * 创建人： Administrator
	 * 创建时间：2016年9月1日下午5:54:33
	 * @param parseInt
	 * @return
	 */
	public Integer getLiveRobotSum(int parseInt) {
		return liveRobotDao.getLiveRobotSum(parseInt);
	}


	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人： Administrator
	 * 创建时间：2016年9月2日下午5:04:54
	 * @return
	 */
	public Map<String, String> getLiveRobotSum2() {
		List<Map<String, String>> list =liveRobotDao.getLiveRobotSum2();
		Map<String,String> map=new HashMap<String,String>();
		for (Map<String, String> map1 : list) {
			String key=map1.get("liveRecordId").toString();
			String value=map1.get("liveRobotSum").toString();
			map.put(key,value);
		}
		return map;
	}


	/**
	 * 方法描述：删除所有机器人信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-13下午2:02:20 <br/>
	 * @return
	 */
	public int deleteAll() {
		return liveRobotDao.deleteAll();
	}
	
	
	

}
