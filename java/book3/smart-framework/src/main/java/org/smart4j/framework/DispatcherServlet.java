package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) {
        //初始化相关Helper类
        HelperLoader.init();

        //获取ServletContext对象，用于注册servlet
        ServletContext servletContext = servletConfig.getServletContext();

        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

        //初始化文件上传
        UploadHelper.init(servletContext);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求与请求路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        if (requestPath.equals("/favicon.ico")) {
            return;
        }

        //获取Action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            //获取Controller类机器Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            // 创建请求参数对象
            // 以下注释部分为迭代之前的代码
            Param param;
            if (UploadHelper.isMultipart(request)) {
                //文件请求
                param = UploadHelper.createParam(request);
            } else {
                param = RequestHelper.createParam(request);
            }

//            // 创建请求参数对象
//            Map<String, Object> paramMap = new HashMap<>();
//            Enumeration<String> paramNames = request.getParameterNames();
//            while (paramNames.hasMoreElements()) {
//                String paramName = paramNames.nextElement();
//                //TO DO - 这里有个问题，可能出现重复参数但是值不同 有个getParameterValues
//                String paramValue = request.getParameter(paramName);
//                paramMap.put(paramName, paramValue);
//            }
//            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
//            if (StringUtil.isNotEmpty(body)) {
//                String[] params = StringUtil.splitString(body, "&");
//                if (ArrayUtil.isNotEmpty(params)) {
//                    for (String param : params) {
//                        String[] array = StringUtil.splitString(param, "=");
//                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
//                            String paramName = array[0];
//                            String paramValue = array[1];
//                            paramMap.put(paramName, paramValue);
//                        }
//                    }
//                }
//            }
//
//            // to do -
//            // refactor after Param changed
//            Param param = new Param(paramMap);

            //调用Action方法
            //优化Action参数
            //当 参数为空时，直接不去调用含参方法
            Method actionMethod = handler.getActionMethod();
            Object result;
            if (param.isEmpty()) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }
            //Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);


            //处理Action方法返回值
            if (result instanceof View) {
                handleViewResult((View) result, request, response);
            } else if (result instanceof Data) {
                handleDataResult((Data) result, response);
            }
        }

    }

    private void handleDataResult(Data result, HttpServletResponse response) throws IOException {
        //返回JSON数据
        Data data = result;
        Object model = data.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }

    private void handleViewResult(View result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //返回JSP页面
        View view = result;
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                //这个就没看懂，就是else分支下的redirect
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }
}
