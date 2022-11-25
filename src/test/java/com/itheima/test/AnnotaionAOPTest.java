package com.itheima.test;

import com.itheima.math.MathCaculator;
import com.itheima.math.MyMathCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotaionAOPTest {
    @Test
    public void annotationAop(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        //注意此时被代理方法有接口的实现，这里必须使用接口类型去获取代理对象，根据类型无法获取到，因为容器中不存在该类型的Bean
        MathCaculator bean =context.getBean(MathCaculator.class);
        bean.add(2,1);
    }
}
