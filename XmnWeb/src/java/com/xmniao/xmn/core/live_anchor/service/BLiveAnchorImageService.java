/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.BLiveAnchorImageDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiveAnchorImage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：BLiveAnchorImageService
 *
 * 类描述：主播相册Service
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-18下午4:54:14
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class BLiveAnchorImageService extends BaseService<BLiveAnchorImage> {
	
	/**
	 * 注入主播相册服务
	 */
	@Autowired
	private BLiveAnchorImageDao liveAnchorImageDao;
	
	/**
	 * 注入redis模板服务
	 */
	/*@Autowired
	private StringRedisTemplate redisTemplate;*/

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return liveAnchorImageDao;
	}

	/**
	 * 
	 * 方法描述：删除主播相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:21
	 * @param id
	 * @return
	 */
    public int deleteById(Integer id){
		return liveAnchorImageDao.deleteById(id);
	}

    
    public int deleteImages(Integer imageType){
    	return liveAnchorImageDao.deleteImages(imageType);
    }
    
    /**
	 * 
	 * 方法描述：根据条件删除相册 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-4-12下午4:53:58 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value = "burs")
	public int deleteImagesByBean(BLiveAnchorImage record){
		return liveAnchorImageDao.deleteImagesByBean(record);
	}
    

	/**
	 * 
	 * 方法描述：添加主播相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:38
	 * @param record
	 * @return
	 */
    public  void add(BLiveAnchorImage record){
    	liveAnchorImageDao.add(record);
    }

    /**
	 * 
	 * 方法描述：批量添加相册
	 * 创建人： huang'tao
	 * 创建时间：2016-8-18下午4:43:38
	 * @param record
	 * @return
	 */
    public Integer addBatch(List<BLiveAnchorImage> imageList){
    	return liveAnchorImageDao.addBatch(imageList);
    }
    /**
     * 
     * 方法描述：根据ID获取主播相册
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:44:16
     * @param id
     * @return
     */
    public BLiveAnchorImage selectById(Integer id){
    	return liveAnchorImageDao.selectById(id);
    }

    /**
     * 
     * 方法描述：更新主播相册
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:37:54
     * @param record
     * @return
     */
    public Integer update(BLiveAnchorImage record){
    	return liveAnchorImageDao.update(record);
    }
    
    /**
     * 
     * 方法描述：获取主播相册列表
     * 创建人： huang'tao
     * 创建时间：2016-8-18下午4:37:54
     * @param record
     * @return
     */
    public List<BLiveAnchorImage> getList(BLiveAnchorImage record){
    	return liveAnchorImageDao.getList(record);
    }
    
    public Long count(BLiveAnchorImage record){
    	return liveAnchorImageDao.count(record);
    }
    
    /**
     * 批量添加相册
     */
    public int addImageBatch(String picUrls,Integer imageType,int anchorId){
    	int count=0;
    	if(StringUtils.isNotBlank(picUrls)){
    		String[] images = picUrls.split(";");
    		List<BLiveAnchorImage> imageList=new ArrayList<BLiveAnchorImage>();
    		if(images!=null && images.length>0){
    			for(String anchorImage:images){
    				BLiveAnchorImage anchorImageBean=new BLiveAnchorImage();
    				anchorImageBean.setAnchorId(anchorId);
    				anchorImageBean.setAnchorImage(anchorImage);
    				anchorImageBean.setImageType(LiveConstant.IMAGETYPE_ROBOT);
    				anchorImageBean.setStatus(1);//默认 1启用   0停用
    				anchorImageBean.setCreateTime(new Date());
    				anchorImageBean.setUpdateTime(new Date());
    				imageList.add(anchorImageBean);
    			}
    		}
    		if(imageList!=null && imageList.size()>0){
    			count = liveAnchorImageDao.addBatch(imageList);
    		}
    	}
		return count;
    }

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月20日上午11:26:23 <br/>
	 * @param asList
	 * @return
	 */
	public List<BLiveAnchorImage> getByIds(List<String> asList) {
		return liveAnchorImageDao.getByImageIds(asList);
	}
}
