package learn.sockets.http;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by MuhammadAli on 11/9/2017.
 */
public class ResponseHandler {

    private PrintWriter printWriter;
    private Response response;

    ResponseHandler(PrintWriter printWriter) {
        this.printWriter = printWriter;
        response = new Response();
    }

    public void handle(RequestHandler.Request request) {
        // manage request
        // return response
        File file = new File(request.getValue("filename"));
        if(file.exists()){
            // 200 OK
        } else {
            // 404
        }
        sendResponse(response);
    }

    private void sendResponse(Response response){

    }

    public class Response{

    }

}
