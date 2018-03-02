package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解，相关逻辑处理类
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {


    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        //初始化执行
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

       helloService.greeting("tom");
       System.out.println(value);
        return true;
    }
}
