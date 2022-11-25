package com.itheima.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
//定义该类为一个切面资源类
@Aspect
public class LogUtils {
    /*
     * @Author GhostGalaxy
     * @Description 可重用的切入点表达式
     * @Date 21:46:39 2022/11/24
     * @Param []
     * @return void
     **/
    @Pointcut("execution(public int com.itheima.math.MyMathCalculator.add(int,int))")
    public void myPoint(){

    }

    @Before(value="myPoint()")
    public void methodStart(JoinPoint joinPoint){
        //得到所有的参数列表
        Object[] args = joinPoint.getArgs();
        //方法的名字
        String name = joinPoint.getSignature().getName();
        //interface com.itheima.math.MathCaculator
        Class declaringType = joinPoint.getSignature().getDeclaringType();
        System.out.println(declaringType);
        //com.itheima.math.MathCaculator
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        System.out.println(declaringTypeName);
        //1025
        int modifiers = joinPoint.getSignature().getModifiers();
        System.out.println(modifiers);


        System.out.println("目标方法执行前"+"参数列表为"+args+"方法的名字为"+name+"返回值的类型为"+declaringType);
    }
    @After(value = "execution(public int com.itheima.math.MyMathCalculator.add(int,int))" )
    public void methodRunning(JoinPoint joinPoint){
        System.out.println("目标方法执行中");
    }
    /*
     * @Author GhostGalaxy
     * @Description 通知方法是spring通过反射调用，参数表上的每个参数都需要按顺序来 JoinPoint joinPoint，自己写的参数一定要告诉Spring
     * 通过throwing指定用那个变量接收异常
     * @Date 21:35:40 2022/11/24
     * @Param [joinPoint, e]
     * @return void
     **/
    @AfterThrowing(value = "execution(public int com.itheima.math.MyMathCalculator.add(int,int))",throwing = "e")
    public void methodException(JoinPoint joinPoint,Exception e){
        System.out.println("目标方法执行发生异常");
    }

    @AfterReturning(value = "execution(public int com.itheima.math.MyMathCalculator.add(int,int))",returning = "object")
    public void methodEnd(JoinPoint joinPoint,Object object){
        System.out.println("目标方法执行结束");
    }
    /*
     * @Author GhostGalaxy
     * @Description 环绕通知由于joinPoint.proceed();这个方法执行后，其它普通通知才能感知到，所以环绕通知的优先级要普通通知。
     * @Date 22:24:48 2022/11/24
     * @Param [joinPoint]
     * @return java.lang.Object
     **/
    @Around(value = "execution(public int com.itheima.math.MyMathCalculator.mul(int,int))")
    public Object around(ProceedingJoinPoint joinPoint){
        Object proceed =null;
        try {
            //此方法相当于执行method.invoke()
            //proceed()之前相当于前置通知 @Before
            Object[] args = joinPoint.getArgs();
            String name = joinPoint.getSignature().getName();
            System.out.println("前置通知执行，目标方法是"+name+"参数包含"+args);
            proceed = joinPoint.proceed();

            //proceed()之后相当于返回通知 @AfterRunning
            System.out.println("后置通知执行，目标方法是"+name+"参数包含"+args+"方法的返回值为："+proceed);
        } catch (Throwable throwable) {
            //异常通知 @AfterThrowing
            throwable.printStackTrace();
            System.out.println("异常通知执行，异常信息为："+throwable.getCause());
        }finally {

            //后置通知
            System.out.println("后置通知执行");
        }
        //反射调用方法后的返回值 @After
        return proceed;
    }
}
