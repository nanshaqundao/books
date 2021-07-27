package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//有了ClassUtil, ClassHelper 可以获取需要加载的类
//但是没法实例化，这里用反射来实例化
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     */

    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            LOGGER.error("new instance failure for InvocationTargetException", e);
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            LOGGER.error("new instance failure for InstantiationException", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            LOGGER.error("new instance failure for IllegalAccessException", e);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("new instance failure for NoSuchMethodException", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            LOGGER.error("invoke method failure for InvocationTargetException", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            LOGGER.error("invoke method failure for IllegalAccessException", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 设置成员变量值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
