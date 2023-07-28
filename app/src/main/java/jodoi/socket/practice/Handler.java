package jodoi.socket.practice;

import java.lang.reflect.Method;

public class Handler {
    private final Object controller;
    private final Method method;

    public Handler(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }
}
