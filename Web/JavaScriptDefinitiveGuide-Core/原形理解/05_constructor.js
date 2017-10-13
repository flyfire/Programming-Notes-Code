//========================================================================
// 构造器
//========================================================================
// var obj = {}; 与 var obj = new Object();是等效的
//新对象 obj 是使用 new 操作符后跟一个构造函数来创建的。构造函数（Object）本身就是一个函数（就是上面说的函数对象）
//构造函数（Object）与其他构造器并没有什么区别。只是它是js解释器中定义的，


// 同理，可以创建对象的构造器不仅仅有 Object，也可以是 Array，Date，Function等。
// 所以我们也可以通过构造函数来创建 Array、 Date、Function

var b = new Array();
console.log(b.constructor === Array);//-> true
console.log(b.__proto__ === Array.prototype);//-> true

var c = new Date();
console.log(c.constructor === Date);//-> true
console.log(c.__proto__ === Date.prototype);//-> true

var d = new Function();
console.log(d.constructor === Function);//-> true
console.log(d.__proto__ === Function.prototype);//-> true

console.log(Function.constructor === Function);//->true，Function的constructor是其本身

//========================================================================
// 原型链
//========================================================================
//对象之间的继承关系构成了原型链
function Child() {

}
var child = new Child();
console.log(child.constructor === Child);//-> true
console.log(Child.constructor === Function);//-> true
console.log(child.__proto__ === Child.prototype);//-> true
console.log(Child.prototype.__proto__ === Object.prototype);//-> true
//这里的原型链是：child-->Child.prototype-->Object.prototype