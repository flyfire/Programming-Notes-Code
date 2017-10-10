//========================================================================
// 鸭子辩型编程思想
//========================================================================
//各种判断对象类的方法都有那样这样的问题，如果使用鸭子辩型编程思想则可以规避这样的问题，**不要关注对象的类是什么，而是关注对象能做什么**。

// 如果对象o返回了参数中声明的函数就返回true
function quacks(o /*, ... */) {
    for (var i = 1; i < arguments.length; i++) {  //注意从arguments的第2个参数遍历
        var arg = arguments[i];
        switch (typeof arg) {
            case 'string':       // 如果是字符串，就检测o中是否有这个函数
                if (typeof o[arg] !== "function") return false;
                continue;
            case 'function':
                // 如果是函数就是用它的prototype属性代替
                arg = arg.prototype;
            // 会进入下面的case语句，并在此运行arg的typeof值
            case 'object':       // object: check for matching methods
                for (var m in arg) { // For each property of the object
                    if (typeof arg[m] !== "function") continue; // skip non-methods
                    if (typeof o[m] !== "function") return false;
                }
        }
    }
    //运行到这里则说明o对象实现了所有的函数
    return true;
}

var obj = {
    m1: function () {

    }
};
var methods = {
    m1: function (x) {
        return x * x;
    }
};
var add = function (x, y) {
    return x + y;
};

var result1 = quacks(obj, add);
var result2 = quacks(obj, methods);
console.log(result1);//->true，感觉这是bugs么？
console.log(result2);//->false