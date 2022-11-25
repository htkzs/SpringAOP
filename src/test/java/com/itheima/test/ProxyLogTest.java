package com.itheima.test;

import com.itheima.math.MathCaculator;
import com.itheima.math.MyMathCalculator;
import com.itheima.proxy.JdkProxyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@ContextConfiguration(locations = "classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProxyLogTest {
    @Test
    public void caculateLog(){

        MyMathCalculator myMathCalculator = new MyMathCalculator();
        //注意这里使用接口去接受，如果使用具体的实现类出现转换异常 由于代理对象和被代理对象唯一能产生关系的是他们实现了共同的接口
        MathCaculator proxy = JdkProxyFactory.getProxy(myMathCalculator);
        //class com.sun.proxy.$Proxy14
        System.out.println(proxy.getClass());

        System.out.println(proxy.getClass().getSuperclass());
        //[interface com.itheima.math.MathCaculator]
        System.out.println(Arrays.asList(proxy.getClass().getInterfaces()));
        int add = proxy.add(1, 2);

    }

}
