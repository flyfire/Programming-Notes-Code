//========================================================================
// 面向对象技术，实现Set集合。
//========================================================================

//定义构造函数
function Set() {
    this.values = {};     //该对象用于保持集合元素
    this.n = 0;           //集合有多少元素
    this.add.apply(this, arguments);  // 所有的参数作为变量加入
}

//定义add方法用于添加元素：添arguments的参数到这个set集合
Set.prototype.add = function () {
    for (var i = 0; i < arguments.length; i++) {
        var val = arguments[i];
        var str = Set._v2s(val);                 // 转换元素为一个String，作为key查找元素
        if (!this.values.hasOwnProperty(str)) {  // If not already in the set
            this.values[str] = val;              // Map string to value
            this.n++;                            // size自增
        }
    }
    return this;                                 // 返回this用于支持链式调用
};

//定义remove方法用以移除元素
Set.prototype.remove = function () {
    for (var i = 0; i < arguments.length; i++) {
        var str = Set._v2s(arguments[i]);
        if (this.values.hasOwnProperty(str)) {
            delete this.values[str];
            this.n--;
        }
    }
    return this;
};

//用于判断称号否存在某个元素
Set.prototype.contains = function (value) {
    return this.values.hasOwnProperty(Set._v2s(value));
};

// Return the size of the set.
Set.prototype.size = function () {
    return this.n;
};

// Call function f on the specified context for each element of the set.
Set.prototype.foreach = function (f, context) {
    for (var s in this.values)                 // For each string in the set
        if (this.values.hasOwnProperty(s))    // Ignore inherited properties
            f.call(context, this.values[s]);  // Call f on the value
};

// 内部方法，返回js对象对于的唯一字符串
Set._v2s = function (val) {
    switch (val) {
        case undefined:
            return 'u';          // Special primitive
        case null:
            return 'n';          // values get single-letter
        case true:
            return 't';          // codes.
        case false:
            return 'f';
        default:
            switch (typeof val) {
                case 'number':
                    return '#' + val;    // Numbers get # prefix.
                case 'string':
                    return '"' + val;    // Strings get " prefix.
                default:
                    return '@' + objectId(val); // Objs and funcs get @
            }
    }

    // For any object, return a string. This function will return a different
    // string for different objects, and will always return the same string
    // if called multiple times for the same object. To do this it creates a
    // property on o. In ES5 the property would be nonenumerable and read-only.
    function objectId(o) {
        var prop = "|**objectid**|";   // Private property name for storing ids
        if (!o.hasOwnProperty(prop))   // If the object has no id
            o[prop] = Set._v2s.next++; // Assign it the next available
        return o[prop];                // Return the id
    }
};

Set._v2s.next = 100;    // Start assigning object ids at this value.
