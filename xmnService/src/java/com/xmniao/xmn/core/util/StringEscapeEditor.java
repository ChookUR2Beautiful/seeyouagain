package com.xmniao.xmn.core.util;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;
/**
 * 
* 项目名称：saasService   
* 类名称：StringEscapeEditor   
* 类描述：   字符窜过滤
* 创建人：liuzhihao   
* 创建时间：2016年3月30日 下午3:33:53   
* @version    
*
 */
public class StringEscapeEditor extends PropertyEditorSupport{
	private boolean escapeHTML;
	private boolean escapeJavaScript;
	private boolean escapeSQL;
	
	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript, boolean escapeSQL) {
		super();
		this.escapeSQL = escapeSQL;
	}
	
	@Override
	public void setAsText(String text) {

		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (!"".equals(value) && !value.matches("^\\d+$") && !value.matches("^[\u4e00-\u9fa5]+$")) {
				String pattern = "select|select *|insert into|delete from|delete|create|create table|alter|declare|drop|begain|exists|drop table|update|truncate|exec|script|javascript";
				//小写关键字替换
				value = value.replaceAll(pattern, "");
				//大写关键字替换
				value = value.replaceAll(pattern.toUpperCase(), "");
				//其他敏感括号转换
				value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
				//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
				value = value.replaceAll("'", "& #39;");
				value = value.replaceAll("\"", "&#34;");
				value = value.replaceAll("&", "&#38;");
				value = value.replaceAll("eval\\((.*)\\)", "");
				value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

				// if (escapeHTML) {
				// value = StringEscapeUtils.escapeHtml(value);
				// }
				// if (escapeJavaScript) {
				// value = StringEscapeUtils.escapeJavaScript(value);
				// }
				if (escapeSQL) {
					value = StringEscapeUtils.escapeSql(value);
				}
			}

			setValue(value);
			
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	public static void main(String[] args) {
		String value = "";
		if (true) {
			value = StringEscapeUtils.escapeHtml(value);
		}
		if (true) {
			value = StringEscapeUtils.escapeJavaScript(value);
		}
		if (true) {
			value = StringEscapeUtils.escapeSql(value);
		}
		String pattern = "select|insert|delete|from|create|alter|declare|drop|if|else|table|begain|end|set|add|exists|count|drop table|update|truncate|asc|exec|or|and";
		value = value.replaceAll(pattern, "");
		System.out.println(value);
	}
}
