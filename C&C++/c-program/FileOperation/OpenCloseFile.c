#include <stdio.h>
#include <stdlib.h>


#define FILE1 "H:\\codes\\C\\C-File\\file\\test1.txt"

//打开文件
FILE *openFile(char *);

//文件属于系统资源，用完需要关闭文件
//其次，由于文件缓冲区的存在，不关闭文件直接退出程序有可能造成文件的数据丢失。
void closeFile(FILE *file) ;

int main() {
    FILE *fp = openFile(FILE1);
    closeFile(fp);
    return 0;
}


//打开文件
FILE *openFile(char *fileName) {
    //fopen参数说明(文件名，打开的模式)
    FILE *fp;
    if ((fp = fopen(fileName, "r")) == NULL) {
        printf("open file error \n");
        //退出
        exit(0);
    }
    return fp;
}

//文件属于系统资源，用完需要关闭文件
//其次，由于文件缓冲区的存在，不关闭文件直接退出程序有可能造成文件的数据丢失。
void closeFile(FILE *file) {
    int result = fclose(file);
    if (result == 0) {
        printf("file close success");
    } else if (result == EOF) {
        printf("file close error");
    }
}

