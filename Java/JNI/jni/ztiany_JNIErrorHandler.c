#include "ztiany_JNIErrorHandler.h"


JNIEXPORT void JNICALL Java_ztiany_JNIErrorHandler_testNativeThrow
  (JNIEnv * env, jobject jobj){

        jclass class_EOF = (*env)->FindClass(env,"java/io/EOFException");
  	 	jmethodID id = (*env)->GetMethodID(env,class_EOF,"<init>", "()V");
  	 	jthrowable obj_exc = (*env)->NewObject(env,class_EOF,id);
  	 	if(JNI_TRUE){
  	 			(*env)->Throw(env,obj_exc);
  	 			return;
  	 	}

        //这是另外一种方式
//  	 	(*env)->ThrowNew(env,(*env)->FindClass(env,"java/io/EOFException"), "Unexpected end of file");

 }

JNIEXPORT void JNICALL Java_ztiany_JNIErrorHandler_testJavaThrow
  (JNIEnv * env, jobject jobj){


  }

