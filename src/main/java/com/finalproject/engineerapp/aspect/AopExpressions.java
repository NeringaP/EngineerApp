package com.finalproject.engineerapp.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopExpressions {

    @Pointcut("execution(public * com.finalproject.engineerapp.service.*.save(..))")
    public void getServiceSaveMethods() {}

    @Pointcut("execution(public void com.finalproject.engineerapp.service.*.delete(..))")
    public void getServiceDeleteMethods() {}

    @Pointcut("getServiceSaveMethods() || getServiceDeleteMethods()")
    public void getServiceSaveAndDeleteMethods() {}
}
