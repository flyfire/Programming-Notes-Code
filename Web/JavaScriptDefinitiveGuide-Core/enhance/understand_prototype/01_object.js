//========================================================================
// 普通对象和函数对象
//========================================================================
var o1 = {};//对象直接量归根结底都是通过new Function()的方式进行创建的。
var o2 = new Object();
var o3 = new f1();

function f1() {
}
var f2 = function () {
};
var f3 = new Function('str', 'console.log(str)');

var type = typeof Object; //->function
var type = typeof Function; //->function
var type = typeof f1; //->function
var type = typeof f2; //->function
var type = typeof f3; //->function
var type = typeof o1; //->object
var type = typeof o2; //->object
var type = typeof o3; //->object
