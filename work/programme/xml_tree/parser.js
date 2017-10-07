var fs   = require("fs"),
    sax  = require("sax");

var tree = null;
var stack = [];

var saxStream = sax.createStream(true, {
    trim: true
});
saxStream.on("error", function (e) {
    console.log(e);
});
saxStream.on("text", function (t) {
    stack[0].children.push({
        name: t
    });
});
saxStream.on("opentag", function (node) {
    var newnode = {
        name: node.name,
        children: []
    }
    if (tree) {
        stack[0].children.push(newnode);
    } else {
        tree = newnode;
    }
    stack.unshift(newnode);
});
saxStream.on("closetag", function (node) {
    stack.shift();
});
saxStream.on("end", function () {
    console.log(JSON.stringify(tree));
});

var fs = require("fs");
fs.createReadStream(process.argv[2])
  .pipe(saxStream);