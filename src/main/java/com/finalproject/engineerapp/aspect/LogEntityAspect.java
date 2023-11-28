package com.finalproject.engineerapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class LogEntityAspect {

    @Before("com.finalproject.engineerapp.aspect.AopExpressions.getServiceSaveAndDeleteMethods()")
    public void logSaveNewOrDeleteEntity(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.finalproject.engineerapp.service.*.save(..))",
            returning = "result"
    )
    public void returnEntityAfterSave(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Method: " + method);
        System.out.println("Result: " + result);


    }
}
