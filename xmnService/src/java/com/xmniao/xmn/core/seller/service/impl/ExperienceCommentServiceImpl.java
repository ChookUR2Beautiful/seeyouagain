package com.xmniao.xmn.core.seller.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.SessionTokenService;
import com.xmniao.xmn.core.common.MapResponse;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.service.FileService;
import com.xmniao.xmn.core.live.dao.AnchorLiveRecordDao;
import com.xmniao.xmn.core.live.dao.LiveUserDao;
import com.xmniao.xmn.core.seller.dao.ExperienceCommentDao;
import com.xmniao.xmn.core.seller.entity.CommentIdRequest;
import com.xmniao.xmn.core.seller.entity.CommentRequest;
import com.xmniao.xmn.core.seller.entity.CommentSellerRequest;
import com.xmniao.xmn.core.seller.entity.ExperienceComment;
import com.xmniao.xmn.core.seller.entity.ExperienceCommentMedia;
import com.xmniao.xmn.core.seller.entity.ModifyCommentRequest;
import com.xmniao.xmn.core.seller.entity.SellerCommentRequest;
import com.xmniao.xmn.core.seller.service.ExperienceCommentService;
import com.xmniao.xmn.core.util.CutVideoToImageUtil;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.EmojiFilter;
import com.xmniao.xmn.core.xmer.dao.SellerDao;
import com.xmniao.xmn.core.xmer.dao.SellerInfoDao;
import com.xmniao.xmn.core.xmer.dao.UnsignedSellerDao;

@Service
public class ExperienceCommentServiceImpl implements ExperienceCommentService {

	@Resource
	private SessionTokenService sessionTokenService;

	@Resource
	private SellerDao sellerDao;

	@Resource
	private UnsignedSellerDao unsignedSellerDao;

	@Resource
	private LiveUserDao liveUserDao;

	@Resource
	private AnchorLiveRecordDao anchorLiveRecordDao;

	@Resource
	private ExperienceCommentDao experienceCommentDao;
	
	@Resource
	private SellerInfoDao sellerInfoDao;

	@Resource
	private String fileUrl;
	
	@Resource
	private FileService fileService;
	
	@Autowired
	private CutVideoToImageUtil cutVideoToImageUtil;

