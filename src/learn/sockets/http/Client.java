package learn.sockets.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by MuhammadAli on 11/8/2017.
 */
public class Client {

    // host port method filename
    public static void main (String args[]){

        try {

            Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            Scanner s = new Scanner(socket.getInputStream());

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.print(args[2] + " \\" + args[3] + " HTTP/1.0\r\n");
            printWriter.print("HOST: " + args[0] + "\r\n");
            printWriter.print("Connection: keep-alive\r\n");
            printWriter.print("User-Agent: CrazyClient\r\n");

            printWriter.print("\r\n");
            printWriter.flush();

            while(s.hasNextLine()){
                System.out.println(s.nextLine());
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
