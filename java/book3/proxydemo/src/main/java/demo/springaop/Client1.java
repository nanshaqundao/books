package demo.springaop;

import demo.Greeting;
import demo.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

public class Client1 {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GreetingImpl());
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());




        Greeting greeting = (Greeting) proxyFactory.getProxy();

        greeting.sayHello("Jack London");
    }
}
