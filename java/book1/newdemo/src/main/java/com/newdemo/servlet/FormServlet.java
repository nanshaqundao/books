package com.newdemo.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "FormServlet", urlPatterns = {"/form"})
public class FormServlet extends HttpServlet {
    private static final long serialVersionUID = 54L;
    private static final String TITLE = "Order Form";

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");


        //add cookie
        response.addCookie(new Cookie("lukesha", String.valueOf(System.currentTimeMillis())));

        //add header
        response.addHeader("secretName", "goodboy");


        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>" + TITLE + "</title></head>");
        writer.println("<body><h1>" + TITLE + "</h1>");
        //add other info
        Cookie[] cookies = request.getCookies();

        if (cookies.length > 0) {
            for (Cookie c : cookies
            ) {
                writer.println("<h3>Cookie Name: " + c.getName() + "</h3>");
                writer.println("<h3>Cookie Value: " + c.getValue() + "</h3>");
            }

        }

        // query string
        writer.println("<h3>Query String Value: " + request.getQueryString() + "</h3>");
        // header names
        Enumeration<String> headerNames = request.getHeaderNames();
        writer.println("<table><tbody>");
        writer.println("<tr>");
        writer.println("<td>header names: " + "</td>");
        writer.println("<td>header value: " + "</td>");
        writer.println("</tr>");
        while (headerNames.hasMoreElements()) {
            String xx = headerNames.nextElement();
            writer.println("<tr>");
            writer.println("<td>" + xx + "</td>");
            writer.println("<td>" + request.getHeader(xx) + "</td>");
            writer.println("</tr>");
        }
        writer.println("</tbody></table>");

        // parameter names
        Enumeration<String> parameterNames = request.getParameterNames();
        writer.println("<table><tbody>");
        writer.println("<tr>");
        writer.println("<td>parameter names: " + "</td>");
        writer.println("<td>parameter value: " + "</td>");
        writer.println("</tr>");
        while (parameterNames.hasMoreElements()) {
            String xx = parameterNames.nextElement();
            writer.println("<tr>");
            writer.println("<td>" + xx + "</td>");
            writer.println("<td>" + request.getParameter(xx) + "</td>");
            writer.println("</tr>");
        }
        writer.println("</tbody></table>");
        // above are added
        writer.println("<form method='post'>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<td>Name:</td>");


        writer.println("<td><input name='name'/></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Address:</td>");
        writer.println("<td><textarea name='address' "
                + "cols='40' rows='5'></textarea></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Country:</td>");
        writer.println("<td><select name='country'>");
        writer.println("<option>United States</option>");
        writer.println("<option>Canada</option>");
        writer.println("</select></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Delivery Method:</td>");
        writer.println("<td><input type='radio' " +
                "name='deliveryMethod'"
                + " value='First Class'/>First Class");
        writer.println("<input type='radio' " +
                "name='deliveryMethod' "
                + "value='Second Class'/>Second Class</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Shipping Instructions:</td>");
        writer.println("<td><textarea name='instruction' "
                + "cols='40' rows='5'></textarea></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>&nbsp;</td>");
        writer.println("<td><textarea name='instruction' "
                + "cols='40' rows='5'></textarea></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Please send me the latest " +
                "product catalog:</td>");
        writer.println("<td><input type='checkbox' " +
                "name='catalogRequest'/></td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>&nbsp;</td>");
        writer.println("<td><input type='reset'/>" +
                "<input type='submit'/></td>");
        writer.println("</tr>");
        writer.println("</table>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>" + TITLE + "</title></head>");
        writer.println("</head>");
        writer.println("<body><h1>" + TITLE + "</h1>");


        // query string
        writer.println("<h4>Query String Value: " + request.getQueryString() + "</h4>");
        // header names
        Enumeration<String> headerNames = request.getHeaderNames();
        writer.println("<table><tbody>");
        writer.println("<tr>");
        writer.println("<td>header names: " + "</td>");
        writer.println("<td>header value: " + "</td>");
        writer.println("</tr>");
        while (headerNames.hasMoreElements()) {
            String xx = headerNames.nextElement();
            writer.println("<tr>");
            writer.println("<td>" + xx + "</td>");
            writer.println("<td>" + request.getHeader(xx) + "</td>");
            writer.println("</tr>");
        }
        writer.println("</tbody></table>");

        // parameter names
        Enumeration<String> aparameterNames = request.getParameterNames();
        writer.println("<table><tbody>");
        writer.println("<tr>");
        writer.println("<td>parameter names: " + "</td>");
        writer.println("<td>parameter value: " + "</td>");
        writer.println("</tr>");
        while (aparameterNames.hasMoreElements()) {
            String xx = aparameterNames.nextElement();
            writer.println("<tr>");
            writer.println("<td>" + xx + "</td>");
            writer.println("<td>" + request.getParameter(xx) + "</td>");
            writer.println("</tr>");
        }
        writer.println("</tbody></table>");
        // above are added

        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<td>Name:</td>");
        writer.println("<td>" + request.getParameter("name")
                + "</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Address:</td>");
        writer.println("<td>" + request.getParameter("address")
                + "</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Country:</td>");
        writer.println("<td>" + request.getParameter("country")
                + "</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Shipping Instructions:</td>");
        writer.println("<td>");
        String[] instructions = request
                .getParameterValues("instruction");
        if (instructions != null) {
            for (String instruction : instructions) {
                writer.println(instruction + "<br/>");
            }
        }
        writer.println("</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Delivery Method:</td>");
        writer.println("<td>"
                + request.getParameter("deliveryMethod")
                + "</td>");
        writer.println("</tr>");
        writer.println("<tr>");
        writer.println("<td>Catalog Request:</td>");
        writer.println("<td>");
        if (request.getParameter("catalogRequest") == null) {
            writer.println("No");
        } else {
            writer.println("Yes");
        }
        writer.println("</td>");
        writer.println("</tr>");
        writer.println("</table>");
        writer.println("<div style='border:1px solid #ddd;" +
                "margin-top:40px;font-size:90%'>");

        writer.println("Debug Info<br/>");
        Enumeration<String> parameterNames = request
                .getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            writer.println(paramName + ": ");
            String[] paramValues = request
                    .getParameterValues(paramName);
            for (String paramValue : paramValues) {
                writer.println(paramValue + "<br/>");
            }
        }
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
