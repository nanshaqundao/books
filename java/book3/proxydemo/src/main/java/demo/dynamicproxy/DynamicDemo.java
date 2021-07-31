package demo.dynamicproxy;

import demo.Greeting;
import demo.GreetingImpl;

import java.lang.reflect.Proxy;

public class DynamicDemo {
    public static void main(String[] args) {
        Greeting greeting = new GreetingImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(greeting);

        Greeting greetingProxy = (Greeting) Proxy.newProxyInstance(greeting.getClass().getClassLoader(), greeting.getClass().getInterfaces(),dynamicProxy);

        Greeting greetingProxy2 = dynamicProxy.getProxy();
        greetingProxy.sayHello("Jack");
        greetingProxy2.sayHello("Luke");
    }
}
