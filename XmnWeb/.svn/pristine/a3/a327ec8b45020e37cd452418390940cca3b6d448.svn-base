package com.xmniao.xmn.core.user_terminal.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.user_terminal.dao.TTopicCommentDao;
import com.xmniao.xmn.core.user_terminal.dao.TTopicDao;
import com.xmniao.xmn.core.user_terminal.dao.TTopicImgDao;
import com.xmniao.xmn.core.user_terminal.entity.TTopic;
import com.xmniao.xmn.core.user_terminal.entity.TTopicComment;
import com.xmniao.xmn.core.user_terminal.entity.TTopicImg;
import com.xmniao.xmn.core.user_terminal.entity.TopicGlobal;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.StringUtils;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TTopicService
 * 
 * 类描述：寻蜜鸟话题service类
 * 
 * 创建者：yang'xu
 *
 * 创建时间：2014-12-23 13：53：41
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
@Service
public class TTopicService extends BaseService<TTopic> {
	@Autowired
	private TTopicDao topicDao;
	@Autowired
	private TTopicCommentDao topicCommentDao;
	@Autowired
	private TTopicImgDao topicImgDao;
	@Override
	protected BaseDao getBaseDao() {
		
		return topicDao;
	}
	/**
	 * 
	 * @param topicId
	 * @return
	 */
	public List<TTopicComment> getCommentList(Long topicId){
		return topicCommentDao.getTopicCommentList(topicId);
	}
	/**
	 * 添加话题、话题图片
	 * @param topicGlobal
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Resultable addTopicGlobalService(TopicGlobal topicGlobal) {//话题基本信息添加
		Resultable resultable=null;
		try {
			addTopicAndTopicImg(topicGlobal);//添加操作
			resultable = logAndResultable(topicGlobal);//返回信息
		} catch (Exception e) {
			this.log.error("新增：", e);
			throw new ApplicationException("新增",e,new Object[]{topicGlobal},getStr(topicGlobal));
		}
		return resultable;
	}
	public Resultable logAndResultable(TopicGlobal topicGlobal){
		this.log.info("新增成功");
		fireLoginEvent(getStr(topicGlobal),UserConstants.FIRELOGIN_SUCCESS);
		return new Resultable(true, "操作成功");
	}
	public String[] getStr(TopicGlobal topicGlobal){
		String word = topicGlobal.getTopic().getContent();//添加到日志记录表
		String str = "";
		if (word.length() <= 12){
			str = word;
		}else{
			str = word.substring(0, 12)+"...";
		}
		return new String[]{"成长记",str,"新增"};
	}
	public void addTopicAndTopicImg(TopicGlobal topicGlobal){//新增操作
		try {
			addTopic(topicGlobal);//话题新增
			addTopicImg(topicGlobal);//话题图片新增
		} catch (Exception e) {
			this.log.error("话题信息新增：", e);
			throw new ApplicationException("话题信息新增",e,new Object[]{topicGlobal});
		}
	}
	public void addTopic(TopicGlobal topicGlobal){//话题新增
		try {
			topicGlobal.getTopic().setTime(new Date());//话题对象  设置默认时间
			topicDao.addReturnId(topicGlobal.getTopic());//新增话题
		} catch (Exception e) {
			this.log.error("话题新增：", e);
			throw new ApplicationException("话题新增",e,new Object[]{topicGlobal});
		}
	}
	public void addTopicImg(TopicGlobal topicGlobal){//话题图片新增
		try {
			List<TTopicImg> imgList = topicGlobal.getTopicImgList();//获取话题图片对象
			if(null != imgList && !imgList.isEmpty()){//添加话题图片
				for (TTopicImg topicImg : topicGlobal.getTopicImgList()){
					topicImg.setTopicId(topicGlobal.getTopic().getId());
					topicImgDao.add(topicImg);
				}
			}
		} catch (Exception e) {
			this.log.error("话题图片新增：", e);
			throw new ApplicationException("话题图片新增",e,new Object[]{topicGlobal});
		}
	}
	
	/**
	 * 获取话题的全部信息：话题、话题评论、话题图片
	 * @param topicId
	 * @return
	 */
	public TopicGlobal getTopicInfo(Long topicId){
		TopicGlobal topicGlobal = new TopicGlobal();
		TTopic topic = topicDao.getObject(topicId);
		List<TTopicComment> topicCommentList =  topicCommentDao.getTopicCommentList(topicId);
		List<TTopicImg> topicImgList =  topicImgDao.getTopicImgList(topicId);
		topicGlobal.setTopic(topic);
		topicGlobal.setTopicCommentList(topicCommentList);
		topicGlobal.setTopicImgList(topicImgList);
		return topicGlobal;
	}
	
