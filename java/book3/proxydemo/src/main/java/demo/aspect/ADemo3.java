package demo.aspect;

import demo.Greeting;
import demo.GreetingImpl;
import demo.springaop.Apology;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring+AspectJ 引入增强
 */
public class ADemo3 {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring2.xml");
        Greeting greeting = (Greeting) context.getBean("greetingImpl");
        greeting.sayHello("Liu Bei");

        Apology apology = (Apology) greeting; //强制转换为Apology接口
        apology.saySorry("曹操");
    }
}

