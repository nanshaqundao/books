package org.smart4j.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 切面注解
     */
    Class<? extends Annotation> value();
}
