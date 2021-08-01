package demo.aspect;

import demo.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ADemo1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring2.xml");
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingImpl");
        greetingImpl.sayHello("我已引入类增强");
        greetingImpl.goodMorning("ma chao");
    }
}
