package com.xmniao.xmn.core.vstar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.dao.TVstarContentImgDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentImg;
/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentImgDao
 * 
 * 类描述： V客学堂图片素材Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:24:56 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TVstarContentImgService extends BaseService<TVstarContentImg>{

	@Autowired
	private TVstarContentImgDao vstarImgDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return vstarImgDao;
	}

	/**
	 * 方法描述：批量添加图片 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-27下午3:36:02 <br/>
	 * @param id
	 * @param imgUrls
	 * @return
	 */
	public Resultable addImgBatch(Long id, String imgUrls) {
		Resultable result= new Resultable();
		// TODO Auto-generated method stub
		try {
			if(StringUtils.isNotBlank(imgUrls)){
				String[] imgUrlArray = imgUrls.split(";");
				List<TVstarContentImg> imgList=new ArrayList<TVstarContentImg>();
				for(String imgUrl:imgUrlArray){
					TVstarContentImg vstarImg=new TVstarContentImg();
					vstarImg.setContentId(id);
					vstarImg.setImgUrl(imgUrl);
					vstarImg.setStatus(1);//有效状态，1有效，2无效
					vstarImg.setCreateTime(new Date());
					vstarImg.setUpdateTime(new Date());
					imgList.add(vstarImg);
				}
				
				if(imgList!=null && imgList.size()>0){
					vstarImgDao.addBatch(imgList);
				}
				
				result.setSuccess(true);
				result.setMsg("操作成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
		}
		
		return result;
	}
}