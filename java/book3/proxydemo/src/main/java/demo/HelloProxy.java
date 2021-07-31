package demo;

/**
 * 静态代理
 */
public class HelloProxy implements Hello{

    private Hello hello;

    public HelloProxy(){
        hello = new HelloImpl();
    }
    @Override
    public void say(String name) {
        before();

        hello.say(name);


        after();
    }

    private void after() {
        System.out.println("--BEFORE--");
    }

    private void before() {
        System.out.println("===AFTER===");
    }
}
