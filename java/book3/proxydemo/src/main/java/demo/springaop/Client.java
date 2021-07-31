package demo.springaop;

import demo.Greeting;
import demo.GreetingImpl;
import org.springframework.aop.framework.ProxyFactory;

public class Client {
    public static void main(String[] args) {
        //创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();

        //射入目标类对象
        proxyFactory.setTarget(new GreetingImpl());

        //添加前置和后置曾倩
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();

        greeting.sayHello("Mark Twin");
    }
}
