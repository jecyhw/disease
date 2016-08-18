package com.jecyhw.request.aspect;

import com.jecyhw.request.exception.RequestFailedException;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;


/**
 * Created by jecyhw on 16-8-18.
 */
@Aspect
public class RequestFaildAspect {
    static final Logger logger = Logger.getLogger(RequestFaildAspect.class);

    /**
     * 处理Page类的html方法抛出的异常RequestFailedException
     * @param rfe
     */
    @AfterThrowing(pointcut = "execution(* com.jecyhw.request.Page.html())", throwing = "rfe")
    public void exception(RequestFailedException rfe) {
        logger.error(rfe.toString());
    }
}
