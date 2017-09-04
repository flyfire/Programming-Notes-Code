//JavaScript的运算符非常多

//操作数和结果类型：一些运算符可以作用域任何类型，但仍希望它们操作的数是指定类型的，并且大多数操作符返回特定类型的值
//比如 * 希望操作数字类型，下面表达式的结果是数字9
var result1 = "3" * "3";
console.log(result1);

//+ 运算符：如果有一个数据类型是字符串，那么返回字符串，如果两个操作数都是对象，那么都将进行数学运算。
var date = new Date();
var a = 3;
console.log(a + {});//返回的结果是字符串，{}对象转换为字符串了
console.log(date + a);//结果为数字
console.log(2 + null);//结果为2，null->0
console.log(2 + undefined);//结果为：NaN


