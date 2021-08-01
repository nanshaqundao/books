package demo;

import demo.aspect.Tag;
import org.springframework.stereotype.Component;

@Component
public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("hello, " + name);
        //下面的 抛出异常 是用来验证GreetingThrowAdvice的，正常运行需要注释掉
        //throw new RuntimeException("Error");
    }

    public void goodMorning(String name) {
        System.out.println("Good Morning, " + name);
    }

    public void goodNight(String name) {
        System.out.println("Good Night, " + name);
    }

    @Tag
    public void tagMorning(String name){
        System.out.println("Tag Good Morning, "+ name);
    }
}
