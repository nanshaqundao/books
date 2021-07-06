package com.newdemo.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "GenericServletDemoServlet",
        urlPatterns = {"/generic"},
        initParams = {@WebInitParam(
                name = "admin",
                value = "Harry Taciak"
        ), @WebInitParam(
                name = "email",
                value = "admin@example.com"
        )}
)
public class GenericServletDemoServlet extends GenericServlet {
    private static final long serialVersionUID = 62500890L;

    public GenericServletDemoServlet() {
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
        String admin = servletConfig.getInitParameter("admin");
        String email = servletConfig.getInitParameter("email");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head><body>Admin:" + admin + "<br/>Email:" + email + "</body></html>");
    }
}

