package com.rm.retty.server.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Method {
    MethodType methodType();
    String path();
}