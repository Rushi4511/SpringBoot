package com.AOP.AspectOrientedProgramming.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.AOP.AspectOrientedProgramming.service.impl.ShipmentServiceImpl.*(..))")
    public void beforeShipmentServiceMethods(JoinPoint joinPoint){
        log.info("Before method call:{}",joinPoint.getSignature());
    }
}
