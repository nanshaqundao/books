package demo.springaop;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * 引入增强 - Introduction Advice
 * 相比之前的对方法的增强，这是对类的增强
 */
@Component
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements Apology {
    @Override
    public void saySorry(String name) {
        System.out.println("Sorry! " + name);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return super.invoke(invocation);
    }
}
