package com.jecyhw.request.aspect;

import com.jecyhw.request.exception.RequestFailedException;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by jecyhw on 16-8-18.
 */
@Component
@Aspect
public class RequestFaildAspect {
    static final Logger logger = Logger.getLogger(RequestFaildAspect.class);

    @Pointcut("execution(* com.jecyhw.request.aspect.RequestFaildAspect.html())")
    public void html() {}

    @Before("html()")
    public void before() {
        logger.error("before");
    }

    /**
     * 处理Page类的html方法抛出的异常RequestFailedException
     */
    @AfterThrowing(pointcut = "html()", throwing = "rfe")
    @Order(1)
    public void afterThrowing(RequestFailedException rfe) {
        logger.error("AfterThrowing");
    }
}
