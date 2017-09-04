//========================================================================
// 属性描述符
//========================================================================
var pd = Object.getOwnPropertyDescriptor({x: 1}, 'x');
console.log(pd);

var accessorObj = {
    get value() {
        return 1;
    },
    set value(value) {

    }
};
var accessorPd = Object.getOwnPropertyDescriptor(accessorObj, 'value');
console.log(accessorPd);

//========================================================================
// defineProperty
//========================================================================
var o = {};
Object.defineProperty(o, "x", {
    value: 1,
    writable: true,
    enumerable: true,
    configurable: true
});
console.log(o.x);
console.log(Object.keys(o));
//现在对属性x进行修改，让其变为只读
Object.defineProperty(o, "x", {
    writable: false
});
o.x = 4;//操作失败，不报错
console.log(o.x);
//由于x依然是可以配置id，可以通过下面方法改变x的值
Object.defineProperty(o, "x", {
    value: 4
});
console.log(o.x);
//现在将先从数据属性改为存储器属性
Object.defineProperty(o, "x", {
    get: function () {
        return 0;
    }
});
console.log(o.x);


//========================================================================
// 健壮版本的extend方法
//========================================================================
Object.defineProperty(Object.prototype, "extend", {
    writable: true,
    enumerable: false,
    configurable: true,
    value: function (o) {
        var names = Object.getOwnPropertyNames(o);//得到所有自有属性的名称，包括不可枚举属性
        for (var i = 0; i < names.length; i++) {
            if (names[i] in this) {//this指向方法调用者，如果已经存在这些属性，跳过
                continue;
            }
            var desc = Object.getOwnPropertyDescriptor(o, names[i]);
            Object.defineProperty(this, names[i], desc);
        }
    }
});

var a = {x: 4, y: 4};
var b = {};
b.extend(a);
console.log(b);