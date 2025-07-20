package com.pradata.app.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    //inside the execution we mention return type class name (whole path).method name and then arguments

    //Before advice
    @Before("execution(* com.pradata.app.service.JobService.*(..))")
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("The method called is "+ jp.getSignature().getName());
    }

    //After advice
    @After("execution(* com.pradata.app.service.JobService.*(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("The method executed is "+ jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.pradata.app.service.JobService.*(..))")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("The method crashed (ie having some issues) is "+ jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.pradata.app.service.JobService.*(..))")
    public void logMethodSuccessfulExecution(JoinPoint jp){
        LOGGER.info("The method successfully executed is "+ jp.getSignature().getName());
    }



}
