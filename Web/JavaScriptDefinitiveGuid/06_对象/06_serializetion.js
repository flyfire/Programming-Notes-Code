var o = {x: 1, y: 2, z: [false, null, ""]};
var json = JSON.stringify(o);
console.log(json);
var o1 = JSON.parse(json);
console.log(o1);