	@Override
	public Object addComment(CommentRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";

		if (StringUtils.isEmpty(uid) || uid.equals("null")) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		//判断是否有emoj表情包返回给用户
		String content = request.getContent();
		if (EmojiFilter.containsEmoji(content) || EmojiFilter.containsEmojiByMatcher(content)) {	
				return new BaseResponse(ResponseCode.FAILURE, "不能含有表情包哦！");
		}
		

		Integer type = request.getType();
		Integer sellerId = request.getSellerId();
		/*
		 * type，入口类型， type=1时，主播身份进入，直播首页、点评列表、直播结束 有sellerid，sellerType
		 * type=2时，美食体验官入口，有通告id 和活动id
		 */
		
		Integer userType;
		String sellerName = request.getSellerName();
		Integer sellerType = request.getSellerType();
		Integer recordType = 1;
		ExperienceComment comment = new ExperienceComment();

		if (type == 1) {
			Integer signType = liveUserDao.querySignType(uid);// 主播签约类型：0 兼职主播，1
																// 签约主播，2公司账号
			userType = signType == 1 ? 1 : 2;
		} else {
			Integer recordId = request.getRecordId();
			userType = 3;
			sellerType = 1;

			recordType = request.getRecordType(); // 直播主题类型：1、商家，2、活动
			
			comment.setRecordId(recordId);
			comment.setActivityId(request.getActivityId());
		}

		comment.setRecordType(recordType);
		comment.setUid(Integer.parseInt(uid));
		comment.setSellerId(sellerId);
		comment.setSellerName(sellerName);
		comment.setSellerType(sellerType);
		comment.setUserType(userType);
		comment.setContent(content);
		comment.setReviewState(0);// 待审核
		comment.setIsRecommend(0);// 默认不推荐
		comment.setCreateTime(new Date());
		comment.setUpvote(new Random().nextInt(40)+10);	//初始化点赞数量

		experienceCommentDao.addComment(comment);

		List<ExperienceCommentMedia> mediaList = new ArrayList<>();

		String picUrls = request.getPicUrls();
		Integer i = 0;
		if (picUrls != null) {
			String[] pic = picUrls.split(",");
			for (i = 0; i < pic.length; i++) {
				ExperienceCommentMedia media = new ExperienceCommentMedia();
				media.setCommentId(comment.getId());
				media.setCreateTime(comment.getCreateTime());
				media.setMediaType(1);
				media.setMediaUrl(pic[i].trim());
				media.setSort(i);
				mediaList.add(media);
			}
		}
		String videoUrl = request.getVideoUrl();
		if (videoUrl != null) {
			ExperienceCommentMedia media = new ExperienceCommentMedia();
			media.setCommentId(comment.getId());
			media.setCreateTime(comment.getCreateTime());
			media.setMediaType(2);
			media.setMediaUrl(videoUrl.trim());
			media.setSort(i);
			mediaList.add(media);
			
			//保存视频截图   先注释
//			List<String> toImageList = cutVideoToImageUtil.cutVideoToImages(fileUrl+videoUrl.trim());
//			if (toImageList.size()>0) {
//				ExperienceCommentMedia mediaVideo = new ExperienceCommentMedia();
//				mediaVideo.setCommentId(comment.getId());
//				mediaVideo.setCreateTime(comment.getCreateTime());
//				mediaVideo.setSort(i);
//				mediaVideo.setMediaType(3);
//				mediaVideo.setMediaUrl(toImageList.get(0));
//				mediaList.add(mediaVideo);
//			}
			
		}

		experienceCommentDao.addCommentMediaList(mediaList);
		return new BaseResponse(ResponseCode.SUCCESS, "点评添加成功");
	}

	@Override
	public Object sellerCommentList(SellerCommentRequest request) {
		Integer sellerid = request.getSellerid();
		Integer sellerType = request.getSellerType();
		List<ExperienceComment> commentList = experienceCommentDao.listExperienceCommentBySellerid(sellerid,sellerType);
		List<Map<Object, Object>> comments = new ArrayList<>();
		for (ExperienceComment comment : commentList) {
			Map<Object, Object> map = new HashMap<>();
			Integer commentUid = comment.getUid();
			Map<Object, Object> userMap = liveUserDao.queryLiverInfoByUid(commentUid);
			
			List<ExperienceCommentMedia> mediaList = comment.getMediaList();
			for (ExperienceCommentMedia media : mediaList) {
				String mediaUrl = fileUrl + media.getMediaUrl();
				media.setMediaUrl(mediaUrl);
			}
			
			
			Date createTime = comment.getCreateTime();
			Date now = new Date();
			Long timeSpan  = (now.getTime()-createTime.getTime())/(1000*60);  //当前时间和评论时间 相差的分钟数
			
			String commentTime = DateUtil.format(createTime,DateUtil.daySimpleFormater);
			
			if(timeSpan < 60){
				commentTime = timeSpan + "分钟前";
			}else if(timeSpan>=60 && timeSpan < 24*60){
				commentTime = timeSpan/60 + "小时前";
			}
			
			
			Object sex = userMap.get("sex");
			map.put("sex", sex == null ? 1 : Integer.parseInt(sex + ""));
			map.put("avatar", fileUrl + userMap.get("avatar"));
			map.put("nname", userMap.get("nname"));
			map.put("commentTime", commentTime);
			map.put("comment", comment);
			comments.add(map);
		}
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS, "查询点评成功");
		Map<Object, Object> result = new HashMap<>();
		result.put("comments", comments);
		result.put("commentCount", comments == null ? 0 : comments.size());
		mapResponse.setResponse(result);
		
