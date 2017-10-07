We assume that you already installed Node.js and NPM.

Step 1 (Install NPM modules):
-----------------------------

Open a terminal in this directory and enter the following command:

> npm install

This installs JSPM, a tool we need to use the ES6 import/export Syntax, an HTTP
server and a file watcher.


Step 2 (Install JSPM modules):
------------------------------

JSPM also needs some more stuff to work. One of them is the ES6 -> ES5 transpiler
Babel. So, open a terminal or use the one you opened already and type the
following command:

> jspm install


Step 3 (Serve the files):
-------------------------

Open a terminal or use the one you opened already and type the following command:

> npm run serve

Now you can navigate your browser to localhost:8080/wetter.html to see the current
HTML page. If you already use the port 8080, you can make the server use another
port, but you cannot use the provided NPM script. Instead you write

> http-server -p 8090

where you can substitute a free port for 8090.


Optional step 4 (Start the file system watcher):
------------------------------------------------

Open another terminal and type the following command:

> npm run watch

Any change to a JavaScript file now causes the browser to load it again without
you having to refresh the page manually. Simply remove the marked import
statement at the top of wetter.js to disable it.

Note: For Chrome it might be necessary to keep the DevTools open and check
the option "Disable cache (while DevTools is open)". To activate it, open
the DevTools (Ctrl+Shift+I) and press F1. It should be the first checkbox in
the general settings. Similar issues might arise in other browsers.