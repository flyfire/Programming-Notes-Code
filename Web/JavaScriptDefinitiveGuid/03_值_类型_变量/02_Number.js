//定义数字
var num1 = 123;
var num2 = 0x123;
var num3 = 3.32;
var num4 = .324;
var num5 = 6.02e23;
console.log(num1);
console.log(num2);
console.log(num3);
console.log(num4);
console.log(num5);

//Math
console.log("3的9次方= " + Math.pow(3, 9));

//溢出、下溢
console.log(0 / 0);
console.log(1 / 0);
console.log(-1 / 0);

//判断NaN的方法
var nan = NaN;
if (nan != nan) {
    console.log("NaN != NaN");
}
if (isNaN(nan)) {
    console.log("isNaN");
}
if (!isFinite(nan)) {//不是NaN、Infinity、-Infinity时为true
    console.log("!isFinite");
}

//全局对象
console.log(Number.NaN);
console.log(Number.MAX_VALUE);
console.log(Number.MIN_VALUE);
console.log(Number.POSITIVE_INFINITY);
console.log(Number.NEGATIVE_INFINITY);
console.log(Math.PI);

//Number方法
var number = Number("23");
console.log(number);

//进制
var a = 15;
console.log(a.toString(2));//转化为二进制字符串
console.log(a.toString(8));
console.log(a.toString(16));
