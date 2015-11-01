package com.hong.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket server
 *
 * Created by Hongwei on 2015/10/31.
 */
public class TalkServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(4000)) {
            try (Socket socket = server.accept()) {
                String line;
                BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter os = new PrintWriter(socket.getOutputStream());
                BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Client: " + is.readLine());

                line = sin.readLine();
                while (!"bye".equals(line)) {
                    os.println(line);
                    os.flush();

                    System.out.println("Server: " + line);
                    System.out.println("Client: " + is.readLine());
                    line = sin.readLine();
                }

                os.close();
                is.close();
                sin.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
