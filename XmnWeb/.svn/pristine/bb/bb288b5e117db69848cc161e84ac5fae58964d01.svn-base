package com.xmniao.xmn.core.util.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xmniao.xmn.core.util.AnnotationUtil;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;

public class TokenHandler extends HandlerInterceptorAdapter {

	private static final String DELIM = ",";
	private Map<String, Boolean> concurrentHashMap = new ConcurrentHashMap<String, Boolean>();
	private static final Boolean mapValue = true;
	private static final Class<RequestToken> RequestTokenClass = RequestToken.class;

	private RequestToken getRequestToken(Object handler) {
		return AnnotationUtil.getMethodAnnotation(
				((HandlerMethod) handler).getMethod(), RequestTokenClass);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try {
			if (handler != null && (handler instanceof HandlerMethod)) {
				RequestToken annotation = getRequestToken(handler);
				if (annotation != null) {
					if (annotation.removeToken()) {
						// 根据token判断是否多次提交
						boolean notRepeatedSubmission = requestTokenHandel(
								request, annotation.tokenName(), true);
						if (!notRepeatedSubmission) {
							setResponseStatu(response);
						}
						return notRepeatedSubmission;
					}
				}
			}
		} catch (Exception e) {
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		try {
			if (handler != null && (handler instanceof HandlerMethod)) {
				RequestToken annotation = getRequestToken(handler);
				if (annotation != null) {
					if (annotation.createToken()) {
						// 多个token处理
						String[] tokenNames = StringUtils.paresToArray(
								annotation.tokenName(), DELIM);
						// 将token存入modelAndView对象 并返回页面
						setTokenToView(modelAndView, tokenNames);
					} else if (annotation.removeToken()) {
						requestTokenHandel(request, annotation.tokenName(),
								false);
					}
				}
			}
		} finally {
			super.postHandle(request, response, handler, modelAndView);
		}
	}

	/**
	 * 重复提交处理
	 * 
	 * @param request
	 *            请求对象
	 * @param tokenName
	 *            token名称
	 * @param addOrRemove
	 *            true:添加token false:移除token
	 * @return
	 */
	public boolean requestTokenHandel(HttpServletRequest request,
			String tokenName, boolean addOrRemove) {
		String token = request.getParameter(tokenName);
		return (StringUtils.hasLength(token) ? (addOrRemove ? addToken(token)
				: removeToken(token)) : false);
	}

	/**
	 * token存在表示重复提交 tokne不存在添加token
	 * 
	 * @param token
	 * @return
	 */
	private boolean addToken(String token) {
		if (!concurrentHashMap.containsKey(token)) {
			concurrentHashMap.put(token, mapValue);
			return true;
		}
		return false;
	}

	/**
	 * token存在移除 tokne不存在不处理
	 * 
	 * @param token
	 * @return
	 */
	private boolean removeToken(String token) {
		return (concurrentHashMap.containsKey(token) ? concurrentHashMap
				.remove(token) : false);
	}

	/**
	 * 重复提交设置response状态码并在前台页面提示
	 * 
	 * @param response
	 */
	private void setResponseStatu(HttpServletResponse response) {
		response.setStatus(1001);
	}

	/**
	 * 存储token
	 * 
	 * @param modelAndView
	 * @param tokenNames
	 */
	private void setTokenToView(ModelAndView modelAndView, String[] tokenNames) {
		for (String name : tokenNames) {
			String value = StringUtils.getUUIDString();
			modelAndView.addObject(name, value);
		}

	}
}
