package demo;

import java.lang.reflect.Proxy;

public class DynamicDemo {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(),dynamicProxy);

        Hello helloProxy2 = dynamicProxy.getProxy();
        helloProxy.say("Jack");
        helloProxy2.say("Luke");
    }
}
