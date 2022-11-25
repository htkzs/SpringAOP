package com.itheima.aspect;

import org.aspectj.lang.JoinPoint;

public class DataValidation {
    public void methodStart(JoinPoint joinPoint){
        System.out.println("目标方法的名称为"+joinPoint.getSignature().getName()+"目标方法的参数列表为"+joinPoint.getArgs());
    }
    public void methodAfter(){
        System.out.println("目标方法执行完成");
    }
    public void methodReturn(JoinPoint joinPoint,Object obj){
        System.out.println("目标方法的名称为"+joinPoint.getSignature().getName()+"目标方法的参数列表为"+joinPoint.getArgs()+"返回值为"+obj);
    }
    public void methodException(JoinPoint joinPoint,Exception e){
        System.out.println("目标方法的名称为"+joinPoint.getSignature().getName()+"目标方法的参数列表为"+joinPoint.getArgs()+"异常信息为"+e.getCause());
    }
}
