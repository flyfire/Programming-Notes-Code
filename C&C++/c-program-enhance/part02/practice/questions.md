1. 画出字符串一级指针内存四区模型


	#include <stdio.h>
	#include <string.h>

	int main(void)
	{
		char buf[20]= "aaaa";
		char buf2[] = "bbbb"; 
		char *p1 = "111111"; 
		char *p2 = malloc(100); 
		
		strcpy(p2, "3333");

		return 0;               
	}

2. 有一个字符串开头或结尾含有n个空格 （”   abcdefgdddd    ”），欲去掉前后空格，返回一个新字符串。


	要求1：请自己定义一个接口（函数），并实现功能；
	要求2：编写测试用例。
	int trimSpace(char *inbuf, char *outbuf);  

3. 有一个字符串“1a2b3d4z”；


    要求写一个函数实现如下功能：
	功能1：把偶数位字符挑选出来，组成一个字符串1。
	功能2：把奇数位字符挑选出来，组成一个字符串2。
	功能3：把字符串1和字符串2，通过函数参数，传送给main，并打印。
	功能4：主函数能测试通过。
	int getStr1Str2(char *source, char *buf1, char *buf2);


4. 键值对（“key = value”）字符串，在开发中经常使用


	要求1：请自己定义一个接口，实现根据key获取.
	要求2：编写测试用例。
	要求3：键值对中间可能有n多空格，请去除空格

	注意：键值对字符串格式可能如下：
	"key1 = value1"
	"key2 =       value2"
	"key3  = value3"
	"key4        = value4"
	"key5   =   "
	"key6   ="
	
	int getKeyByValue(char *keyvaluebuf,  char *keybuf,  char *valuebuf, int * valuebuflen);

	int main(void)
	{
		//...
		getKeyByValude("key1 =   valude1", "key1", buf, &len);
		//...

		return 0;
	}
