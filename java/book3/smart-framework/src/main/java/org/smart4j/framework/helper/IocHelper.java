package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

public final class IocHelper {
    static {
        //获取所有的Bean类与Bean实例之间的映射关系
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从beanMap中获取Bean 类 与 Bean 实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //获取Bean类定义的所有成员变量 Bean Field
                // getFields好像无法返回Inject成员变量
                Field[] beanFields = beanClass.getDeclaredFields();

                //遍历以判断时候有Inject注解
                for (Field beanField : beanFields) {
                    if (beanField.isAnnotationPresent(Inject.class)) {
                        //实例化beanField
                        // getType 和 getClass区别？
                        Class<?> beanFieldClass = beanField.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if (beanFieldInstance != null) {
                            ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                        }
                    }
                }
            }
        }
    }
}
