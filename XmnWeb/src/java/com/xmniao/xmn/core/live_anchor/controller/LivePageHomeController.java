/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveAnchorVideo;
import com.xmniao.xmn.core.live_anchor.entity.TLiveDelicious;
import com.xmniao.xmn.core.live_anchor.entity.TLiveEntertainment;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecommendRecord;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.LivePageHomeService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorVideoService;
import com.xmniao.xmn.core.live_anchor.service.TLiveDeliciousService;
import com.xmniao.xmn.core.live_anchor.service.TLiveEntertainmentService;
import com.xmniao.xmn.core.live_anchor.service.TLiveRecommendRecordService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivePageHomeController
 * 
 * 类描述： 直播首页推荐管理Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-16 上午11:43:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播首页推荐管理")
@Controller
@RequestMapping(value = "livePageHome/manage")
public class LivePageHomeController extends BaseController {
	
	@Autowired
	private LivePageHomeService livePageHomeService;
	
	@Autowired
	private TLiveAnchorVideoService anchorVideoService;
	
	@Autowired
	private TLiveRecommendRecordService liveRecommendRecordService;
	
	@Autowired
	private TLiveDeliciousService liveDeliciousService;
	
	@Autowired
	private TLiveEntertainmentService liveEntertainmentService;
	
	
	/**
	 * 跳转到直播首页推荐页面
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(TLiveRecord liveRecord,Model model) {
		String operationType = liveRecord.getOperationType();
		if(StringUtils.isBlank(operationType)){
			operationType="0";
		}
		model.addAttribute("operationType", operationType);
		return "live_anchor/livePageHomeManage";
	}
	
	/**
	 * 直播管理列表
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TLiveRecord liveRecord) {
		Pageable<TLiveRecord> pageable = new Pageable<TLiveRecord>(liveRecord);
		try {
			List<TLiveRecord> content = livePageHomeService.getList(liveRecord);
			Long count = livePageHomeService.count(liveRecord);
			pageable.setContent(content);
			pageable.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>initList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	/**
	 * 精彩视频
	 * 
	 * @author huang'tao
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = "init/anchorVideoList")
	@ResponseBody
	public Object anchorVideoList(TLiveAnchorVideo anchorVideo) {
		Pageable<TLiveAnchorVideo> pageable = new Pageable<TLiveAnchorVideo>(anchorVideo);
		try {
			anchorVideo.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_YES);
			List<TLiveAnchorVideo> content = anchorVideoService.getList(anchorVideo);
			Long count = anchorVideoService.count(anchorVideo);
			pageable.setContent(content);
			pageable.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>anchorVideoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加首页推荐(预告,直播,回放)页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-16下午8:06:44 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	public String addInit(TLiveRecord liveRecordReqest,Model model){
		Integer zhiboTypeParam = liveRecordReqest.getZhiboType();
		model.addAttribute("zhiboTypeParam", zhiboTypeParam);
		return "live_anchor/recommendEdit";
	}
	
	@RequestMapping(value="add")
	@ResponseBody
	public Resultable add(TLiveRecord liveRecord){
		Resultable result=new Resultable();
		try {
			liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_YES);
			livePageHomeService.updateRecommendedInfo(liveRecord);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作成功!");
		}
		return result;
	}
	
	
	/**
	 * 
	 * 方法描述：跳转到添加首页精彩视频推荐页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-17下午4:57:18 <br/>
	 * @return
	 */
	@RequestMapping(value="addAnchorVideo/init")
	public String addAnchorVideoInit(){
		return "live_anchor/addAnchorVideoInit";
	}
	
