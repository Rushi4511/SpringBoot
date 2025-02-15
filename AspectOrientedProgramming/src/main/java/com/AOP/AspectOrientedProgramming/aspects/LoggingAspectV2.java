package com.AOP.AspectOrientedProgramming.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {


    @Pointcut("execution(* com.AOP.AspectOrientedProgramming.service.impl.*.*(..))")
    public void allServiceMethodsPointCut(){

    }

    @Before("allServiceMethodsPointCut()")
    public void beforeServiceMethodsCalls(JoinPoint joinPoint){
        log.info("Before advice method call:{}",joinPoint.getSignature());
    }

    /*@After("allServiceMethodsPointCut()")
    public void afterServiceMethodsCalls(JoinPoint joinPoint){
        log.info("After advice method call:{}",joinPoint.getSignature());
    }*/

    @AfterReturning("allServiceMethodsPointCut()")
    public void afterReturningServiceMethodsCalls(JoinPoint joinPoint){
        log.info("After Returning advice method call:{}",joinPoint.getSignature());
    }

    @AfterThrowing(value = "allServiceMethodsPointCut()")
    public void afterThrowingServiceMethodsCalls(JoinPoint joinPoint){
        log.info("After Throwing advice method call:{}",joinPoint.getSignature());
    }




    @Around("allServiceMethodsPointCut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Long startTime = System.currentTimeMillis();
        Object returnedValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long diff =endTime-startTime;
        log.info("Time taken {} is {} ",proceedingJoinPoint.getSignature(),diff);
        return returnedValue;
    }

}
