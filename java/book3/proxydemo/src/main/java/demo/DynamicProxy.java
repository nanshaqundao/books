package demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();

        Object result = method.invoke(target, args);
        after();

        return result;
    }

    private void after() {
        System.out.println("---------Dynamic Proxy Before---------");
    }

    private void before() {
        System.out.println("---------Dynamic Proxy After---------");
    }
}
