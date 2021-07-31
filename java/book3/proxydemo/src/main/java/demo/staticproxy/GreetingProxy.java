package demo.staticproxy;

import demo.Greeting;
import demo.GreetingImpl;

/**
 * 静态代理
 */
public class GreetingProxy implements Greeting {

    private Greeting greeting;

    public GreetingProxy(){
        greeting = new GreetingImpl();
    }
    @Override
    public void sayHello(String name) {
        before();

        greeting.sayHello(name);


        after();
    }

    private void after() {
        System.out.println("--BEFORE--");
    }

    private void before() {
        System.out.println("===AFTER===");
    }
}
