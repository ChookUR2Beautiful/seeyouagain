package com.xmniao.xmn.core.util.filters.encodingFilter;

import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.xmniao.xmn.core.util.StringUtils;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：GetHttpServletRequestWrapper
 * 
 * 类描述：get请求编码设置
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2015-02-10 10:00:17
 * 
 */
public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private String encoding = "UTF-8";
	
	private Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");

	public GetHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public GetHttpServletRequestWrapper(HttpServletRequest request,
			String encoding) {
		super(request);
		this.encoding = encoding;
	}

	/**
	 * 重写HttpServletRequest getParameter() 转换成指定编码
	 * 
	 * @param name
	 * @return
	 */
	@Override
	public String getParameter(String name) {
		String parame = super.getParameter(name);
		if (StringUtils.hasLength(parame)) {
			parame = encodingConvert(parame);
		}
		return parame;
	}

	/**
	 * 重写HttpServletRequest getParameterMap() 转换成指定编码
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameterMap =  super.getParameterMap();
		for(String[] values : parameterMap.values()){
			encodingConvert(values);
		}
		return parameterMap;
	}
	
	/**
	 * 重写HttpServletRequest getParameterValues() 转换成指定编码
	 */
	@Override
	public String[] getParameterValues(String name) {
		return encodingConvert(super.getParameterValues(name));
	}

	/**
	 * 将数组中元素的字符编码转换成指定的字符编码
	 * 
	 * @param values
	 * @return
	 */
	private String[] encodingConvert(String[] values) {
		if (StringUtils.hasLength(values)) {
			for (int i = 0; i < values.length; i++) {
				values[i] = encodingConvert(values[i]);
			}
		}
		return values;
	}
	
	/**
	 * 转换编码
	 * @param param
	 * @return
	 */
	@SuppressWarnings("finally")
	private String encodingConvert(String value) {
		try {
			
			if( !existenceOfChinese(value)){
				value = new String(value.getBytes("ISO-8859-1"), encoding);
			}
		} finally{
			return value;
		}
	}
	
	/**
	 * 是否包含中文
	 * @param value
	 * @return
	 */
	private boolean existenceOfChinese(String value){
		return p.matcher(value).find();
	}

}
