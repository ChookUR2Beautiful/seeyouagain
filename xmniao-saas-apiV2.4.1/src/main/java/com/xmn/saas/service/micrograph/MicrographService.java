package com.xmn.saas.service.micrograph;

import java.util.List;
import java.util.Map;

import com.xmn.saas.entity.celebrity.Tag;
import com.xmn.saas.entity.micrograph.MicrographModuleShare;
import com.xmn.saas.entity.micrograph.MicrographPage;
import com.xmn.saas.entity.micrograph.MicrographSearch;
import com.xmn.saas.entity.micrograph.MicrographTemplate;
import com.xmn.saas.entity.shop.SellerInfo;

public interface MicrographService {
	
	/**
	 * 
	 * 方法描述：加载列表
	 * 创建人：jianming  
	 * 创建时间：2016年11月30日 下午4:48:58   
	 * @param pageSize
	 * @param pageIndex
	 * @param tag 
	 * @param searchName 
	 * @param pageIndex2 
	 * @return
	 */
	List<MicrographTemplate> list(MicrographSearch searchModel);
	
	/**
	 * 
	 * 方法描述：获取默认排序标签
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 上午9:53:19   
	 * @return
	 */
	List<Tag> getTagsBySerial();
	
	/**
	 * 
	 * 方法描述：关键字模糊搜索
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午2:18:56   
	 * @param name
	 * @return 
	 */
	List<Map<String, String>> searchLike(String name);
	
	/**
	 * 
	 * 方法描述：获取搜索记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午5:09:33   
	 * @param sellerId
	 * @return
	 */
	List<MicrographSearch> getMicrographSearch(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：删除搜索记录
	 * 创建人：jianming  
	 * 创建时间：2016年12月1日 下午8:12:05   
	 * @param sellerId
	 */
	void clearSearch(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询模板页面
	 * 创建人：jianming  
	 * 创建时间：2016年12月2日 上午11:13:45   
	 * @param id
	 * @return
	 */
	List<MicrographPage> pageList(Integer id);
	
	/**
	 * 
	 * 方法描述：保存分享信息
	 * 创建人：jianming  
	 * 创建时间：2016年12月3日 下午5:33:36   
	 * @param list
	 * @param sellerId
	 */
	Integer saveShare(List<MicrographModuleShare> list, Integer sellerId);
	
	/**
	 * 
	 * 方法描述：根据分享记录id获取商户信息
	 * 创建人：jianming  
	 * 创建时间：2016年12月12日 下午5:16:39   
	 * @param id
	 * @return
	 */
	SellerInfo getSellerMsg(Integer id);
	
	

}
