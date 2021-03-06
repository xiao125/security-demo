package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))") //切入点
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{

        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg :args){
            System.out.println("arg is" +arg);
        }

        Object object = pjp.proceed(); //获取Request对象

        long start = new Date().getTime();
        System.out.println("time aspect 耗时:"+(new Date().getTime() - start));
        System.out.println("time aspect end");

        return  object;

    }

}
