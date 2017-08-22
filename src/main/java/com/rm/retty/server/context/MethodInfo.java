package com.rm.retty.server.context;

import java.lang.reflect.Method;

public class MethodInfo {
    private final Method method;
    private final Object object;

    public MethodInfo(Method method, Object object) {
        this.method = method;
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodInfo that = (MethodInfo) o;

        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        return object != null ? object.equals(that.object) : that.object == null;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "method=" + method +
                ", object=" + object +
                '}';
    }
}