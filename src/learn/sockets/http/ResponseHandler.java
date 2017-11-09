package learn.sockets.http;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * Created by MuhammadAli on 11/9/2017.
 */
public class ResponseHandler {

    private PrintWriter printWriter;
    private Response response;
    private RequestHandler.Request request;

    ResponseHandler(PrintWriter printWriter) {
        this.printWriter = printWriter;
        response = new Response();
    }

    public void handle(RequestHandler.Request request) {
        this.request = request;
        String method = request.getValue("method");
        switch (method.toUpperCase()) {
            case "GET":
                handleGETRequest(request);
            case "PUT":
                handlePUTRequest(request);
        }
        sendResponse(response);
    }

    private void handlePUTRequest(RequestHandler.Request request) {
        File file = new File(request.getValue("filename"));
        if (file.exists()) {
            response.setResponseCode("Resource already exists");
        } else {
            response.setResponseCode("200 OK");
            try {
                PrintWriter printWriter = new PrintWriter(file);
                printWriter.print(" ");
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleGETRequest(RequestHandler.Request request) {
        File file = new File(request.getValue("filename"));
        response.setServerName(Server.ServerName);

        if (file.exists()) {

            response.setResponseCode("200 OK");
            try (Scanner s = new Scanner(new File("index.html"))) {
                while (s.hasNextLine()) {
                    response.appendBody(s.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {

            response.setResponseCode("404 File not found");
            try (Scanner s = new Scanner(new File("404.html"))) {
                while (s.hasNextLine()) {
                    response.appendBody(s.nextLine());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendResponse(Response response) {

        final Date currentTime = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm:ss a z");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        printWriter.print(this.request.getValue("HTTPVersion") + " " + response.getResponseCode() + "\r\n");
        printWriter.print("Date: " + sdf.format(currentTime) + "\r\n");
        printWriter.print("Server: " + response.getServerName() + "\r\n");
        printWriter.print("Content-Type: " + response.getContentType() + "\r\n");

        printWriter.print("\r\n");

        printWriter.print(response.getBody() + "\r\n");

    }

    public class Response {

        String responseCode;
        StringBuilder body;

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }

        public String getContentType() {
            return ContentType;
        }

        public void setContentType(String contentType) {
            ContentType = contentType;
        }

        String serverName = "CrazyServer/1.0";
        String ContentType;

        Response() {
            body = new StringBuilder();
        }

        void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        String getResponseCode() {
            return this.responseCode;
        }

        void appendBody(String nextLine) {
            body.append(nextLine);
        }

        String getBody() {
            return body.toString();
        }

    }

}
