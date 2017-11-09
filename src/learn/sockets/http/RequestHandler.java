package learn.sockets.http;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by MuhammadAli on 11/9/2017.
 */

class RequestHandler {

    private Scanner scanner;
    private Request request;

    RequestHandler(Scanner scanner) {
        this.scanner = scanner;
        this.request = new Request();
    }

    public Request handle() {

        String line;

        line = scanner.nextLine();
        String[] values = line.split(" ");
        request.addAttribute("method", values[0]);

        if (values[1].equals("\\")) values[1] = "index.html";
        else if (values[1].charAt(0) == '\\')
            values[1] = values[1].substring(1);

        request.addAttribute("filename", values[1]);
        request.addAttribute("HTTPVersion", values[2]);

        do {
            line = scanner.nextLine();
            values = line.split(":", 2);
            if (values.length == 2) {
                request.addAttribute(values[0].trim(), values[1].trim());
            }
        } while (!line.isEmpty());

        return request;

    }

    public class Request {

        HashMap<String, String> requestData;

        Request() {
            requestData = new HashMap<>();
        }

        boolean addAttribute(String key, String value) {
            return !requestData.containsKey(key) && Boolean.parseBoolean(requestData.put(key, value));
        }

        String getValue(String key) {
            return requestData.get(key);
        }

        public void showRequest() {
            Set<String> keys = requestData.keySet();
            for (String key : keys) {
                System.out.println(key + ": " + requestData.get(key));
            }
        }

    }


}