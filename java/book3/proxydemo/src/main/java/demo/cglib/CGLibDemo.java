package demo.cglib;

import demo.Greeting;
import demo.GreetingImpl;

public class CGLibDemo {
    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
        Greeting greetingProxy = CGLibProxy.getInstance().getProxy(GreetingImpl.class);
        greetingProxy.sayHello("刘玄德");
    }
}
