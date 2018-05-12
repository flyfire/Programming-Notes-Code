/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_ztiany_jni_sample_JniBridge */

#ifndef _Included_com_ztiany_jni_sample_JniBridge
#define _Included_com_ztiany_jni_sample_JniBridge
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    stringFromC
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_ztiany_jni_sample_JniBridge_stringFromC
  (JNIEnv *, jclass);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    intFromC
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_ztiany_jni_sample_JniBridge_intFromC
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    addArray
 * Signature: ([II)[I
 */
JNIEXPORT jintArray JNICALL Java_com_ztiany_jni_sample_JniBridge_addArray
  (JNIEnv *, jobject, jintArray, jint);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    bubbleSort
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_com_ztiany_jni_sample_JniBridge_bubbleSort
  (JNIEnv *, jobject, jintArray);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    encryption
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_ztiany_jni_sample_JniBridge_encryption
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    callJava
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_ztiany_jni_sample_JniBridge_callJava
  (JNIEnv *, jobject, jstring);

/*
 * Class:     com_ztiany_jni_sample_JniBridge
 * Method:    throwError
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_ztiany_jni_sample_JniBridge_throwError
  (JNIEnv *, jobject, jstring);


#ifdef __cplusplus
}
#endif
#endif
