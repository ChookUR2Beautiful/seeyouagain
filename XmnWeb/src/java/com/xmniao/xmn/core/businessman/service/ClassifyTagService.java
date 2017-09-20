/**
 * 
 */
package com.xmniao.xmn.core.businessman.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.businessman.dao.ClassifyTagDao;
import com.xmniao.xmn.core.businessman.entity.Classify;
import com.xmniao.xmn.core.businessman.entity.ClassifyTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ClassifyTag
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月24日 下午4:02:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class ClassifyTagService extends BaseService<ClassifyTag>{

	@Autowired
	private ClassifyTagDao classifyTagDao;
	
	@Autowired
	private ClassifyService classifyService;
	
	@Override
	protected BaseDao getBaseDao() {
		return classifyTagDao;
	}

	/**
	 * 方法描述：保存标签
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月24日下午6:02:08 <br/>
	 * @param classifyTag
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addTag(ClassifyTag classifyTag) {
		Classify t = new Classify();
		t.setClassifyName(classifyTag.getClassifyName());
		t.setClassifyType( classifyTag.getClassifyType());
		Classify classify = classifyService.getObject(t); 
		if(classify==null){
			t.setUpdateTime(new Date());
			classifyService.add(t);   
			classify=t;
		}
		String tagName = classifyTag.getTagName();
		String[] tagNames = tagName.split(",");
		for (String name : tagNames) {
			ClassifyTag tag = new ClassifyTag();
			tag.setClassifyId(classify.getId());
			tag.setTagName(name);
			classifyTagDao.add(tag);  
		}
			
	}
	
}
