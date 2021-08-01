package demo.springaop;

import demo.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring AOP 切面
 * 只有GreetingImpl中 good* 为方法名的方法会得到增强
 * 这里增强用的是 GreetingAroundAdvice
 */
public class Client6 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingProxy3");
        greetingImpl.sayHello("我已引入类增强");
        greetingImpl.goodMorning("guan yu");
    }
}
