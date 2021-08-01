package demo.aspect;


import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class GreetingAspect {
    @Around("execution(* demo.GreetingImpl.good*(..))")

    /**
     * first * is return type
     * second * is matching method
     * (..) is for argument of methods, here it is any
     */
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        before();
        Object result = pjp.proceed();
        after();
        return result;

    }

    private void after() {
        System.out.println("-------AspectJ-Before--------");
    }

    private void before() {
        System.out.println("========AspectJ-After==========");
    }
}
