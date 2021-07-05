import org.apache.catalina.LifecycleException;
import web.run.CustomBoot;
import web.run.MyDemo;

import java.net.MalformedURLException;

public class Demo {
    public static void main(String[] args) throws LifecycleException, MalformedURLException {
        // System.out.println("hi");
        new MyDemo().run();

    }
}
