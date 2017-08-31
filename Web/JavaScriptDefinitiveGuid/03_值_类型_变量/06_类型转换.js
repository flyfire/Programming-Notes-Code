//Number to String
var num =15.555;
console.log(num.toString(2));
console.log(num.toFixed(2));//保留小数点的位数换为字符串
console.log(num.toExponential(3));//使用指数计数法换为字符串
console.log(num.toPrecision(10));//指定有效数字的位数转换为字符串

//parseInt，parseFloat
console.log(parseInt("4 abc"));
console.log(parseInt("-12.32"));
console.log(parseFloat("4.32 abc"));
console.log(parseFloat("0xFF"));

//对象转换为原始类型
console.log([1, 3, 4].toString());
console.log(new Date().valueOf());
