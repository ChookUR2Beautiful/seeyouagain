/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.CommentMediaDao;
import com.xmniao.xmn.core.businessman.dao.ExperienceCommentDao;
import com.xmniao.xmn.core.businessman.dao.SellerDao;
import com.xmniao.xmn.core.businessman.dao.UnsignedSellerDao;
import com.xmniao.xmn.core.businessman.entity.CommentMedia;
import com.xmniao.xmn.core.businessman.entity.ExperienceComment;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.userService.ResponseData;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ExperienceCommentService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年5月15日 下午5:22:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Service
public class ExperienceCommentService extends BaseService<ExperienceComment>{
	
	@Autowired
	private ExperienceCommentDao experienceCommentDao;
	
	@Autowired
	private BursService bursService;
	
	@Autowired
	private CommentMediaDao commentMediaDao;
	
	@Autowired
	private SellerDao sellerDao;

	@Autowired
	private UnsignedSellerDao unsignedSellerDao;
	
	@Resource(name="userServiceClient")
	private ThriftClientProxy thriftClientProxy;
	
	@Override
	protected BaseDao getBaseDao() {
		return experienceCommentDao;
	}

	/**
	 * 方法描述：getList
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月15日下午6:25:09 <br/>
	 * @param experienceComment
	 * @return
	 */
	public List<ExperienceComment> getExperienceCommentList(ExperienceComment experienceComment) {
		if(StringUtils.isNotBlank(experienceComment.getLiverPhone())||StringUtils.isNotBlank(experienceComment.getLiverName())){
			Burs burs = new Burs();
			burs.setPhone(experienceComment.getLiverPhone());
			burs.setNname(experienceComment.getLiverName());
			List<Burs> ursList = bursService.getUrsList(burs);
			if(ursList.isEmpty()){
				return new ArrayList<>();
			}
			List<Integer> uids=new ArrayList<>();
			for (Burs burs1 : ursList) {
				uids.add(burs1.getUid());
			}
			experienceComment.setUids(uids);
			
		}
		List<ExperienceComment> list = experienceCommentDao.getList(experienceComment);
		if(list.isEmpty()){
			return list;
		}
		List<Integer> uids=new ArrayList<>();
		for (ExperienceComment experienceComment2 : list) {
			uids.add(experienceComment2.getUid());
		}
		List<Burs> urs = bursService.getUrsListByUids(uids.toArray());
		one:for (ExperienceComment experienceComment2 : list) {
			for (Burs burs2 : urs) {
				if(experienceComment2.getUid().equals(burs2.getUid())){
					experienceComment2.setLiverName(burs2.getNname());
					experienceComment2.setLiverPhone(burs2.getPhone());
					continue one;
				}
			}
		}
		
		return list;	
		
		
	}

	/**
	 * 方法描述：count
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月15日下午6:25:53 <br/>
	 * @param experienceComment
	 * @return
	 */
	public Long experienceCommentCount(ExperienceComment experienceComment) {
		return experienceCommentDao.count(experienceComment);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午2:08:33 <br/>
	 * @param id
	 * @param isRecommend
	 * @return
	 */
	public int updateIsRecommend(Integer id, Integer isRecommend) {
		return experienceCommentDao.updateIsRecommend(id,isRecommend);
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午2:10:28 <br/>
	 * @param id
	 * @param reviewState
	 * @param content 
	 * @return
	 */
	public int updateReviewState(Integer id, Integer reviewState, String refuseRemark) {
		return experienceCommentDao.updateReviewState(id,reviewState,refuseRemark);
	}

	/**
	 * 方法描述：获取签约和未签约的商户
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日上午11:14:05 <br/>
	 * @param experienceComment
	 * @return
	 */
	public List<ExperienceComment> getCommentChoose(ExperienceComment experienceComment) {
		return experienceCommentDao.getCommentChoose(experienceComment);
	}

	/**
	 * 方法描述：查询评论详情
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月19日下午2:03:17 <br/>
	 * @param id
	 * @return
	 */
	public ExperienceComment getExperienceCommentDetail(Integer id) {
		ExperienceComment param = new ExperienceComment();
		param.setId(id);
		List<ExperienceComment> experienceCommentList = getExperienceCommentList(param);
		ExperienceComment experienceComment = experienceCommentList.get(0);
		HashMap<String,String> hashMap = new HashMap<>();
		hashMap.put("uid", experienceComment.getUid().toString());
		try {
			ResponseData data =  thriftClientProxy.doClient("getUserMsg", hashMap);
			experienceComment.setAvatar(data.getResultMap().get("avatar"));
		} catch (Exception e) {
			log.error(e);
		}
		List<CommentMedia> list=commentMediaDao.getListByCommentId(id);
		Comparator<CommentMedia> comparator=  new Comparator<CommentMedia>(){
			@Override
			public int compare(CommentMedia o1, CommentMedia o2) {
				 return o1.getVideoType()<o2.getVideoType()?0:1;
			}
		};
		Collections.sort(list, comparator);
		experienceComment.setCommentMedias(list);
		Map<String,Object> map;
		if(experienceComment.getSellerType()==1){
			//签约	
			map=sellerDao.getCommentParam(experienceComment.getSellerid());
		}else{
			//非签约
			map=unsignedSellerDao.getCommentParam(experienceComment.getSellerid());
		}
		if(map!=null){
			experienceComment.setAddress((String) map.get("address"));
			experienceComment.setZoneName((String) map.get("zoneName"));
			experienceComment.setPhone((String) map.get("phone"));
		}
		return experienceComment; 
	}

}