	/**
	 * 
	 * 方法描述：添加首页精彩视频推荐信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-17下午5:53:27 <br/>
	 * @param anchorVideo
	 * @return
	 */
	@RequestMapping(value="addAnchorVideo")
	@ResponseBody
	public Resultable addAnchorVideo(TLiveAnchorVideo anchorVideo){
		Resultable result=new Resultable();
		try {
			anchorVideo.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_YES);
			anchorVideoService.update(anchorVideo);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("添加首页精彩视频推荐信息失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改首页推荐排序值(预告,直播,回放)页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-16下午8:25:48 <br/>
	 * @param liveRecordRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TLiveRecord liveRecordRequest,Model model){
		TLiveRecord liveRecord = livePageHomeService.getObject(liveRecordRequest);
		Integer zhiboTypeParam = liveRecord.getZhiboType();
		model.addAttribute("liveRecord", liveRecord);
		model.addAttribute("zhiboTypeParam", zhiboTypeParam);
		return "live_anchor/recommendSortEdit";
	}
	
	/**
	 * 
	 * 方法描述：修改预告，直播，回放排序值 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-16下午8:28:48 <br/>
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Resultable update(TLiveRecord liveRecord){
		Resultable result=new Resultable();
		try {
			livePageHomeService.updateRecommendedInfo(liveRecord);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("修改预告，直播，回放排序值失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：跳转到修改首页精彩视频排序值页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-17上午9:41:05 <br/>
	 * @param anchorVideoRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value="updateAnchorVideo/init")
	public String updateAnchorVideoInit(TLiveAnchorVideo anchorVideoRequest,Model model){
		Integer id = anchorVideoRequest.getId();
		if(id==null){
			id=0;
		}
		TLiveAnchorVideo anchorVideo = anchorVideoService.getObject(id.longValue());
		model.addAttribute("anchorVideo", anchorVideo);
		return "live_anchor/anchorVideoSortEdit";
	}
	
	/**
	 * 
	 * 方法描述：更新精彩视频首页推荐排序信息<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-17上午10:18:55 <br/>
	 * @param anchorVideoRequest
	 * @return
	 */
	@RequestMapping(value="updateAnchorVideo")
	@ResponseBody
	public Resultable updateAnchorVideo(TLiveAnchorVideo anchorVideoRequest){
		Resultable result=new Resultable();
		try {
			anchorVideoService.update(anchorVideoRequest);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新精彩视频首页推荐排序信息失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：初始化通告下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value = "initRecordId",method=RequestMethod.POST)
	@ResponseBody
	public Object initRecordId(TLiveRecord liveRecord) {
		Pageable<TLiveRecord> pageable = new Pageable<TLiveRecord>(liveRecord);
		liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
		List<TLiveRecord> liveRecordList = livePageHomeService.getList(liveRecord);
		
		pageable.setContent(liveRecordList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：初始化主播精彩视频下拉框
	 * 创建人： huang'tao
	 * 创建时间：2016-8-10下午3:45:24
	 * @param anchorVideo
	 * @return
	 */
	@RequestMapping(value = "initAnchorVideoId",method=RequestMethod.POST)
	@ResponseBody
	public Object initAnchorVideoId(TLiveAnchorVideo anchorVideo) {
		Pageable<TLiveAnchorVideo> pageable = new Pageable<TLiveAnchorVideo>(anchorVideo);
		anchorVideo.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
		List<TLiveAnchorVideo> anchorVideoList = anchorVideoService.getList(anchorVideo);
		pageable.setContent(anchorVideoList);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：更新首页通告推荐 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-20下午6:24:07 <br/>
	 * @param liveRecord
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Resultable delete(TLiveRecord liveRecord){
		Resultable result=new Resultable();
		try {
			liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
			liveRecord.setUpdateTime(new Date());
			livePageHomeService.updateRecommendedInfo(liveRecord);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("删除首页推荐通告,操作失败:"+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：更新首页精彩视频推荐状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-20下午6:24:07 <br/>
	 * @param anchorVideo
	 * @return
	 */
	@RequestMapping(value="deleteAnchorVideo")
	@ResponseBody
	public Resultable deleteAnchorVideo(TLiveAnchorVideo anchorVideo){
		Resultable result=new Resultable();
		try {
			anchorVideo.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
			anchorVideo.setUpdateTime(new Date());
			anchorVideoService.update(anchorVideo);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("删除首页推荐精彩视频,操作失败:"+e.getMessage(), e);
		}
		return result;
	}

	
	/* ********************************* 好看推荐 ********************************************** */
	@RequestMapping(value = "init/recommendSpecialList")
	@ResponseBody
	public Object liveRecommendSpecialList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> pageable = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		try {
			pageable = liveRecommendRecordService.getLiveRecommendRecordInfoList(liveRecommendRecord);
			this.log.info("LivePageHomeController-->list pageable=" + pageable);
		} catch (Exception e) {
//			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>anchorVideoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	
	/**
	 * 方法描述：新增好看推荐 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:27 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value="addRecommendSpecial")
	@ResponseBody
	private Object addRecommendSpecial(TLiveRecommendRecord liveRecommendRecord){
		log.info("LivePageHomeController-->add-------liveRecommendRecord="+liveRecommendRecord);
		try {
			Integer id = liveRecommendRecord.getId();
			if(id==null){
				log.info("[执行新增好看推荐方法]id="+id);
				liveRecommendRecordService.saveAddActivity(liveRecommendRecord);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	
	/**
	 * 方法描述：删除好看推荐<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:11 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = "deleteSpecialRecommendById")
	@ResponseBody
	public Resultable deleteSpecialRecommendById(TLiveRecommendRecord liveRecommendRecord) {
		Resultable result = new Resultable();
		
		try {
			int count = liveRecommendRecordService.deleteById(liveRecommendRecord.getId());
			if (count > 0) {
				result.setMsg("删除成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 方法描述：修改好看推荐排序 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:33:51 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = { "updateRecommendSpecialSort"})
	@ResponseBody
	public Resultable updateRecommendSpecialSort(TLiveRecommendRecord liveRecommendRecord) {
		Resultable result = new Resultable();
		
		try {
			int count = liveRecommendRecordService.saveUpdateActivity(liveRecommendRecord);
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("更新数据失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	

	/* ********************************* 新人推荐 ********************************************** */
	@RequestMapping(value = "init/freshmanRecommendList")
	@ResponseBody
	public Object freshmanRecommendList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> pageable = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		try {
			pageable = liveRecommendRecordService.getFreshmanRecommendRecordInfoList(liveRecommendRecord);
			this.log.info("LivePageHomeController-->list pageable=" + pageable);
		} catch (Exception e) {
//			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>anchorVideoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	
	/**
	 * 方法描述：新增新人推荐 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:27 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value="addFreshmanRecommend")
	@ResponseBody
	private Object addFreshmanRecommend(TLiveRecommendRecord liveRecommendRecord){
		log.info("LivePageHomeController-->add-------liveRecommendRecord="+liveRecommendRecord);
		try {
			Integer id = liveRecommendRecord.getId();
			if(id==null){
				log.info("[执行新增好看推荐方法]id="+id);
				liveRecommendRecordService.saveAddActivity(liveRecommendRecord);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	
	/**
	 * 方法描述：删除新人推荐<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:11 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = "deleteFreshmanRecommendById")
	@ResponseBody
	public Resultable deleteFreshmanRecommendById(TLiveRecommendRecord liveRecommendRecord) {
		Resultable result = new Resultable();
		
		try {
			int count = liveRecommendRecordService.deleteById(liveRecommendRecord.getId());
			if (count > 0) {
				result.setMsg("删除成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 方法描述：修改新人推荐排序 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:33:51 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = { "updateFreshmanRecommendSort"})
	@ResponseBody
	public Resultable updateFreshmanRecommendSort(TLiveRecommendRecord liveRecommendRecord) {
		Resultable result = new Resultable();
		try {
			int count = liveRecommendRecordService.saveUpdateActivity(liveRecommendRecord);
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("更新数据失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	

	/* ********************************* 缤纷娱乐 ********************************************** */
	@RequestMapping(value = "init/entertainmentList")
	@ResponseBody
	public Object entertainmentList(TLiveEntertainment liveEntertainment) {
		Pageable<TLiveEntertainment> pageable = new Pageable<TLiveEntertainment>(liveEntertainment);
		try {
			pageable = liveEntertainmentService.getLiveEntertainmentInfoList(liveEntertainment);
			this.log.info("LivePageHomeController-->list pageable=" + pageable);
		} catch (Exception e) {
//			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>anchorVideoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	
	/**
	 * 方法描述：新增新人推荐 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:27 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value="addEntertainment")
	@ResponseBody
	private Object addEntertainment(TLiveEntertainment liveEntertainment){
		log.info("LivePageHomeController-->add-------addEntertainment="+liveEntertainment);
		try {
			Integer id = liveEntertainment.getId();
			if(id==null){
				log.info("[执行新增好看推荐方法]id="+id);
				liveEntertainmentService.saveAddActivity(liveEntertainment);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	
	/**
	 * 方法描述：删除新人推荐<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:11 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = "deleteEntertainmentById")
	@ResponseBody
	public Resultable deleteEntertainmentById(TLiveEntertainment liveEntertainment) {
		Resultable result = new Resultable();
		
		try {
			int count = liveEntertainmentService.deleteById(liveEntertainment.getId());
			if (count > 0) {
				result.setMsg("删除成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 方法描述：修改新人推荐排序 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:33:51 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = { "updateEntertainmentSort"})
	@ResponseBody
	public Resultable updateEntertainmentSort(TLiveEntertainment liveEntertainment) {
		Resultable result = new Resultable();
		
		try {
			int count = liveEntertainmentService.saveUpdateActivity(liveEntertainment);
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("更新数据失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	

	/* ********************************* 美味撩味********************************************** */
	@RequestMapping(value = "init/deliciousList")
	@ResponseBody
	public Object deliciousList(TLiveDelicious liveDelicious) {
		Pageable<TLiveDelicious> pageable = new Pageable<TLiveDelicious>(liveDelicious);
		try {
			pageable = liveDeliciousService.getLiveDeliciousInfoList(liveDelicious);
			this.log.info("LivePageHomeController-->list pageable=" + pageable);
		} catch (Exception e) {
//			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>getLiveDeliciousInfoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
	
	
	/**
	 * 方法描述：新增新人推荐 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:27 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value="addDelicious")
	@ResponseBody
	private Object addDelicious(TLiveDelicious liveDelicious){
		log.info("LivePageHomeController-->add-------addDelicious="+liveDelicious);
		try {
			Integer id = liveDelicious.getId();
			if(id==null){
				log.info("[执行新增好看推荐方法]id="+id);
				liveDeliciousService.saveAddActivity(liveDelicious);
			}
			
			return new Resultable(true, "操作成功");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return new Resultable(false, "操作失败");
	}
	
	
	
	/**
	 * 方法描述：删除新人推荐<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:34:11 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = "deleteDeliciousById")
	@ResponseBody
	public Resultable deleteDeliciousById(TLiveDelicious liveDelicious) {
		Resultable result = new Resultable();
		
		try {
			int count = liveDeliciousService.deleteById(liveDelicious.getId());
			if (count > 0) {
				result.setMsg("删除成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("删除失败!");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setMsg("删除失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * 方法描述：修改新人推荐排序 <br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年4月14日下午6:33:51 <br/>
	 * @param liveRecommendRecord
	 * @return
	 */
	@RequestMapping(value = { "updateDeliciousSort"})
	@ResponseBody
	public Resultable updateDeliciousSort(TLiveDelicious liveDelicious) {
		Resultable result = new Resultable();
		
		try {
			int count = liveDeliciousService.saveUpdateActivity(liveDelicious);
			if (count > 0) {
				result.setMsg("更新数据成功!");
				result.setSuccess(true);
			} else {
				result.setMsg("更新数据失败!");
				result.setSuccess(false);
			}
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			this.log.error(e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "initFreshmanRecordId",method=RequestMethod.POST)
	@ResponseBody
	public Object initFreshmanRecordId(TLiveRecord liveRecord) {
		Pageable<TLiveRecord> pageable = new Pageable<TLiveRecord>(liveRecord);
//		liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
		List<TLiveRecord> liveRecordList = livePageHomeService.getFreshmanRecordList(liveRecord);//直播记录信息
		
		//signType: 1 代表签约，0 代表未签约
		if (liveRecord.getSignType() != null && liveRecord.getSignType().equals(0) ){
			 BLiver liver = new BLiver();
			 liver.setSignType(liveRecord.getSignType());
			 liver.setLimit(-1);
			 List<BLiver> liverList = liveRecommendRecordService.getLiverInfoList(liver);//主播信息记录表
			 if (liverList != null && liveRecordList != null) {
				 List<TLiveRecord> liveRecordDataList = new ArrayList<TLiveRecord>();
				 for (BLiver bean: liverList) {  //未签约主播
					 for (TLiveRecord object: liveRecordList) {//直播id,   
						 if (bean.getId() != null && bean.getId().equals(object.getAnchorId()))
							 liveRecordDataList.add(object) ;
					 }
				 }
				 
				 liveRecordList = liveRecordDataList;
			 }
		}
		
		pageable.setContent(liveRecordList);
		return pageable;
	}
	
	@RequestMapping(value = "initFreshmanVideoId",method=RequestMethod.POST)
	@ResponseBody
	public Object initFreshmanAnchorVideoId(TLiveAnchorVideo anchorVideo) {
		Pageable<TLiveAnchorVideo> pageable = new Pageable<TLiveAnchorVideo>(anchorVideo);
		//TODO 2017-09-06
//		anchorVideo.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_NO);
		anchorVideo.setStatus("001");
		List<TLiveAnchorVideo> anchorVideoList = anchorVideoService.getList(anchorVideo);
		
		//查出已经推荐的信息
		TLiveRecommendRecord liveRecommendRecord = new TLiveRecommendRecord();
		liveRecommendRecord.setRtype(anchorVideo.getRtype()); //推荐类型 1-好看推荐, 2-直播推荐, 3-精选预告, 4-精彩视频推荐, 5-热门回放
		List<TLiveRecommendRecord> liveRecommendRecordList = liveRecommendRecordService.getLiveRecommendRecordList(liveRecommendRecord);
		
		//计算出推荐的数据
		if (liveRecommendRecordList != null && liveRecommendRecordList.size() > 0){
			 List<TLiveAnchorVideo> liveAnchorVideoList = new ArrayList<TLiveAnchorVideo>();
			 for (TLiveAnchorVideo liveAnchorVideo: anchorVideoList) {  //直播记录信息
			     for (TLiveRecommendRecord bean: liveRecommendRecordList) {  //推荐信息
					 if (liveAnchorVideo.getId() != null && liveAnchorVideo.getId().equals(bean.getRid()))
						 liveAnchorVideoList.add(liveAnchorVideo) ;
				 }
			 }
		     anchorVideoList.removeAll(liveAnchorVideoList);
		}
		
		pageable.setContent(anchorVideoList);
		return pageable;
	}
	
	
	@RequestMapping(value = "init/freshmanVideoRecommendList")
	@ResponseBody
	public Object freshmanVideoRecommendList(TLiveRecommendRecord liveRecommendRecord) {
		Pageable<TLiveRecommendRecord> pageable = new Pageable<TLiveRecommendRecord>(liveRecommendRecord);
		try {
			pageable = liveRecommendRecordService.getFreshmanVideoRecommendInfoList(liveRecommendRecord);
			this.log.info("LivePageHomeController-->list pageable=" + pageable);
		} catch (Exception e) {
//			e.printStackTrace();
			this.log.error("执行LivePageHomeController——>anchorVideoList方法出错:"+e.getMessage(), e);
		}
		return pageable;
	}
}
