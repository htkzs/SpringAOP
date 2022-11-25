package com.itheima.test;

import com.itheima.log.LogUtils;
import com.itheima.math.MathCaculator;
import com.itheima.math.MyMathCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CGlibProxyTest {
    @Test
    public void caculateLog(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application.xml");
        //如果要代理的对象至少有一个父接口对象 那么就需要特别注意 在获取对象的时候如果要通过类型获取，必须是接口类型
        //MyMathCalculator bean =classPathXmlApplicationContext.getBean(MyMathCalculator.class); 这种方式将报NoSuchBeanDefinitionException: No qualifying bean of type 'com.itheima.math.MyMathCalculator' available
        MathCaculator bean = classPathXmlApplicationContext.getBean(MathCaculator.class);
        //此处由于有切面逻辑，必然产生的不是简单对象，而是代理对象，
        //class com.itheima.math.MyMathCalculator$$EnhancerBySpringCGLIB$$2b03b9be 可以发现是基于CGlib的代理方式
        System.out.println(bean.getClass());

        int add = bean.add(1, 2);

    }
}
