//typeof操作符：返回表示操作数类型的一个字符串
//注意：虽然函数是对象，但是JavaScript对其做了特殊对待，typeof 一个函数返回的是 ”function“

function func() {

}
console.log(typeof func);

//delete操作符用于删除对象属性或者数组元素
var o = {x: 1, y: 2};
delete o.x;
console.log("x" in o);//false

//void运算符是一个运算符，出现在操作数之前，操作数可以是任何类型，操作符会照常计算，但忽略计算结果并返回undefined，由于void会忽略操作数的值
//因此在操作数具有副作用的时候用void来让程序更有意义，比如：<a href="javascript:void window.open();">打开窗口</a>

