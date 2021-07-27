package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 */
public class ControllerHelper {
    /**
     * 用于存放 请求 和 处理器 的映射关系 ActionMap<Request, Handler>
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();


    static {
        //获取所有Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();

        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            //遍历Controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //获取Controller类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    //遍历该Controller类中的 方法数组
                    for (Method method : methods) {
                        //是否有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从Action注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();

                            //验证映射规则
                            //TO DO - refactor - Regular expression
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    //获取请求方法 与 请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];

                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);

                                    //存入实例化好的 请求 - 处理器对
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
