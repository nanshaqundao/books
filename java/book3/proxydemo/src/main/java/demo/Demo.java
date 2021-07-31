package demo;

public class Demo {
    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("jack");
    }
}
