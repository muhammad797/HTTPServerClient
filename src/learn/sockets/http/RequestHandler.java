package learn.sockets.http;

import java.util.Scanner;

/**
 * Created by MuhammadAli on 11/9/2017.
 */

class RequestHandler {

    private Scanner scanner;

    RequestHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Request handle() {
        String line;
        do {
            line = scanner.nextLine();
            System.out.println(line);
        } while (!line.isEmpty());
        Request request = new Request();
        request.setFilename("index.html");
        return request;
    }

    public class Request {
        String filename;

        public String filename() {
            return filename;
        }

        void setFilename(String filename) {
            this.filename = filename;
        }
    }
}