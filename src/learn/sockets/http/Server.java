package learn.sockets.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by MuhammadAli on 11/8/2017.
 */
public class Server {

    public static String ServerName = "CrazyServer/1.0";

    public static void main(String args[]) {

        System.out.println("Server has started");
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("No clients connected yet.");
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println("*******************************************************************");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {

        private Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                System.out.println("Client Connected. " + socket.getInetAddress().getHostName());
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                RequestHandler requestHandler = new RequestHandler(scanner);
                ResponseHandler responseHandler = new ResponseHandler(printWriter);

                RequestHandler.Request request =requestHandler.handle();
                request.showRequest();

                responseHandler.handle(request);

                printWriter.flush();
                socket.close();

            } catch (SocketException e) {
                System.out.println("Client terminated");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
