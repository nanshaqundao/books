package demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect1 {
    /**
     * first * is return type
     * second * is matching method
     * (..) is for argument of methods, here it is any
     */
    @Around("@annotation(demo.aspect.Tag)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();
        return result;

    }

    private void after() {
        System.out.println("-------Tag-Before--------");
    }

    private void before() {
        System.out.println("========Tag-After==========");
    }
}
