package com.pradata.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.pradata.app.service.JobService.*(..))")
    // for around you have to always return abject
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {
        //you can also use system.out.println also but using logger here
        long start = System.nanoTime();//using currentMillis iam getting 0 mostly so using nanotime
        Object obj = jp.proceed();
        long end = System.nanoTime();
        LOGGER.info("Time taken: "+(end-start)/1e6 +" ms");
        return obj;
    }
}