	/**
	 * 更新话题相关信息：话题、话题图片、话题评论
	 * @param topicGlobal
	 * @return
	 */
	public Resultable updateTopicGlobalServer(TopicGlobal topicGlobal) {
		Resultable resultable=null;
		try {
			updateTopicGlobal(topicGlobal);//话题信息更新
			resultable=logResult(topicGlobal);//日志记录、信息返回
		} catch (Exception e) {
			this.log.error("话题更新：", e);
			throw new ApplicationException("话题更新",e,new Object[]{topicGlobal},new String[]{"成长记编号",String.valueOf(topicGlobal.getTopic().getId()),"修改","修改"});
		}
		return resultable;
	}
	public Resultable logResult(TopicGlobal topicGlobal){//日志记录、信息返回
		this.log.info("修改成功");
		String[] s={"成长记编号",String.valueOf(topicGlobal.getTopic().getId()),"修改","修改"};
		fireLoginEvent(s,UserConstants.FIRELOGIN_SUCCESS);//添加到日志记录表
		return  new Resultable(true, "操作成功");
	}
	public void updateTopicGlobal(TopicGlobal topicGlobal){//更新
		try {
			updateTopic(topicGlobal);//话题更新
			updateTopicImg(topicGlobal);//话题图片更新
		} catch (Exception e) {
			this.log.error("更新：", e);
			throw new ApplicationException("更新",e,new Object[]{topicGlobal});
		}
	}
	public void updateTopic(TopicGlobal topicGlobal){//话题更新
		try {
			topicDao.update(topicGlobal.getTopic());
		} catch (Exception e) {
			this.log.error("话题信息更新：", e);
			throw new ApplicationException("话题信息更新",e,new Object[]{topicGlobal});
		}
	}
    public void  updateTopicImg(TopicGlobal topicGlobal){//话题图片更新
    	try {
			List<TTopicImg> imgList = topicGlobal.getTopicImgList();
			Integer[]  id = {topicGlobal.getTopic().getId()};
			topicImgDao.deleteTopicImg(id);//清除后，添加
			if(null !=imgList && !imgList.isEmpty()){//根据判断进行更新操作
				updateTopicImgs(topicGlobal);
			}
		} catch (Exception e) {
			this.log.error("话题图片信息更新：", e);
			throw new ApplicationException("话题图片信息更新",e,new Object[]{topicGlobal});
		}
	}
    public void updateTopicImgs(TopicGlobal topicGlobal){//话题图片更新添加
		for (TTopicImg topicImg : topicGlobal.getTopicImgList()){
			if(StringUtils.hasLength(topicImg.getImg())){
				topicImg.setTopicId(topicGlobal.getTopic().getId());
				topicImgDao.add(topicImg);
			}
		}
    }
	/**
	 *  删除话题及其图片和评论
	 */
	public Integer deleteTopic(Object[] objects) {
		//调3个dao的删除方法
		Integer[] num = {0,0,0};
		num[1] = topicCommentDao.deleteTopicComent(objects);
		num[2] = topicImgDao.deleteTopicImg(objects);
		num[0] = topicDao.delete(objects);
		return num[0];
	}
	/**
	 * 删除话题评论信息
	 */
	public Integer deleteComment(Object[] objects){
		return topicCommentDao.delete(objects);
	}
	/**
	 * 删除评论相关的回复
	 */
	public Integer deleteReply(Object[] objects){
		return topicCommentDao.deleteReply(objects);
	}
	/**
	 * 添加评论信息，包含了评论的内容、图片、和评论
	 */
	public TTopicComment addComment(TopicGlobal topicGlobal,Object user){
		TTopicComment topicComment = new TTopicComment();
		//获取当前话题的id
		Long topicId = new Long (topicGlobal.getTopic().getId());
		//添加剂话题的其他属性
		topicComment.setTopicId(topicGlobal.getTopic().getId());
		topicComment.setContent(topicGlobal.getTopicComment().getContent());
		topicComment.setTime(new Date());
		topicComment.setStatus(1);
		if(user!=null){
			TUser u = (TUser)user;
			topicComment.setUserId(u.getUserId().intValue());
			topicComment.setNname(u.getName());
		}
		topicCommentDao.add(topicComment);
		this.log.info("添加评论信息"+topicComment);
		TTopic topic = topicDao.getObject(topicId);
		Integer commentNum = topic.getCommentNum()+1;
		topic.setCommentNum(commentNum);
		this.log.info("话题评论数为"+commentNum);
		topicDao.update(topic);
		return topicComment;
	}
	/**
	 * 需求变更：回复的userId和username 和评论记录的保持一致
	 * @param reply
	 * @param user
	 * @return
	 */
	public TTopicComment addReply(TTopicComment reply,Object user){
		/*
		 * 待添加的回复信息 topicComment
		 */
		TTopicComment topicComment = new TTopicComment();
		topicComment.setTopicId(reply.getTopicId());
		topicComment.setContent(reply.getContent());
		topicComment.setTime(new Date());
		topicComment.setType(1);
		topicComment.setStatus(1);
		topicComment.setPid(reply.getPid());
		/*
		 * 被回复的评论
		 */
		TTopicComment fatherComment = topicCommentDao.getObject(new Long(reply.getPid()));
		topicComment.setUserId(fatherComment.getUserId());
		topicComment.setNname(fatherComment.getNname());
		
		/*
		if(user!=null){
			TUser u = (TUser)user;
			topicComment.setUserId(u.getUserId().intValue());
			topicComment.setNname(u.getName());
		}
		*/
		topicCommentDao.add(topicComment);
		return topicComment;
	}
	
	/**
	 * 统计话题的评论数量
	 * @param topicId
	 * @return
	 */
	public Long countComment(Integer topicId){
		return topicCommentDao.countComment(topicId);
	}
}
