package com.ztiany.androidaspectj03.tools;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 演示：
 * <pre>
 *     使用Pointcut定义切入点，并且使用改切入点
 *     切入点之间的逻辑组合
 *     AfterThrowing
 *     withincode
 *     call和execution的区别：call捕获的JoinPoint是签名方法的调用点，而execution捕获的则是执行点
 * </pre>
 *
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-09-25 18:31
 */
@Aspect
public class Aspect2 {

    private static final String TAG = Aspect2.class.getSimpleName();

    ///////////////////////////////////////////////////////////////////////////
    // sample1
    ///////////////////////////////////////////////////////////////////////////

    @Around("execution(* com.ztiany.androidaspectj03.AspectTarget1.*(..)) && !runAllPoint() && !loginPoint()")
    public void onAspectTarget1MethodRun(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Log.d(TAG, "onAspectTarget1MethodRun: " + signature.toString());
        joinPoint.proceed();
    }

    //执行runAll方法的切入点，此处必须为execution
    @Pointcut("execution(* com.ztiany.androidaspectj03.AspectTarget1.runAll(..))")
    public void runAllPoint() {

    }

    @Pointcut("execution(* com.ztiany.androidaspectj03.AspectTarget1.login(..))")//执行login方法的切入点
    public void loginPoint() {

    }

    //https://stackoverflow.com/questions/11270459/aspectj-around-pointcut-all-methods-in-java
    @Around("loginPoint()")
    public Object aroundLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d(TAG, "onAspectTarget1MethodRun: " + proceedingJoinPoint.getSignature().toString());
        Object proceed = proceedingJoinPoint.proceed();
        Log.d(TAG, "proceed:" + proceed);
        return proceed;
    }


    ///////////////////////////////////////////////////////////////////////////
    // sample2：AfterThrowing
    ///////////////////////////////////////////////////////////////////////////
    /*
        AfterThrowing：异常处理
                1. AfterThrowing虽然可以捕获已异常，但是最终还是会把异常抛出，估计只能做一些统计日志和统计功能。
                2. 如果目标分发已经处理了异常，则Aspect不会处理异常
  */
    @AfterThrowing(pointcut = "execution(* *..AspectTarget2.throwError(..))", throwing = "exception")
    public void catchExceptionMethod1(Exception exception) {//这里的参数名必须和上面throwing = "exception"指定的名称一致
        Log.e(TAG, "AspectTarget2.throwError() called with: exception = [" + exception + "]");
    }


    ///////////////////////////////////////////////////////////////////////////
    //sample3：Pointcut和withincode
    ///////////////////////////////////////////////////////////////////////////
    // withincode：通常来进行一些切入点条件的过滤，作更加精确的切入控制
    // 此处切入点表示：在print1方法中执行的
    @Pointcut("withincode(* com.ztiany.androidaspectj03.AspectTarget2.print1())")
    public void withincodePrint1Point() {

    }

    // 在调用doPrint方法的时候
    @Pointcut("call(* com.ztiany.androidaspectj03.AspectTarget2.doPrint(..))")
    public void callDoPrintPoint() {

    }

    // 在print1方法中执行并且是调用doPrint的时候
    @Pointcut("withincodePrint1Point() && callDoPrintPoint()")
    public void inPrint1AndCallDoPrintPoint() {

    }

    @Around("inPrint1AndCallDoPrintPoint()")
    public void aroundPrint(ProceedingJoinPoint joinPoint) {
        Log.d(TAG, "AspectTarget2.print1 called，but not process");
    }

}
