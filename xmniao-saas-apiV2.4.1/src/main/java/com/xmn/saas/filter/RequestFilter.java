package com.xmn.saas.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.xmn.saas.base.Response;
import com.xmn.saas.base.ResponseCode;

/**
 * @Description:request过滤器实现
 * @author zhouxiaojian
 * @date 2016/10/12
 */
public class RequestFilter implements Filter {



    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if (req.getParameter("appVersion") == null || req.getParameter("systemVersion") == null) {
            Response map = new Response(ResponseCode.PARAM_ERROR, "appVersion为空或systemVersion为空!");
            PrintWriter out = response.getWriter();
            out.print(JSONObject.toJSON(map));
            out.close();
        }else{
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }


}
