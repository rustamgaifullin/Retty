package com.rm.retty.container;

import java.util.HashSet;
import java.util.Set;

public class Context {
    private Set<Class> classList = new HashSet<>();

    public Context addClass(Class clazz) {
        classList.add(clazz);

        return this;
    }

    public Set<Class> getClassList() {
        return classList;
    }
}
