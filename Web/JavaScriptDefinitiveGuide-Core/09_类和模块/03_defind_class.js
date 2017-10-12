//========================================================================
// 模拟定义Java类
//========================================================================

//nodeJs模块引入
var Tools = require("../Tools");
var extend = Tools.extend;

//工厂方法，定义一个类
function defineClass(constructor, // 用以设置实例的属性的函数
                     methods,     //实例的方法，复制到原型中
                     statics)     // 类属性，复制到构造函数中
{
    if (methods) extend(constructor.prototype, methods);
    if (statics) extend(constructor, statics);
    // Return the class
    return constructor;
}

//创建一个SimpleRange类
var SimpleRange = defineClass(
    function (f, t) {
        this.f = f;
        this.t = t;
    },
    {
        includes: function (x) {
            return this.f <= x && x <= this.t;
        },
        toString: function () {
            return "(" + this.f + "..." + this.t + ")";
        }
    },
    {
        upto: function (t) {
            return new SimpleRange(0, t);
        }
    }
);

//创建对象并使用
var sr = new SimpleRange(1, 100);
console.log(sr.includes(3));//-> true
var sr2 = SimpleRange.upto(8);
console.log(sr2);