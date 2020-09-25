package com.eyes.cloud.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/7/28
 * @since 1.0
 */
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println("+++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("Execute cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void destroy() {

    }
}