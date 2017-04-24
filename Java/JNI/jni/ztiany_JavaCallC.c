#include "ztiany_JavaCallC.h"
#include "Utils.c"
#include <stdio.h>
#include <strings.h>

//打印字符串
JNIEXPORT void JNICALL Java_ztiany_JavaCallC_helloC (JNIEnv * env, jclass jcls){
                  printf("hello from c\n");
  }

//返回字符串
JNIEXPORT jstring JNICALL Java_ztiany_JavaCallC_stringFromC
  (JNIEnv * env, jclass jcls){
            char* str = "hello Java";
            return (*env)->NewStringUTF(env,str);
  }

//传参与返回
JNIEXPORT jint JNICALL Java_ztiany_JavaCallC_login
  (JNIEnv * env, jobject jobj, jint jintCard, jint jintPassword){
            if(jintCard == 2017 && jintPassword == 123456){
                    return 1;
            }
            return 0;
  }

//修改数组
JNIEXPORT jintArray JNICALL Java_ztiany_JavaCallC_add
  (JNIEnv * env, jobject jobj, jintArray jintArr, jint jintAdd){
                        //获取数组的指针
                      jint* arr = (*env)->GetIntArrayElements(env,jintArr,0);
                      //获取数组的长度
                      int size = (*env)->GetArrayLength(env,jintArr);
                      printf("array length = %d\n",size);
                      printf("add = %d\n",jintAdd);
                      //修改和打印元素
                      int len = 0;
                      for(;len<size;len++){
                       printf("element at %d = %d   ",len,arr[len]);
                            *(arr+len) = jintAdd + arr[len];
                      }
                      (*env)->ReleaseIntArrayElements(env,jintArr,arr,0);
                        return jintArr;
  }

//冒泡排序
JNIEXPORT void JNICALL Java_ztiany_JavaCallC_cSort
  (JNIEnv * env, jobject jobj, jintArray jintArr){

                        	//获取数组指针
                        	jint* jintArrPointer =(*env)->GetIntArrayElements(env,jintArr,0);//java int数组 转 c int数组
                        	//获取数组长度
                        	int len = (*env)->GetArrayLength(env,jintArr);//获取字符穿的长度
                        	int x ;
                        	int y ;
                        	for(x=0;x<len-1;x++){
                        		for(y=0;y<len-x-1;y++){
                        			if(jintArrPointer[y]>jintArrPointer[y+1]){
                        				int temp = jintArrPointer[y];
                        				jintArrPointer[y] = jintArrPointer[y+1];
                        				jintArrPointer[y+1] = temp;
                        			}
                        		}
                        	}
                        	(*env)->ReleaseIntArrayElements(env,jintArr,jintArrPointer,0);
  }


//操作字符串
JNIEXPORT jstring JNICALL Java_ztiany_JavaCallC_encryption
  (JNIEnv * env,  jobject jobj, jstring jstr){
            char* cArr = Jstring2CString(env,jstr);
            const char* cHello = "hello";
            strcat(cArr, cHello);
            return (*env)->NewStringUTF(env,cArr);
}