		return mapResponse;
	}

	@Override
	public Object initCommentInfo(CommentIdRequest commentIdRequest) {
		String uid = sessionTokenService.getStringForValue(commentIdRequest.getSessiontoken()) + "";

		if (StringUtils.isEmpty(uid) || uid.equals("null")) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		
		Integer commentId = commentIdRequest.getCommentId();
		
		ExperienceComment commentInfo = experienceCommentDao.queryCommentInfoById(commentId);
		List<ExperienceCommentMedia> mediaList = commentInfo.getMediaList();
		for (ExperienceCommentMedia media : mediaList) {
			media.setUrl(media.getMediaUrl());
			media.setMediaUrl(fileUrl + media.getMediaUrl());
		}
		
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		Map<Object,Object> result = new HashMap<>();
		result.put("commentInfo", commentInfo);
		mapResponse.setResponse(result);
		
		return mapResponse;
	}

	@Override
	public Object updateComment(ModifyCommentRequest request) {
		String uid = sessionTokenService.getStringForValue(request.getSessiontoken()) + "";

		if (StringUtils.isEmpty(uid) || uid.equals("null")) {
			return new BaseResponse(ResponseCode.TOKENERR, "token已失效,请重新登录");
		}
		//判断是否有emoj表情包返回给用户
		String content = request.getContent();
		if (EmojiFilter.containsEmoji(content) || EmojiFilter.containsEmojiByMatcher(content)) {		
				return new BaseResponse(ResponseCode.FAILURE, "不能包含表情包哦！");		
		}
		ExperienceComment comment = new ExperienceComment();
		comment.setId(request.getCommentId());		//id
		comment.setContent(content);	//内容
		comment.setReviewState(0);							//重新提交审核设置状态 待审核
		comment.setIsRecommend(0);							//修改为不推荐
		comment.setCreateTime(new Date());					//修改时间
		comment.setSellerId(request.getSellerId());
		comment.setSellerName(request.getSellerName());
		comment.setSellerType(request.getSellerType());
		
		comment.setUpvote(new Random().nextInt(40)+10);	//初始化点赞数量
		
		experienceCommentDao.updateComment(comment);
		
		//级联删除旧的 点评图片和视频
		ExperienceComment commentInfo = experienceCommentDao.queryCommentInfoById(comment.getId());
		List<ExperienceCommentMedia> medias = commentInfo.getMediaList();
		List<String> deleteFileIds = compareOldFileUrl(medias,request);
		//删除服务器历史文件
		fileService.deleteFiles(deleteFileIds);
		experienceCommentDao.deleteCommentMedia(comment.getId());
		
		
		//插入新的 点评图片和视频
		List<ExperienceCommentMedia> mediaList = new ArrayList<>();

		//获取修改上传的图片信息
		String picUrls = request.getPicUrls();
		Integer i = 0;
		if (picUrls != null) {
			String[] pic = picUrls.split(",");
			for (i = 0; i < pic.length; i++) {
				ExperienceCommentMedia media = new ExperienceCommentMedia();
				media.setCommentId(comment.getId());
				media.setCreateTime(comment.getCreateTime());
				media.setMediaType(1);
				media.setMediaUrl(pic[i]);
				media.setSort(i);
				mediaList.add(media);
			}
		}
		//获取修改上传的视频信息
		String videoUrl = request.getVideoUrl();
		if (videoUrl != null) {
			ExperienceCommentMedia media = new ExperienceCommentMedia();
			media.setCommentId(comment.getId());
			media.setCreateTime(comment.getCreateTime());
			media.setMediaType(2);
			media.setMediaUrl(videoUrl);
			media.setSort(i);
			mediaList.add(media);
		}
		
		experienceCommentDao.addCommentMediaList(mediaList);
		return new BaseResponse(ResponseCode.SUCCESS, "点评修改成功");
	}



	/**对比返回的url获取需要删掉的服务器中的资源文件
	 * @param file_ids
	 * @param request
	 */
	private List<String> compareOldFileUrl(List<ExperienceCommentMedia> oldmedias, ModifyCommentRequest request) {
		List<String> deleteFilUrls = new ArrayList<String>();
		if (oldmedias!=null && !oldmedias.isEmpty()) {
			String picUrls = request.getPicUrls();
			String[] picArray = picUrls.split(",");
			List<String> newFileUrls = new ArrayList<String>();
			Collections.addAll(newFileUrls, picArray);
			if (StringUtils.isNotBlank(request.getVideoUrl())) {
				String vedioUrl = request.getVideoUrl();
				newFileUrls.add(vedioUrl);
			}
			for(int i=0;i<oldmedias.size();i++) {
				String url = oldmedias.get(i).getMediaUrl();
				if (!newFileUrls.contains(url)) {
					deleteFilUrls.add(url);
				}
			}																	
		}	
		return deleteFilUrls;
	}

	@Override
	public Object commentSellerInfo(CommentSellerRequest request) {

		Map<Object,Object> result = new HashMap<>();
		Map<String,String> sellerInfo = new HashMap<>();
		String picUrl = "";
		Integer recordType = 1;
		Integer type = request.getType();
		if(type.equals(1)){ //主播入口，有sellerId 和 sellerType
			Integer sellerId = request.getSellerId();
			Integer sellerType = request.getSellerType();
			
			if(sellerType.equals(1)){ //商家标识 1 签约商家, 2 非签约商家
				sellerInfo = sellerInfoDao.queryCommentSellerInfoById(sellerId);
			}else if(sellerType.equals(2)){
				sellerInfo = unsignedSellerDao.queryCommentSellerInfoById(sellerId);
			}
			
			if(sellerInfo.isEmpty()){
				return new BaseResponse(ResponseCode.FAILURE,"商家信息查询失败");
			}
			if(StringUtils.isNotEmpty(sellerInfo.get("picUrl"))){
				picUrl = fileUrl + sellerInfo.get("picUrl");
			}
			sellerInfo.put("picUrl", picUrl);
			
		}else if(type.equals(2)){	//美食体验官入口，recordId
			Integer recordId = request.getRecordId();
			Map<Object, Object> liveRecordInfo = anchorLiveRecordDao.queryLiveRecordInfoById(recordId);
			if (liveRecordInfo == null || liveRecordInfo.isEmpty()) {
				return new BaseResponse(ResponseCode.FAILURE, "直播通告记录查询失败");
			}
			recordType = Integer.parseInt(liveRecordInfo.get("live_topic") + ""); // 直播主题类型：1、商家，2、活动
			if(recordType.equals(1)){
				Integer sellerId = Integer.parseInt(liveRecordInfo.get("sellerid")+"");	//商家通告根据sellerid查询商家信息
				sellerInfo = sellerInfoDao.queryCommentSellerInfoById(sellerId);
				
				if(sellerInfo.isEmpty()){
					return new BaseResponse(ResponseCode.FAILURE,"商家信息查询失败");
				}
				if(StringUtils.isNotEmpty(sellerInfo.get("picUrl"))){
					picUrl = fileUrl + sellerInfo.get("picUrl");
				}
				sellerInfo.put("picUrl", picUrl);
				
			}else if(recordType.equals(2)){	//活动通告
				Map<String,String> activityInfo = new HashMap<>();
				activityInfo.put("activityName", liveRecordInfo.get("seller_alias") +"");
				activityInfo.put("address", liveRecordInfo.get("zhibo_address") +"");
				if(StringUtils.isNotEmpty(liveRecordInfo.get("zhibo_cover") +"")){
					picUrl = fileUrl + liveRecordInfo.get("zhibo_cover") +"";
				}
				activityInfo.put("picUrl", picUrl);
				result.put("activityInfo", activityInfo);
			}
		}
		
		result.put("recordType", recordType);
		result.put("sellerInfo", sellerInfo);
		
		MapResponse mapResponse = new MapResponse(ResponseCode.SUCCESS,"查询成功");
		mapResponse.setResponse(result);
		
		return mapResponse;
	}

}
