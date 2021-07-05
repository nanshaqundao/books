import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.naming.resources.VirtualDirContext;

import javax.servlet.ServletException;
import java.io.File;

public class MyDemo {
    public void run() throws LifecycleException {
        String classPath = System.getProperty("user.dir");
        System.out.println(classPath);

        // new 一个 tomcat，设置相应的ip和端口信息
        Tomcat tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
        connector.setPort(8080);
        Host host = tomcat.getHost();
        host.setName("localhost");
        host.setAppBase("my-mvc-web");

        // 把启动工程和class加载进去
        Context context = tomcat.addContext(host, "/", classPath);

        if (context instanceof StandardContext) {
            StandardContext standardContext = (StandardContext) context;
            standardContext.setDefaultContextXml("D:\\github\\nanshaqundao\\books\\java\\book1\\mydemo\\example\\src\\main\\webapp\\WEB-INF\\web.xml");
            // 设置Servlet
//            Wrapper wrapper = tomcat.addServlet("/", "FrontControllerServlet", new FrontControllerServlet());
//            wrapper.addMapping("/");
        }

        // 跑起来并等待
        tomcat.start();
        tomcat.getServer().await();
    }
}
