//对象和数组初始化表达式

var emptyArray = [];
var matrix = [[1, 2, 3], [1, 2, 3], [1, 2, 3]];
var sparseArray = [1, , , , undefined, 3];

var objP = {
    x: 2.3, y: -1.2
}

//函数表达式
var square = function (x) {
    return x * 2;
}

//属性访问表达式，包括 [] 和 .
var objQ = {x: 1, y: 2, z: {a: 3}};
console.log(objQ.x);
console.log(objQ.z.a);
console.log(objQ["x"]);


//对象创建表达式，使用new创建对象
var objR = new Object();