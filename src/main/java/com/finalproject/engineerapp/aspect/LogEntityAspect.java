package com.finalproject.engineerapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LogEntityAspect {

    @Before("com.finalproject.engineerapp.aspect.AopExpressions.getServiceSaveAndDeleteMethods()")
    public void logSaveNewOrDeleteEntity(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Executing @Before on method:  " + methodSignature);

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
        System.out.println("Executing @AfterReturning on method: " + method);
        System.out.println("Result: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.finalproject.engineerapp.service.*.findById(..))",
            throwing = "ex"
    )
    public void afterThrowingInvalidId(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Executing @AfterThrowing on method: " + method);
        System.out.println("Exception: " + ex);
    }

    @Around("execution(* com.finalproject.engineerapp.service.*.save(..))")
    public Object computeDurationOfSaveMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("Executing @Around on method: " + method);
        System.out.println("Method " + method + " duration: " + duration + " milliseconds");
        return result;
    }
}
