/*
 ============================================================================
 
 Author      : Ztiany
 Description : ˳���д�ļ�

 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>

#define FILE1 "H:\\codes\\C\\C-File\\file\\test1.txt"
#define FILE2 "H:\\codes\\C\\C-File\\file\\test2.txt"
#define FILE3 "H:\\codes\\C\\C-File\\file\\test3.txt"

void writeFile();
void readFile();
void formatWriteRead() ;

int main() {
    //writeFile();
    //readFile();
    formatWriteRead();
    return 0;
}

void writeFile() {
    FILE *fp = fopen(FILE1, "w");
    char c;
    printf("����#��������");
    c = (char) getchar();
    while (c != '#') {
        fputc(c, fp);
        c = (char) getchar();
    }
    fclose(fp);
    putchar('\n');
}

void readFile() {
    FILE *fp = fopen(FILE1, "r");
    FILE *fpTarget = fopen(FILE2, "w");
    if (fp == NULL || fpTarget == NULL) {
        printf("���ļ�����");
        exit(0);
    }
    char c;
    //feof��ʾ���δ�����ļ�������ʶ��
    while (!feof(fp)) {
        c = (char) fgetc(fp);
        fputc(c, fpTarget);
        putchar(c);
    }
    putchar('\n');
    fclose(fp);
    fclose(fpTarget);
}

void formatWriteRead() {
    //д�ļ�
    FILE *fp = fopen(FILE3, "w");
    fprintf(fp, "%d %f %s", 1, 5.0F, "haha");
    fclose(fp);
    //��ȡ�ļ�
    fp = fopen(FILE3, "r");
    int a;
    float b;
    char s[20];
    fscanf(fp, "%d %f %s", &a, &b, s);
    printf("%d %f %s", a, b, s);
}
