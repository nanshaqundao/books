package demo.springaop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class GreetingAfterAdvice implements AfterReturningAdvice {
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
}
