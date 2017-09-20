package com.xmniao.xmn.core.util;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 项目名称：TravelingWeb
 * 
 * 类名称：DateEditor
 * 
 * 类描述： 日期时间类型转换
 * 
 * 创建人： zhou'sheng
 * 
 * 创建时间：2014年8月25日 下午2:26:46
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */
public class DateEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.length() == 0) {
			// setValue(new Date());
			setValue(null);
		} else {
			try {
				if (text.length() == 10) {
					setValue(new SimpleDateFormat("yyyy-MM-dd").parse(text));
				} else if (text.length() == 13) {
					setValue(new Date(Long.parseLong(text)));
				} else if (text.length() == 16) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm").parseObject(text));
				} else if (text.length() == 19) {
					setValue(new SimpleDateFormat("yy-MM-dd HH:mm:ss").parseObject(text));
				} else {
					throw new IllegalArgumentException("转换日期失败: 日期长度不符合要求!");
				}
			} catch (Exception ex) {
				throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
			}
		}
	}

}
