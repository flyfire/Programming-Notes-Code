//定义字符串
var str = "Hello, World";
var str2 = "a\
                b\
                c\
                ";
console.log(str.length);
console.log(str2.length);

//slice表示切开，可以反着切
console.log(str.slice(1, 4));
console.log(str.slice(-3));

//索引
console.log(str.charAt(3));
console.log(str[3]);

//正则表达式
var text = "testing: 1, 2, 3";
var pattern = "[s]";
console.log(text.search(pattern));