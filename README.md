# HTTPServerClient
This is a HTTP Server and Client developed in Java using Sockets.

# How to run it?
## Server:
Just run server like a simple Java file. No command line arguments required.
It uses port 8000 to listen for clients.

## Client:
You would have to provide command line arguments to run it. Like these:
[Host,Port,Method,Filename]
> localhost 8000 GET index.html
OR
> 127.0.0.1 8000 GET index.html

So, after compiling using javac, You can run it like:
> java Client 127.0.0.1 8000 GET index.html

