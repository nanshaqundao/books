package demo.springaop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAndAfterAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void afterReturning(Object result, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("--------------After----------");
        if (result != null) {
            System.out.println("result: " + result.getClass().getName());
        }
        if (target != null) {
            System.out.println("target: " + target.getClass().getName());
        }

    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("------Before-------" + target.getClass().getName());
    }
}
