package web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodBoyServlet", urlPatterns = {"/goodboy"})
public class GoodBoyServlet implements Servlet {
    private transient ServletConfig servletConfig;

    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String servletName = servletConfig.getServletName();
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.print("< html>< head></ head>" + "<body> Hello from " + servletName + "</body></ html>");
    }

    @Override
    public String getServletInfo() {
        return "Good Boy Servlet";
    }

    @Override
    public void destroy() {

    }


}

