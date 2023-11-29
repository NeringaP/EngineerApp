package com.finalproject.engineerapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("com.finalproject.engineerapp.aspect.AopExpressions.forAppFlow()")
    public void loggingBeforeMethods(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("Executing @Before on method:  " + methodSignature);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info(arg.toString());
        }
    }

    @AfterReturning(
//            pointcut = "execution(* com.finalproject.engineerapp.service.*.save(..))",
            pointcut = "com.finalproject.engineerapp.aspect.AopExpressions.forAppFlow()",
            returning = "result"
    )
    public void loggingAfterMethods(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("Executing @AfterReturning on method: " + method);
        logger.info("Result: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.finalproject.engineerapp.service.*.findById(..))",
            throwing = "ex"
    )
    public void afterThrowingInvalidId(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        logger.warning("Executing @AfterThrowing on method: " + method);
        logger.warning("Exception: " + ex);
    }

    @Around("execution(* com.finalproject.engineerapp.service.*.save(..))")
    public Object computeDurationOfSaveMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("Executing @Around on method: " + method);
        logger.info("Method " + method + " duration: " + duration + " milliseconds = " + duration/1000 + " seconds");
        return result;
    }

    @Around("execution(* com.finalproject.engineerapp.service.*.findById(..))")
    public Object handleFindByIdException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            return result;
        } catch (Exception ex) {
            logger.warning("@Around advice: found an exception " + ex);
            throw ex;
        }

    }
}
