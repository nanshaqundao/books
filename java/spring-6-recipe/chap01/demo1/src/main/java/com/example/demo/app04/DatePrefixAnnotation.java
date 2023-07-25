package com.example.demo.app04;
import jakarta.inject.Qualifier;
import java.lang.annotation.*;

@Qualifier
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DatePrefixAnnotation { }
