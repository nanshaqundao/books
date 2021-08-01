package demo.springaop;

import demo.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 引入（类）增强
 */
public class Client5 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy2");
        greetingImpl.sayHello("我已引入类增强");

        Apology apology = (Apology) greetingImpl;
        apology.saySorry("我已引入类增强");
    }
}
