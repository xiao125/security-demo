package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;
import java.util.logging.LogRecord;

/**
 * 过滤器：只能在容器初始化时被调用一次。
 *
 *   执行顺序：过滤前-拦截前-Action处理-拦截后-过滤后
 */
@Component
public class TimeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        System.out.println("time filter start");
        long start = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("time filter 耗时:"+ (new Date().getTime() -start));
        System.out.println("time filter finish");

    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");

    }
}
