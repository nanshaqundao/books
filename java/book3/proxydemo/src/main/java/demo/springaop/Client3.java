package demo.springaop;

import demo.Greeting;
import demo.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

public class Client3 {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingAroundAdvice());




        Greeting greeting = (Greeting) proxyFactory.getProxy();

        greeting.sayHello("Charles Dickens");
    }
}
