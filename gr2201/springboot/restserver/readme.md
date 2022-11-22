# Rest server

This module is responsible for starting a springboot server that can take http request to mapped out endpoint and give data from data storage if request is correct. The server only deals with user information.

`ServerApplication` has an method to start the server.
`ServerController` mappes out endpoint for http requests such as get, put, post, delete. Afterward delegates the response to `ServerService`.
`ServerService` takes input and checks with persistence module if inputdata is correct and returns or mutates user information on valid requests.
