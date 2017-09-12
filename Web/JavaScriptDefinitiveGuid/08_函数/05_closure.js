//========================================================================
// 闭包
//========================================================================

var scope = "global scope";
function checkScope() {
    var scope = "local scope";

    function f() {
        return scope;
    }

    return f();
}

var result = checkScope();
console.log(result);

//========================================================================
// 闭包的实现
//========================================================================
/*
 如果一个函数的局部变量是定义在cpu的栈中，那么当函数返回时它们就不存在了。
 但是js的采用作用域链，每一次函数调用都为之创建一个新的对象来保存局部变量，
 把这个对象添加到作用域链中。当函数返回就从作用域链中删除这个绑定变量的对象。
 如果不存在嵌套函数，也没有其他引用指向这个绑定的对象，它就会被当作垃圾回收掉，
 如果定义了嵌套函数，每个嵌套的函数都有各自对应的作用域链，并且这个作用域链指向
 一个变量绑定对象，但如果这些嵌套函数在外部函数中保存下来，那么它们也会和所指向
 的变量绑定对象一样当作垃圾被回收，当如果这个函数定义了嵌套函数，并将它作为返回值
 返回，或者存储在某处的属性里，这时就会有一个外部引用指向这个嵌套的函数，他就不会
 被当作垃圾回收，并且它所指向的变量绑定对象也不会当作垃圾回收。
 */
function checkScope2() {
    var scope = "local scope";

    function f() {
        return scope;
    }

    return f;
}

var result = checkScope2();
console.log(result());


//========================================================================
// 实例
//========================================================================

var uniqueInteger = (function () {
    var counter = 0;
    return function () {
        return counter++;
    }
}());
console.log(uniqueInteger());//0
console.log(uniqueInteger());//1

function counter() {
    var n = 0;
    return {
        count: function () {
            return n++;
        },
        reset: function () {
            n = 0;
        }
    }
}
var c = counter();
console.log(c.count());//0
console.log(c.count());//1

//从技术角度来讲，可以将闭包合并为属性存储器方法getter和setter
function counter2(n) {
    return {
        get count(){
            return n++;
        },
        set count(value){
            if(value>=n) {
                n = value;
            }else {
                throw Error("count can only be set to a larger value");
            }
        }
    }
}

var c = counter2(1000);
console.log(c.count);
console.log(c.count);


//========================================================================
// 闭包访问this和arguments
//========================================================================
/*
1，闭包在外部函数里无法访问this，除非外部函数将this转存为一个变量：var self = this;
2，闭包有自己的argument参数，所以闭包用样无法直接访问外部函数的参数数组

 */