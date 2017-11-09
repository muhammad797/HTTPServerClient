package learn.sockets.http;

import java.io.PrintWriter;

/**
 * Created by MuhammadAli on 11/9/2017.
 */
public class ResponseHandler {

    private PrintWriter printWriter;

    ResponseHandler(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void handle(RequestHandler.Request request) {
        // manage request
        // return response
        Response response = new Response();
        sendResponse(response);
    }

    private void sendResponse(Response response){

    }

    public class Response{

    }

}
