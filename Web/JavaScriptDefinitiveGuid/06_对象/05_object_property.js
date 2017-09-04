//========================================================================
// 对象的原型属性
//========================================================================
var p = {x: 1};
var o = {x: 2};
console.log(p.isPrototypeOf(o));//查询属性

var newP = Object.create(p);
console.log(p.isPrototypeOf(newP));
console.log(Object.getPrototypeOf(newP));
console.log(newP.constructor.prototype);//create方法创建对象时不能使用此方式获取原型属性

//========================================================================
// 类属性
//========================================================================
var a = {};
console.log(a.toString());//[object Object]
var aClass = a.toString().slice(8, -1);
console.log(aClass);

function classOf(o) {
    if(o === null) {
        return "Null";
    }
    if(o===undefined) {
        return "Undefined";
    }
    //间接的调用Function.call()方法
    return Object.prototype.toString.call(o).slice(8, -1);
}
var s = "abc";
console.log(s.toString());
console.log(classOf(s));

//========================================================================
// 可扩展性
//========================================================================
var c = {};
console.log(Object.isExtensible(c));//判断是否可扩展
Object.preventExtensions(c);//锁定对象，把对象变为不可扩展
var d = {x:1,y:2};
Object.seal(d);//封闭对象，把对象变为不可扩展，并且所有属性改为不可配置
console.log(Object.isSealed(d));//是否是封闭对象
var e = {x: 32};
Object.freeze(e);//冻结对象，把对象变为不可扩展，并且所有属性改为不可配置且位置为只读，但是setter不受影响
console.log(Object.isFrozen(e));

//创建一个封闭对象，第一个参数为属性描述对象
var f = Object.create(Object.freeze({x: 1}),{
    y:{value:3,writable:true}
});
console.log(f);
console.log(f.y);

