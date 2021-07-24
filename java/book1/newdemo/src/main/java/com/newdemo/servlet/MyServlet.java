package com.newdemo.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", urlPatterns = { "/my" })
public class MyServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig)
            throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public String getServletInfo() {
        return "My Servlet";
    }

    @Override
    public void service(ServletRequest request,
                        ServletResponse response) throws ServletException,
            IOException {
        String servletName = servletConfig.getServletName();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head>"
                + "<body>你好 from: " + servletName
                + " - content length: "+ request.getContentLength()
                + " - content type: " + request.getContentType()
                + " - parameters: " + request.getParameterNames()
                + " - init parameters: " + servletConfig.getInitParameterNames().toString()
                + " - protocol: " + request.getProtocol()
                + "</body></html>");
    }

    @Override
    public void destroy() {
    }
}