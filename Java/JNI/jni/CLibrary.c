#include "ztiany_JavaCallC.c"
#include "ztiany_CCallJava.c"

static jstring nativeDynamicRegFromJni(JNIEnv *env, jobject obj)
{
    return (*env) -> NewStringUTF(env, "动态注册调用成功");
}

//JNINativeMethod是一个结构体，这里初始化了一个JNINativeMethod数组，正是这个，可以动态调用任意 native 方法
JNINativeMethod nativeMethod[] = {{"dynamicRegFromJni", "()Ljava/lang/String;", (void*)nativeDynamicRegFromJni}};

//此方法在jni库被加载时有JVM调用
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *env;
    if ((*jvm) -> GetEnv(jvm, (void**) &env, JNI_VERSION_1_4) != JNI_OK)
    {
        return -1;
    }

    jclass clz = (*env) -> FindClass(env, "ztiany/RegisterMethod");

    (*env) -> RegisterNatives(env, clz, nativeMethod, sizeof(nativeMethod) / sizeof(nativeMethod[0]));

    return JNI_VERSION_1_4;
}
