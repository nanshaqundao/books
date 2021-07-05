package web.run;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.naming.resources.VirtualDirContext;

import javax.servlet.ServletException;
import java.io.File;
import java.net.MalformedURLException;

public class MyDemo {
    public void run() throws LifecycleException, MalformedURLException {
        String classPath = System.getProperty("user.dir");
        // new 一个 tomcat，设置相应的ip和端口信息
        Tomcat tomcat = new Tomcat();
        tomcat.enableNaming();

        Context context = new StandardContext();
        File file = new File("D:\\github\\nanshaqundao\\books\\java\\book1\\mydemo\\example\\src\\main\\webapp\\WEB-INF\\web.xml");
        System.out.println("File Location: "+file.exists());

        context.setConfigFile(file.toURI().toURL());
        Host host = tomcat.getHost();
        host.addChild(context);

        ContextConfig config = new ContextConfig();
        context.addLifecycleListener(config);
        Connector connector = tomcat.getConnector();
        connector.setPort(8080);



        host.setName("localhost");
        host.setAppBase("mydemo");
        // Load class
        tomcat.addContext(host, "/",classPath);
        // 跑起来并等待
        tomcat.start();
        tomcat.getServer().await();
    }
}
