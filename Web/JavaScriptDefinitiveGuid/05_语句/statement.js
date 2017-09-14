//========================================================================
// debugger语句用于添加断点，"use strict"用于启动严格模式，在MCMAScript5中引入
//========================================================================


//========================================================================
// switch语句的条件判断使用的是 ===
//========================================================================
function testSwitch(x) {
    switch (x) {
        case 1:
            console.log("不满意");
            break;
        case 2:
            console.log("满意");
            break;
        default:
            console.log("不是标准答案");
            break;
    }
}
testSwitch("A");

//========================================================================
// for in语句，用于遍历可枚举的属性
//========================================================================
var obj = {a: 1, b: 2, c: 3};
var value;
for (value in  obj) {
    console.log(value);
}

var arr = [], i = 0;
for (arr[i++] in obj);//用于复制obj中的数据到arr
console.log(arr);

//========================================================================
// with语句用于临时扩展作用域链，但不建议使用
//========================================================================
var values = {a: 1, b: 2, c: 3};
with (values) {
    console.log(a);
    console.log(b);
    console.log(c);
}


//========================================================================
// 异常处理语句
//========================================================================
try {
    var a = undefined;
    a.toString();
} catch (e) {
    console.log(typeof e);
    console.log(e);
} finally {
    console.log("error");
}

//========================================================================
// throw可以抛出一个异常
//========================================================================
throw "抛出字符串异常";
throw 1;
throw new Error("发生错误了");

