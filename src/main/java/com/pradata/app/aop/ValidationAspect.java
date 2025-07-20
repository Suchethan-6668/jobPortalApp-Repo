package com.pradata.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.pradata.app.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp,int postId) throws Throwable {
        if(postId < 0){
            LOGGER.info("PostId is negative so need to update it");
            postId = -postId;
            LOGGER.info("The updated value of postId is: "+postId);
        }
        Object obj = jp.proceed(new Object[]{postId});

        return obj;
    }
}
