package com.myq.draft.anotation;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试
 */
public class InitDemoTest {

    /**
         * 注解是一种标识，是为了告诉编译器，框架，之类的，某种信息
     */
    @InitMethod
    public void init() {
        System.out.println("hello world");
    }

    @Test
    public void test1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("begin");

        // do reflect to find annotated method, and do something
        Class cls = Class.forName("com.myq.draft.anotation.InitDemoTest");
        Method[] methods = cls.getMethods();
        if (methods != null) {
            for (Method method : methods) {
                // if the method is annotated by the annotation "InitMethod"
                boolean isInitMethod = method.isAnnotationPresent(InitMethod.class);
                if (isInitMethod) {
                    // if found , do something as the annotation needed;
                    method.invoke(getClass().getConstructor(null).newInstance(null), null);
                }
            }
        }
    }

}
