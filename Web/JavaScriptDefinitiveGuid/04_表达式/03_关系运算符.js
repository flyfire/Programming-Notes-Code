//========================================================================
// =与===关系运算符
//========================================================================

//两个操作符都可以比较任意类型，===成为严格相等运算符(也叫恒等于)，==是相等运算符
//===在比较时不做类型转换，与之对于的不等比较符号是 !==
//==在比较时会做类型转换，与之对于的不等比较符号是 !=

//JavaScript对象的比较是引用比较，而不是值比较，对象只和本身相等，和其他任何对象都不相等
var objA = {};
var objB = {};
console.log(objA == objB);//false

//String在进行比较时，应该选择String.localCompare()函数
console.log("abc".localeCompare("abc"));//0

//比较运算符：只有数字和字符串才能进行真正意义上的比较，其他类型都将进行类型转换。
//如果至少有一个操作数不是字符串，那么两个操作数都转换为数字进行比较
//可以说+运算符更加偏爱字符串，但是比较运算符更加偏爱数字
//比较运算符中的>=、<=中的=并不依赖于关系运算符的比较规则
console.log("11" > 3);//数字的比较
console.log("one" < 0);//数字的比较，one->NaN，始终是false

//========================================================================
// in运算符
//========================================================================
//in运算符希望左操作数是一个字符串或者可以转换为字符串，希望它的右操作数是一个对象，如果对象中有一个名为左操作数的属性名，返回true。
console.log("x" in {x: 32, y: 32});

//========================================================================
// instanceof运算符：instanceof运算符希望左操作数是一个对象，右操作数是一个类
//========================================================================
//为了了解instanceof是如何工作的，必需了解原型链(prototype chain)，原型链是JavaScript的继承机制，为了计算o instanceof f，JavaScript首先计算
//f.prototype, 然后在原型链中查找o，如果找到那么o是f(或f的父类)的一个实例。返回true，否则返回false
var d = new Date();
console.log(d instanceof Date);
console.log(d instanceof Object);//Object是所有对象的父类
console.log(d instanceof Number);//false

//========================================================================
// 逻辑表达式&& 、|| 、！
//========================================================================
//逻辑表达式操作的数据类型是boolean是很好判断，当操作的数据类型不是boolean类型时，就会根据数据是真假值进行判断
var objC = {x: 3};
var empty = null;
console.log(objC && empty);







