package org.smart4j.framework;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.IocHelper;
import org.smart4j.framework.util.ClassUtil;

/**
 * 用于加载所有Helper的入口程序 - HelperLoader
 */
public class HelperLoader {
    public static void init(){
        Class<?> [] classList = {ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> cls: classList){
            ClassUtil.loadClass(cls.getName());
        }
    }
}
