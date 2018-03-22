#include <jni.h>

#ifndef _Included_com_ztiany_bionic_JniBridge
#define _Included_com_ztiany_bionic_JniBridge
#ifdef __cplusplus
extern "C" {
#endif

//---------------------------------------------------------------------------------------------
//                                                      Test
//---------------------------------------------------------------------------------------------

JNIEXPORT void JNICALL Java_com_ztiany_bionic_JniBridge_testJni(JNIEnv *, jobject);


//---------------------------------------------------------------------------------------------
//                                                      Pthread
//---------------------------------------------------------------------------------------------

JNIEXPORT void JNICALL Java_com_ztiany_bionic_JniBridge_nativeInit(JNIEnv *, jobject);
JNIEXPORT void JNICALL Java_com_ztiany_bionic_JniBridge_nativeFree(JNIEnv *, jobject);
JNIEXPORT void JNICALL Java_com_ztiany_bionic_JniBridge_nativeWorker(JNIEnv *, jobject, jint, jint);
JNIEXPORT void JNICALL Java_com_ztiany_bionic_JniBridge_posixThreads(JNIEnv *, jobject, jint, jint);


//---------------------------------------------------------------------------------------------
//                                                      TCP/UDP
//---------------------------------------------------------------------------------------------

JNIEXPORT void JNICALL Java_com_ztiany_bionic_tcpudp_EchoClientActivity_nativeStartTcpClient(JNIEnv *, jobject, jstring, jint, jstring);
JNIEXPORT void JNICALL Java_com_ztiany_bionic_tcpudp_EchoClientActivity_nativeStartUdpClient(JNIEnv *, jobject, jstring, jint, jstring);

JNIEXPORT void JNICALL Java_com_ztiany_bionic_tcpudp_EchoServerActivity_nativeStartTcpServer(JNIEnv *, jobject, jint);
JNIEXPORT void JNICALL Java_com_ztiany_bionic_tcpudp_EchoServerActivity_nativeStartUdpServer(JNIEnv *, jobject, jint);

JNIEXPORT void JNICALL Java_com_ztiany_bionic_tcpudp_LocalEchoActivity_nativeStartLocalServer(JNIEnv *, jobject, jstring);



#ifdef __cplusplus
}
#endif
#endif
