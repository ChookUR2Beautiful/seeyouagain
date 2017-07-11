package com.xmn.saas.filter;

import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:CookiesToken过滤器实现
 * @author zhouxiaojian
 * @date 2016/09/19
 */
public class CookiesTokenFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(CookiesTokenFilter.class);
    // 排除路径
    private String pathToBeIgnored;

    @Autowired
    private RedisService sessionTokenService;

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if (StringUtils.isNotBlank(pathToBeIgnored)) {
            String path = req.getRequestURI();//访问路径
            if(pathToBeIgnored.contains(",")){
                String [] pathToBeIgnoreds =  pathToBeIgnored.split(",");
                for(String p:pathToBeIgnoreds){
                    if(path.contains(p)){
                        chain.doFilter(request, response);
                        return;
                    }
                    
                }
            }else if(path.contains(pathToBeIgnored)){
                chain.doFilter(request, response);
                return;
            }
        }
        

        String sessiontoken = req.getParameter("sessionToken");

        if (StringUtils.isNotBlank(sessiontoken)) {
            // 从缓存中取出Session信息
            boolean flag = sessionTokenService.checkSessionCacheObject(sessiontoken);
            if (flag) {
                // 保存token到cookie
                CookieUtils.setVal("sessionToken", sessiontoken, res);
                chain.doFilter(request, response);
            } else {
                log.info("H5会话令牌错误!");
                req.getSession().setAttribute("info", "会话令牌错误!");
                req.getRequestDispatcher("/error.jsp").forward(req, res);
            }

        } else {
            String sessionToken = CookieUtils.getVal("sessionToken", req);
            if (sessionToken!= null && sessionTokenService.checkSessionCacheObject(sessionToken)) {
                chain.doFilter(request, response);
            }else{
                log.info("H5会话令牌错误!");
            	req.getSession().setAttribute("info", "会话令牌错误!");
            	req.getRequestDispatcher("/error.jsp").forward(req, res);
            }
        }

    }

    public void destroy() {

    }

    public String getPathToBeIgnored() {
        return pathToBeIgnored;
    }

    public void setPathToBeIgnored(String pathToBeIgnored) {
        this.pathToBeIgnored = pathToBeIgnored;
    }

}
