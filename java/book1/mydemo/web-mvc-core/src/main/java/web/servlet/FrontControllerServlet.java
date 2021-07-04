package web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FrontControllerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletName = getServletConfig().getServletName();
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head>"
                + "<body>Get Hello from " + servletName
                + "</body></html>");

    }
}
