package com.itheima.proxy;

import com.itheima.math.MathCaculator;
import com.itheima.math.MyMathCalculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JdkProxyFactory {

    public static MathCaculator getProxy(MyMathCalculator myMathCalculator) {
        /*
         * @Author GhostGalaxy
         * @Description  ClassLoader loader, 当前类的类加载器
                         Class<?>[] interfaces, 被代理对象实现的接口
                         InvocationHandler h  用于执行被代理类的目标目标
         * @Date 18:57:42 2022/11/24
         * @Param [myMathCalculator]
         * @return void
         **/
        MathCaculator CaculateProxy = (MathCaculator) Proxy.newProxyInstance(myMathCalculator.getClass().getClassLoader(), myMathCalculator.getClass().getInterfaces(), new InvocationHandler() {
            /*
             * @Author GhostGalaxy
             * @Description     Object proxy:代理对象 主要给jdk使用
             *                  Method method 被代理对象的方法名
             *                  Object[] args 被代理对象的方法参数列表
             * @Date 18:59:35 2022/11/24
             * @Param [proxy, method, args]
             * @return java.lang.Object
             **/
            @Override
            public Object invoke(Object proxy, Method method, Object[] args){
                System.out.println("======将要执行的方法的名称为======>" + method.getName() + "======即将要执行的方法的参数为======>" + Arrays.asList(args));
                Object result = null;
                try {
                    System.out.println("目标方法执行前");
                    result = method.invoke(myMathCalculator, args);
                } catch (Exception e) {
                    System.out.println("=======方法执行发生异常，异常的类型为：========>" + e.getCause());
                } finally {
                    System.out.println("方法执行完成");
                }
                return result ;
            }
        });
        return CaculateProxy;
    }
}
