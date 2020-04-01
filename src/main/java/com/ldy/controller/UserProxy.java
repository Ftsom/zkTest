package com.ldy.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserProxy implements InvocationHandler {
    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("begin-----");
        Object result = method.invoke(target, args);
        System.out.println("end-----");
        return result;
    }
}
