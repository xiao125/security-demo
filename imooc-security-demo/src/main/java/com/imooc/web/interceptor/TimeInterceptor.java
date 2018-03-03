package com.imooc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 拦截器
  */

@Component
public class TimeInterceptor implements HandlerInterceptor {


    /**
     * 拦截之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("preHandle");
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName()); //获取拦截的类名
        System.out.println(((HandlerMethod)handler).getMethod().getName());//获取拦截的方法
         request.setAttribute("startTime",new Date().getTime());
        return true;
    }


    /**
     * preHandle方法返回true时，调用此方法
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
        Long start = (Long) request.getAttribute("startTime");

        System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
    }

    /**
     * 成功或失败都调用此方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("time interceptor 耗时:"+ (new Date().getTime() - start));
        System.out.println("ex is "+ex);

    }
}
