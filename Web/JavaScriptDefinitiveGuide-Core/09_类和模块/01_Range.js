//========================================================================
//
//========================================================================

var Tools = require('../Tools');//node引入模块语法


// 工厂方法，返回一个Range实例
function range(from, to) {
    var r = Tools.inherit(range.methods);
    r.from = from;
    r.to = to;
    return r;
}

//range的所有实例都继承这些方法，methods将在inherit方法中作为新对象的原形
range.methods = {
    includes: function (x) {
        return this.from <= x && x <= this.to;
    },
    foreach: function (f) {
        for (var x = Math.ceil(this.from); x <= this.to; x++) f(x);
    },
    toString: function () {
        return "(" + this.from + "..." + this.to + ")";
    }
};

var r = range(1, 3);
r.includes(2);
r.foreach(console.log);
console.log(r);
