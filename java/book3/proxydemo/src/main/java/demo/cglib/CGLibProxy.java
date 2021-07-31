package demo.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance = new CGLibProxy();

    public static CGLibProxy getInstance() {
        return instance;
    }

    private CGLibProxy(){

    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("------------CGLIB Before------------");
    }

    private void after() {
        System.out.println("============CGLIB After===========");
    }

}
