package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    //对于UPDATE,INSERT等操作类型，需要自动填充的字段
    OperationType value();
}
