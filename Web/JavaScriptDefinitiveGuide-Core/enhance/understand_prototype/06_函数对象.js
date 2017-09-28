//========================================================================
// 函数对象
//========================================================================

//所有函数对象的__proto__都指向Function.prototype，它是一个空函数

Number.__proto__ === Function.prototype;  //->true
Number.constructor == Function; //true

Boolean.__proto__ === Function.prototype; //->true
Boolean.constructor == Function; //true

String.__proto__ === Function.prototype;  //->true
String.constructor == Function; //true

Array.__proto__ === Function.prototype;   //->true
Array.constructor == Function; //true

RegExp.__proto__ === Function.prototype;  //->true
RegExp.constructor == Function; //true

Error.__proto__ === Function.prototype;   //->true
Error.constructor === Function; //true

Date.__proto__ === Function.prototype    //->true
Date.constructor == Function //true

// 所有的构造器都来自于Function.prototype，甚至包括根构造器Object及Function自身
Object.__proto__ === Function.prototype;  //->true
Object.constructor == Function; //->true

// 所有的构造器都来自于Function.prototype，甚至包括根构造器Object及Function自身
Function.__proto__ === Function.prototype; //->true
Function.constructor == Function; //true
Function.prototype.__proto__ === Object.prototype;//->true

/*
 JavaScript中有内置(build-in)构造器/对象共计12个（ES5中新加了JSON），
 这里列举了可访问的8个构造器。剩下如Global不能直接访问，Arguments仅在函数调用时由JS引擎创建，
 Math，JSON是以对象形式存在的，无需new。它们的proto是Object.prototype。如下
 */
Math.__proto__ === Object.prototype;  //->true
Math.construrctor == Object; //->true

JSON.__proto__ === Object.prototype;  //->true
JSON.construrctor == Object; //true

//结论：所有的构造器都来自于 Function.prototype，甚至包括根构造器Object及Function自身。
// 所有构造器都继承了·Function.prototype·的属性及方法。如length、call、apply、bind
// 所有的构造器也都是一个普通 JS 对象，可以给构造器添加/删除属性等。同时它也继承了Object.prototype上的所有方法：toString、valueOf、hasOwnProperty等。


//========================================================================
// 原型链
//========================================================================
/*
 在 ECMAScript 核心所定义的全部属性中，最耐人寻味的就要数 prototype 属性了。
 对于 ECMAScript 中的引用类型而言，prototype 是保存着它们所有实例方法的真正所在。
 换句话所说，诸如 toString()和 valueOf() 等方法实际上都保存在 prototype 名下，只不过是通过各自对象的实例访问罢了。

 */
