package com.example.demo.app01.s03;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {
                                "com.example.demo.app01.s03.*Dao",
                                "com.example.demo.app01.s03.*Service"
                        }
                )
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {
                                org.springframework.stereotype.Controller.class
                        }
                )
        }
)
public class SequenceConfiguration {
}
