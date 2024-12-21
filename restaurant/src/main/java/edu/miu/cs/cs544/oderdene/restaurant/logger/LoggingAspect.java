package edu.miu.cs.cs544.oderdene.restaurant.logger;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* edu.miu.cs.cs544.oderdene.restaurant.service.*.*(..))")
    public void logBefore() {
        logger.info("Executing method...");
    }

    @After("execution(* edu.miu.cs.cs544.oderdene.restaurant.service.*.*(..))")
    public void logAfter() {
        logger.info("Method execution completed.");
    }

    @Around("execution(* edu.miu.cs.cs544.oderdene.restaurant.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method {} is about to be executed.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Method {} execution completed.", joinPoint.getSignature().getName());
        return result;
    }
}
