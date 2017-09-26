//========================================================================
// 数组的常用方法
//========================================================================

/*
 join
 reverse
 sort
 concat
 slice
 splice
 push pop
 unshift shift
 toString
 toLocalString
 */

var a = [1, 2, 3, 4, 5, 6];
console.log(a.join('--'));//用符号连接数组元素

a.reverse();//反转数组，会修改数组

var b = ["z", "y", "x"];
b.sort();//对数组进行排序，会修改数组，还可以传入function对排序进行设定，负前正后

var a1 = a.concat(3);//连接数组，返回新的数组
var a2 = a.concat([3, 5], 9, [4, [4]]);
console.log(a1);
console.log(a2);

a.slice(0, 3);//切割数组，返回新的数组
a.slice(1, -1);//-1相对于最后一个数组

//splice是在数组中插入或者删除元素的通用方法，会修改数组，第一个参数表示插入或删除的起始位置，第二个参数表示从数组中应该删除的个数
var a = [1, 2, 3, 4, 5, 6, 7, 8, 9];
var newA = a.splice(4);//
console.log(newA);//[ 5, 6, 7, 8, 9 ]
console.log(a);//[ 1, 2, 3, 4 ]

var a = [1, 2, 3, 4, 5];
var newA = a.splice(2, 0, "a", "b");
console.log(a);//[ 1, 2, 3, 4 ]
console.log(newA);//[]
console.log(a);//[ 1, 2, 'a', 'b', 3, 4, 5 ]

var newA = a.splice(2, 2, [1,2], "b");
console.log(newA);//[ 'a', 'b' ]
console.log(a);//[ 1, 2, [ 1, 2 ], 'b', 3, 4, 5 ]




