/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.VstarCommentDao;
import com.xmniao.xmn.core.vstar.entity.VstarComment;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarCommentService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月16日 下午1:46:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VstarCommentService extends BaseService<VstarComment>{

	@Autowired
	private VstarCommentDao vstarCommentDao;
	
	@Autowired
	private BursDao bursDao;

	@Override
	protected BaseDao getBaseDao() {
		return vstarCommentDao;
	}
	
	public List<VstarComment> getList(VstarComment vstarComment) {
		List<VstarComment> list = vstarCommentDao.getList(vstarComment);
		setUserMsg(list);
		return list;
	}

	private void setUserMsg(List<VstarComment> list) {
		if(list.isEmpty()){
			return;
		}
		List<Integer> uids= new ArrayList<>();
		for(VstarComment comment : list){
			if(comment.getUid()!=null){
				uids.add(comment.getUid());
			}
		}
		List<Burs> ursListByUids = bursDao.getUrsListByUids(uids.toArray());
		one: for(VstarComment comment : list){
			 for (Burs burs : ursListByUids) {
				if(comment.getUid()!=null&&comment.getUid().equals(burs.getUid())){
					comment.setUserName(burs.getNname());
					comment.setUserPhone(burs.getPhone());
					continue one;
				}
			}
		 }
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月16日下午3:34:01 <br/>
	 * @param id
	 */
	public void delete(Integer id) {
		vstarCommentDao.delete(id);
	}
	
}
