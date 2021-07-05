package web.run;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import web.servlet.FrontControllerServlet;
import web.servlet.GoodBoyServlet;
import web.servlet.MyServlet;
import web.servlet.ServletConfigDemoServlet;

public class CustomBoot {
    public void run() throws LifecycleException {
        String classPath = System.getProperty("user.dir");
        System.out.println(classPath);

        // new 一个 tomcat，设置相应的ip和端口信息
        Tomcat tomcat = new Tomcat();
        Connector connector = tomcat.getConnector();
        connector.setPort(8080);
        Host host = tomcat.getHost();
        host.setName("localhost");
        host.setAppBase("mydemo");

        // 把启动工程和class加载进去
        Context context = tomcat.addContext(host, "/", classPath);

        if (context instanceof StandardContext) {
            StandardContext standardContext = (StandardContext) context;
            standardContext.setDefaultContextXml("D:\\github\\nanshaqundao\\books\\java\\book1\\mydemo\\example\\src\\main\\webapp\\WEB-INF\\web.xml");
            // 设置Servlet


            Wrapper wrapper = tomcat.addServlet("/","GoodBoyServlet",new GoodBoyServlet());
            wrapper.addMapping("/boy");
            

            Wrapper wrapper2 = tomcat.addServlet("/", "FrontControllerServlet", new FrontControllerServlet());
            wrapper2.addMapping("/nan");

            Wrapper wrapper3 = tomcat.addServlet("/", "MyServlet", new MyServlet());
            wrapper3.addMapping("/my");

            Wrapper wrapper4 = tomcat.addServlet("/", "ServletConfigDemoServlet", new ServletConfigDemoServlet());
            wrapper4.addMapping("/servletConfigDemo");
        }

        // 跑起来并等待
        tomcat.start();
        tomcat.getServer().await();
    }
}