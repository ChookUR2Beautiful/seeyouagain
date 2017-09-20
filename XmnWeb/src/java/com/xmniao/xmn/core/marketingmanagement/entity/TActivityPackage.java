/**
 * 文件名：TActivity.java
 * 
 * 创建日期:2015-01-14 10:03:36
 * 
 * Copyright © 寻蜜鸟网络科技有限公司
 */
package com.xmniao.xmn.core.marketingmanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.Rate;

import com.alibaba.fastjson.annotation.JSONField;
import com.xmniao.xmn.core.base.BaseEntity;
import com.xmniao.xmn.core.businessman.entity.TExtensionSet;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;
import com.xmniao.xmn.core.businessman.entity.TSellerPic;
/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivityPackage
 * 
 * 类说明：营销活动管理
 * 
 * 创建人：caoyingde
 * 
 * 创建时间：2015-01-14 10：09：03
 * 
 * Copyright © 广东寻蜜鸟网络科技有限公司
 */
public class TActivityPackage{
	
	private TActivity tActivity; //活动对象
	private TActivityRule tActivityRule; //活动规则对象
	
	public TActivity gettActivity() {
		return tActivity;
	}

	public void settActivity(TActivity tActivity) {
		this.tActivity = tActivity;
	}

	public TActivityRule gettActivityRule() {
		return tActivityRule;
	}

	public void settActivityRule(TActivityRule tActivityRule) {
		this.tActivityRule = tActivityRule;
	}

	@Override
	public String toString() {
		return "TActivityPackage [tActivity=" + tActivity
				+ ", tActivityRule=" + tActivityRule + "]";
	}
}
