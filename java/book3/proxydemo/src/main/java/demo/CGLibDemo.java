package demo;

public class CGLibDemo {
    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("刘玄德");
    }
}
