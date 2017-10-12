//========================================================================
// 原型属性
//========================================================================

/*
 在 JavaScript 中，每当定义一个对象（函数也是对象）时候，对象中都会包含一些预定义的属性。
 其中每个函数对象都有一个prototype 属性，这个属性指向函数的原型对象。
 */

function Person() {
}
Person.prototype.name = 'Jordon';
Person.prototype.age = 28;
Person.prototype.job = 'Software Engineer';

Person.prototype.sayName = function () {
    console.log(this.name);
};

var person1 = new Person();
person1.sayName(); //->Jordon

var person2 = new Person();
person2.sayName(); //->Jordon

var isSame = (person1.sayName === person2.sayName); //->true
//所以：每个对象都有 __proto__ 属性，但只有函数对象才有 prototype 属性


//========================================================================
// 什么是原型对象？
//========================================================================

//原型对象其实也是一个普通的对象。
var A = Person.prototype;

//这里的原型对象就是Person.prototype，Person.prototype其实它还有一个默认的属性：constructor

//在默认情况下，所有的原型对象都会自动获得一个 constructor（构造函数）属性，这个属性（是一个指针）指向 prototype 属性所在的函数
//上面的结论可以理解为：A 有一个默认的 constructor 属性，这个属性是一个指针，指向 Person。

console.log(Person.prototype.constructor === Person);//->true
console.log(person1.constructor === Person);//->true
console.log(Person.prototype === Person);//->false
//结论：构造函数的原型对象只是构造函数的一个属性，这个属性是自动获得的。并不是构造函数的一个实例。但是这个属性的构造函数指向该构造函数


// 原型对象其实就是普通对象（但 Function.prototype 除外，它是函数对象，但它很特殊，他没有prototype属性（前面说道函数对象都有prototype属性））
function Worker() {
}
console.log(Worker.prototype); //->Worker{}，注意：Worker的prototype并不是Worker的一个实例
console.log(new Worker()); //->Worker{}
console.log(typeof Worker.prototype); //->Object
console.log(typeof Function.prototype); //-> Function，这个特殊
console.log(typeof Object.prototype); // ->Object
console.log(typeof Function.prototype.prototype); //->undefined
console.log(typeof Function.prototype.__proto__); //->object
console.log(Function.prototype.__proto__ === Object.prototype); //->true


