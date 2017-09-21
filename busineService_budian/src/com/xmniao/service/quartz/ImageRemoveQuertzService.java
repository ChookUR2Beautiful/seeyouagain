/**    
 * 文件名：ImageRemoveQuertzService.java    
 *    
 * 版本信息：    
 * 日期：2016年12月22日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2016     
 * 版权所有    
 *    
 */
package com.xmniao.service.quartz;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.fastdfs.FastDfsClient;
import com.xmniao.dao.common.ImageTempDao;
import com.xmniao.domain.common.ImageTempEntity;


/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：ImageRemoveQuertzService
 * 
 * 类描述：移除fastDFS文件服务器上无效的图片定时任务
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月22日 上午9:51:05 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service("imageRemoveQuertz")
public class ImageRemoveQuertzService {
    /*
     * 日志记录
     */
    private final Logger log = Logger.getLogger(getClass());
    
    @Autowired
    private ImageTempDao imageTempDao;
    
	public void removeImage(){
		try{
			/*
			 * 1.检测是否有没删除的图片文件
			 * 2.for{
			 *   1.获取当前有可奖励订单的用户
			 *   	for{
			 *   	1.查看用户是否限领分红红包
			 *   	2.获取用户前一天的打赏信息
			 *   	3.根据前一天的打赏信息，与当前用户粉丝级别对比，检测其是否可领分红红包
			 *   	4.查询用户当前所有在享受红包订单
			 *   		for{
			 *   		1.检测订单的奖励及红包及现金/鸟币情况
			 *   		2.获取订单本次可享受的红包比例区间
			 *   		3.根据订单与红包比例，随机出本单可获取的现金+鸟币
			 *   		}
			 *   	5.合并用户本次能获取的所有红包，并写入红包记录
			 *   	}
			 *   2.获取下一批有可奖励订单的用户
			 *   }
			 */
			log.info("开始定时移除无效图片在fastDfs中的存储");
			
			List<ImageTempEntity> imageList = new ArrayList<>();
			List<Long> rmList = new ArrayList<>();
			do{
				imageList = imageTempDao.getImageList();
				if(imageList.size()==0){
					break;
				}
				
				for(ImageTempEntity image:imageList){
					int result = FastDfsClient.getInstance().deleteFile(image.getUrl());
					if(result==0 || result==2){
						rmList.add(image.getId());
					}else{
						log.error("删除异常:"+image.getUrl());
					}
				}
				if(rmList.size()>0){
					imageTempDao.removeImage(rmList);
				}
			}while(imageList.size()==500);
			log.info("完成定时移除无效图片在fastDfs中的存储");
		}catch(Exception e){
			log.error("定时移除无效图片在fastDfs中的存储异常",e);
		}finally{
		}
	}

}

