//========================================================================
// 函数的属性、方法和构造函数
//========================================================================

//length
function testLength(a, b, c) {

}
var length = testLength.length;
console.log(length);

//========================================================================
// apply方法和call方法
//========================================================================
var o = [1, 2, 3];
function applyFunc(a, b) {
    console.log("this: " + this);// o
    console.log(a);// {x: 1}
    console.log(b);// {y:2}]
}
applyFunc.apply(o, [{x: 1}, {y: 2}]);//o此时时指定调用函数的上下文
applyFunc.call(o, {x: 1}, {y: 2});//o此时时指定调用函数的上下文


//========================================================================
// bind方法
//========================================================================
function f(y) {
    return this.x + y;
}
var o = {x: 1};
var g = f.bind(o);
console.log(g(1));//2


var sum = function (x, y) {
    return x + y;
}
var succ = sum.bind(null, 1);
console.log(succ(2));//3
function f(y, z) {
    return this.x + y + z;
}
var g = f.bind({x: 1}, 2);//2科里化了y
console.log(g(3));//6

//es3版本模拟bind
if (!Function.prototype.bind) {
    Function.prototype.bind = function (o/*, args*/) {
        var self = this;
        var boundArgs = arguments;
        return function () {
            var args = [], i;
            for (i = 1; i < boundArgs.length; i++) {
                args.push(boundArgs[i]);
            }
            for (i = 0; i < arguments.length; i++) {
                args.push(arguments[i]);
            }
            return self.apply(o, args);
        }
    }
}

Function.prototype.mockBind = function (o/*, args*/) {
    var self = this;
    var boundArgs = arguments;
    return function () {
        var args = [], i;
        for (i = 1; i < boundArgs.length; i++) {
            args.push(boundArgs[i]);
        }
        for (i = 0; i < arguments.length; i++) {
            args.push(arguments[i]);
        }
        return self.apply(o, args);
    }
};

function testMockBind(a) {
    console.log(a);
    console.log(this);//{ x: 1, y: 2, z: 2 }
    return this.x + this.y + a;
}
var newFunction1 = testMockBind.mockBind({x: 1, y: 2, z: 2}, 15);//上面a=15
var newFunction2 = testMockBind.mockBind({x: 1, y: 2, z: 2});//上面a=12
console.log(newFunction1(12));//18
console.log(newFunction2(12));//15

