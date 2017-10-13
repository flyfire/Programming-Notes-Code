//========================================================================
// 构造函数
//========================================================================
function Person(name, age, job) {
    this.name = name;
    this.age = age;
    this.job = job;
}
var person1 = new Person("mike", 12, "student");
var person2 = new Person("luck", 22, "teacher");
var same = (person1.constructor === person2.constructor && person2.constructor === Person);//->true，实例的构造函数属性（constructor）指向构造函数

