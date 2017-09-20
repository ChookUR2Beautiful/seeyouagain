package com.xmniao.xmn.core.user_terminal.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.user_terminal.entity.TRegisterGift;

public interface RegisterGiftDao {
	/**
	 * 
	 * 方法描述：注册礼包列表
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:17:50
	 * @param registerGift
	 * @return
	 */
	List<TRegisterGift> getGiftList(TRegisterGift registerGift);

	/**
	 * 
	 * 方法描述：注册礼包总数
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:18:20
	 * @param registerGift
	 * @return
	 */
	long getGiftCount(TRegisterGift registerGift);
	
	/**
	 * 
	 * 方法描述：更新
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:19:41
	 * @param registerGift
	 * @return
	 */
	int updateRegisterGift(TRegisterGift registerGift);
	
	/**
	 * 
	 * 方法描述：删除
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:19:52
	 * @param registerGift
	 * @return
	 */
	int deleteRegisterGift(Integer id);
	
	/**
	 * 
	 * 方法描述：添加
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:20:32
	 * @param registerGift
	 * @return
	 */
	int addRegisterGift(TRegisterGift registerGift);
	
	/**
	 * 
	 * 方法描述：获取礼包信息
	 * 创建人： ChenBo
	 * 创建时间：2016年8月10日上午10:20:32
	 * @param registerGift
	 * @return
	 */
	TRegisterGift getRegisterGift(Integer id);
	
	/**
	 * 
	 * 方法描述：获取注册礼包图
	 * 创建人： ChenBo
	 * 创建时间：2016年8月15日下午3:50:57
	 * @return
	 */
	List<Map<String,Object>> getRegisterGiftPic();
	
	/**
	 * 
	 * 方法描述：设置注册礼包图
	 * 创建人： ChenBo
	 * 创建时间：2016年8月15日下午3:51:00
	 * @param imgMap
	 * @return
	 */
	int updateImg(Map<String,Object> imgMap);
	
	/**
	 * 
	 * 方法描述：设置注册礼包图
	 * 创建人： ChenBo
	 * 创建时间：2016年8月15日下午3:51:00
	 * @param imgMap
	 * @return
	 */
	int addImg(List<Map<String,Object>> imgList);
}