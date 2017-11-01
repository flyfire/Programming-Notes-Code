/*
 ============================================================================
 
 Author      : Ztiany
 Description :  数组

 ============================================================================
 */

int main(){
    int array1[4] = {1, 2, 4, 5};
    //c99可以指定初始化某个元素
    int days[7] = {[6] = 6};
}

void arrayTest1(const int array[]){
    
}

/*
 * 变长数组，c99之前，二维数组传参，必须指定数组的维度，c99开始可以使用变长数组，
 * 变长数组必须使用自动变量。
 */
void variableLengthArray(int rows,int cols, int ar[rows][cols]){

}