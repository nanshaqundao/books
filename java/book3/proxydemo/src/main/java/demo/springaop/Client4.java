package demo.springaop;

import demo.Greeting;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Client4 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring.xml");
        Greeting greeting = (Greeting) context.getBean("greetingProxy");

        greeting.sayHello("罗贯中");
    }
}
