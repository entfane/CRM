package com.example.crm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.example.crm.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.example.crm.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.example.crm.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        //display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("===>>> in @Before: calling method: " + method);
        //display the arguments to the method

        //get the arguments
        Object[] args = joinPoint.getArgs();
        //loop through and display the args
        for (Object arg : args) {

            logger.info("===>>> argument: " + arg);

        }
    }

    //add @AfterReturning advice
    @AfterReturning(

            pointcut = "forAppFlow()",
            returning = "result"

    )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        //display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        logger.info("===>>> in @AfterReturning: from method: " + method);

        //display data returned
        logger.info("===>>> result: " + result);

    }

}
