import org.apache.catalina.LifecycleException;
import web.run.CustomBoot;

public class Demo {
    public static void main(String[] args) throws LifecycleException {
        // System.out.println("hi");
        new CustomBoot().run();

    }
}
