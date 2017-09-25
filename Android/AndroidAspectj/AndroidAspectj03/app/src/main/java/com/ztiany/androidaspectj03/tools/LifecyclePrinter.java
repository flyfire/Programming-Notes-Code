package com.ztiany.androidaspectj03.tools;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-25 18:18
 */
@Aspect//@Aspect注解来定义这是一个AspectJ文件
public class LifecyclePrinter {

    @Before("execution(* android.app.Activity.on*(..))")
    /*
    注解说明：这其实就是一个JoinPoint。
        execution代表其方法执行的时候
        * android.app.Activity.on*(..)对应：返回值 类型.方法名(参数)，第一个星号标识返回值为任意类型、然后类型限定为android.app.Activity或其子类，方法名以on开头，参数不限
     */
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        Log.d(aThis.getClass().getSimpleName(), "onActivityMethodBefore: " + signature.toString());
    }

    @After("execution(* android.app.Activity.on*(..))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        Log.d(aThis.getClass().getSimpleName(), "onActivityMethodAfter: " + signature.toString());
    }

    @Before("execution(* android.support.v4.app.Fragment.on*(..))")
    public void onFragmentMethodBefore(JoinPoint joinPoint) throws Throwable {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        Log.d(aThis.getClass().getSimpleName(), "onFragmentMethodBefore: " + signature.toString());
    }

    @After("execution(* android.support.v4.app.Fragment.on*(..))")
    public void onFragmentMethodAfter(JoinPoint joinPoint) throws Throwable {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        Log.d(aThis.getClass().getSimpleName(), "onFragmentMethodAfter: " + signature.toString());
    }

}
