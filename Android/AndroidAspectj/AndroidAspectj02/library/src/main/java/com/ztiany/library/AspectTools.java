package com.ztiany.library;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectTools {

    @Before("execution(* android.app.Activity.on*(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        Log.d(aThis.getClass().getSimpleName(), "" + signature.toString());
    }

}
