package demo.aspect;

import demo.springaop.Apology;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Spring + AspectJ 引入增强 DeclareParents（类似 Spring里的Introduction）
 */

@Aspect
@Component
public class GreetingAspect2 {
    @DeclareParents(value = "demo.GreetingImpl", defaultImpl = ApologyImpl.class)
    private Apology apology;
}
