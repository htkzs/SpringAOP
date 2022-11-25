package com.itheima.math;

import jdk.nashorn.internal.runtime.regexp.JdkRegExp;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
public class MyMathCalculator implements MathCaculator{
    @Override
    public int add(int i, int j) {
        return i+j;
    }

    @Override
    public int sub(int i, int j) {
        return i-j;
    }

    @Override
    public int mul(int i, int j) {
        return i*j;
    }

    @Override
    public int div(int i, int j) {
        return i/j;
    }


}
