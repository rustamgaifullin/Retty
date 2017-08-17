package com.rm.retty.container.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Method {
    MethodType methodType();
    String path();
}
