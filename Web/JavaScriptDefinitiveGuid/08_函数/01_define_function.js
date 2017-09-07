//========================================================================
// 定义函数
//========================================================================

var funcA = function (a, b, c) {
    return a + b + c;
};

function funcB(a, b) {
    return a / b;
}


//创建函数对象
var funcC = new Function("var a = 10; var b = 20; return a+b;");
console.log(funcC());


//嵌套函数
function outerFunc(a) {
    function innerFunc(b) {
        b.call();
    }

    innerFunc(a);
}


//apply
var o = [1, 2, 3];
function applyFunc(a) {
    console.log("this: " + this);
    console.log(a);
}
applyFunc.apply(o, [{x: 1}]);//o此时时指定调用函数的上下文


//匿名函数
outerFunc(function () {
    console.log("I am running");
});


//定义函数立即调用
(function (a, b) {
    console.log(a + b);
})(1, 2);