package com.rm.retty.server.context;

import com.rm.retty.server.annotations.MethodType;
import com.rm.retty.server.annotations.Rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.rm.retty.server.utils.Logger.println;

public class ContextHolder {

    private HashMap<String, MethodInfo> responderMap = new HashMap<>();

    private final Context context;

    public ContextHolder(Context context) {
        this.context = context;
    }

    public void initialize() {
        println("Initializing context holder");

        context.getClassList().forEach(clazz -> {
            try {
                Object newObject = clazz.newInstance();
                Annotation restAnnotation = clazz.getAnnotation(Rest.class);
                Method getValueMethod = restAnnotation.annotationType().getDeclaredMethod("value");
                String controllerPath = (String) getValueMethod.invoke(restAnnotation);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(com.rm.retty.server.annotations.Method.class)) {
                        Annotation methodAnnotation = method.getAnnotation(com.rm.retty.server.annotations.Method.class);
                        Method getType = methodAnnotation.annotationType().getDeclaredMethod("methodType");
                        MethodType methodType = (MethodType) getType.invoke(methodAnnotation);

                        Method getPath = methodAnnotation.annotationType().getDeclaredMethod("path");
                        String methodPath = (String) getPath.invoke(methodAnnotation);

                        MethodInfo methodInfo = new MethodInfo(method, newObject);
                        String fullPath = String.format("%s %s%s HTTP/1.1", methodType.name(), controllerPath, methodPath);
                        responderMap.put(fullPath, methodInfo);
                    }
                }

            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Could not initialize context", e);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                println("Could not find or invoke method");
            }
        });

        println("Context initialized\n");
    }

    public MethodInfo get(String path) {
        return responderMap.get(path);
    }
}