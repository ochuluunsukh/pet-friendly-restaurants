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
    public void logServiceBefore() {
        logger.info("Executing services method...");
    }

    @After("execution(* edu.miu.cs.cs544.oderdene.restaurant.service.*.*(..))")
    public void logServiceAfter() {
        logger.info("Services method execution completed.");
    }

    @Around("execution(* edu.miu.cs.cs544.oderdene.restaurant.service.*.*(..))")
    public Object logServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Services method {} is about to be executed.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Services method {} execution completed.", joinPoint.getSignature().getName());
        return result;
    }

    @Before("execution(* edu.miu.cs.cs544.oderdene.restaurant.controller.*.*(..))")
    public void logControllerBefore() {
        logger.info("Executing controller method...");
    }

    @After("execution(* edu.miu.cs.cs544.oderdene.restaurant.controller.*.*(..))")
    public void logControllerAfter() {
        logger.info("Controller method execution completed.");
    }

    @Around("execution(* edu.miu.cs.cs544.oderdene.restaurant.controller.*.*(..))")
    public Object logControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Controller method {} is about to be executed.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Controller ethod {} execution completed.", joinPoint.getSignature().getName());
        return result;
    }
}
