1. char buf[] = "abcdef";


	//下面有啥区别？
	const char *p = buf;
	char const *p = buf;
	char * const p = buf;
	const char *const *p = buf;
	

2. sizeof


    char *p1[] = {"1111", "2222", "3333"};
	sizeof(p1)/sizeof(p1[0]) = ?
	
	char *p2[10] = {"1111", "2222", "3333"};
	sizeof(p2)/sizeof(p2[0]) = ?
	
	char p3[][30] = {"1111", "2222", "3333"};
	sizeof(p3)/sizeof(p3[0]) = ?
	
	char p4[10][30] = {"1111", "2222", "3333"};
	sizeof(p4)/sizeof(p4[0]) = ?

3. 二维数组


	二维数组做函数参数，为什么不能这么写？void fun(char **buf);
	
	void fun(char **buf) 和 void fun(char buf[][30]) 有什么区别？


4. 画出三种二级指针内存模型图


	mian()
	{
		//指针数组
		char *p1[] = {"123", "456", "789"};

		//二维数组
		char p2[3][4]  = {"123", "456", "789"};

		//手工二维内存
		char **p3 = (char **)malloc(3 * sizeof(char *)); //char *array[3];

		int i = 0;
		for (i=0; i<3; i++)
		{
			p3[i] = (char *)malloc(10*sizeof(char)); //char buf[10]

			sprintf(p3[i], "%d%d%d", i, i, i);
		}
		
	}
	
5. 有字符串有以下特征（“abcd11111abcd2222abcdqqqqq”）,求写一个函数接口，输出以下结果。
	把字符串替换成（dcba11111dcba2222dcbaqqqqq），并把结果传出。
	
	
	要求：
		1. 正确实现接口和功能
		2. 编写测试用例
	/*
	src:    原字符串
	dst:    生成的或需要填充的字符串
	sub:    需要查找的子字符串
	new_sub:提换的新子字符串

	return : 0 成功
			-1 失败
	*/
	int replaceSubstr(/* in */char *src, /* out */char** dst, 
                 /* in */char *sub,  /* in */char *new_sub);

6. 有一个字符串符合以下特征（“abcdef,acccd,eeee,aaaa,e3eeee,ssss,”）


	写两个函数(API)，输出以下结果
	第一个API(第二种内存模型： 二维数组)
	1)以逗号分隔字符串，形成二维数组，并把结果传出
	2)把二维数组行数运算结果也传出
	int spitString(const char *str, char c, char buf[10][30]/*in*/, int *count);

	第二个API(第三种内存模型：动态生成二维内存)
	1)以逗号分隔字符串，形成一个二级指针。
	2)把一共拆分多少行字符串个数传出
	int spitString2(const char *str, char c, char **myp /*in*/, int *count);

	要求：
	1, 能正确表达功能的要求，定义出接口。
	2, 正确实现接口和功能.
	3, 编写正确的测试用例.


