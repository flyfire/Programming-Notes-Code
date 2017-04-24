#include "ztiany_CCallJava.h"

 void accessIntField(JNIEnv * env,  jobject jobj){
	 	 	jclass  jclass_callJava =  (*env)->GetObjectClass(env, jobj);
	 	 	jfieldID jfieldID = (*env)->GetFieldID(env,jclass_callJava, "age", "I");
	 	 	jint age = (*env)->GetIntField(env,jobj,jfieldID);
	 	 	age += 100;
	 	 	(*env)->SetIntField(env, jobj , jfieldID,age );
}

 void accessStaticField(JNIEnv * env,  jobject jobj){
	 	 	jclass  jclass_callJava =  (*env)->FindClass(env, "ztiany/CCallJava");
	 	 	jfieldID jfieldID = (*env)->GetStaticFieldID(env,jclass_callJava, "salary", "D");

	 	 	jint salary = (*env)->GetStaticDoubleField(env,jobj,jfieldID);
	 	 	salary += 100;
	 	 	(*env)->SetStaticDoubleField(env, jobj , jfieldID,salary );
}


 void accessMethod(JNIEnv * env,  jobject jobj){
	 	 	jclass  jclass_callJava =  (*env)->GetObjectClass(env, jobj);
	 	 	jmethodID methodID = (*env)->GetMethodID(env,jclass_callJava, "getName", "()Ljava/lang/String;");
	 	 	jstring name = (*env)->CallObjectMethod(env,jobj,methodID);
	 	 	char* cName = Jstring2CString(env,name);
	 	 	printf("name = %s\n",cName);
}

 void accessStaticMethod(JNIEnv * env,  jobject jobj){
	 	 	jclass  jclass_callJava =  (*env)->FindClass(env, "ztiany/CCallJava");
	 	 	jmethodID methodID = (*env)->GetStaticMethodID(env,jclass_callJava, "getPath", "()Ljava/lang/String;");
            jstring path = (*env)->CallStaticObjectMethod(env,jclass_callJava,methodID);
            char* cPath =  Jstring2CString(env, path);
            printf("path = %s\n",cPath);
}


JNIEXPORT void JNICALL Java_ztiany_CCallJava_javaCallC
  (JNIEnv *env,  jobject jobj){
        accessIntField(env,jobj);
        accessStaticField(env,jobj);
        accessMethod(env,jobj);
        accessStaticMethod(env,jobj);
 }



