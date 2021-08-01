package demo.aspect;

import demo.GreetingImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 这里用了Tag 引用
// 只有Tag引用标注的方法会被增强
public class ADemo2 {
    public static void main(String[] args) {
        // using aspectJ can save lots of Point definition, you can compare spring.xml and spring2.xml
        ApplicationContext context = new ClassPathXmlApplicationContext("aop/demo/spring2.xml");
        GreetingImpl greetingImpl = (GreetingImpl) context.getBean("greetingImpl");
        greetingImpl.sayHello("我已引入类增强");
        greetingImpl.tagMorning("huang zhong");
    }
}
