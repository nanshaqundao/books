package demo.staticproxy;

import demo.Greeting;

public class Demo {
    public static void main(String[] args) {
        Greeting greetingProxy = new GreetingProxy();
        greetingProxy.sayHello("jack");
    }
}
