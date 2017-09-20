package com.xmn.designer.service.postage;

import java.math.BigDecimal;

import com.xmn.designer.entity.postage.PostageConditions;

public interface PostageService {
	
	/**
	 * 
	 * 方法描述：获取指定商户指定区域运费信息
	 * 创建人：jianming  
	 * 创建时间：2016年11月21日 下午3:32:37   
	 * @param materialId
	 * @param areaId
	 * @return
	 */
	PostageConditions getCondition(Long materialId, Integer areaId);
	
	/**
	 * 
	 * 方法描述：计算运费价格
	 * 创建人：jianming  
	 * 创建时间：2016年11月21日 下午5:24:39   
	 * @param materialId
	 * @param areaId
	 * @param num 
	 * @return
	 */
	BigDecimal calculateCondittion(Long materialId, Integer areaId, Integer num);
	
}
