package com.itheima.test;

import com.itheima.math.MathCaculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:application.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AroundNotify {
    @Test
    public void testAroundNotify(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MathCaculator bean = (MathCaculator) context.getBean(MathCaculator.class);
        int mul = bean.mul(2, 1);
    }
}
