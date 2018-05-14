package com.ztiany.mall.utils;


import com.ztiany.mall.service.UserService;
import com.ztiany.mall.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * AOP核心思想，返回代理
 */
public class BeanFactory {

    public static UserService getUserService() {
        final UserService s = new UserServiceImpl();//原有对象
        return proxyIt(s);
    }

    @SuppressWarnings("unchecked")
    private static <T> T proxyIt(T s) {
        return (T) Proxy.newProxyInstance(s.getClass().getClassLoader(), s.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    try {
                        TransactionManager.startTransaction();
                        Object rtValue = method.invoke(s, args);
                        TransactionManager.commit();
                        return rtValue;
                    } catch (Exception e) {
                        TransactionManager.rollback();
                        throw new RuntimeException(e);
                    } finally {
                        TransactionManager.release();
                    }
                });
    }

}
