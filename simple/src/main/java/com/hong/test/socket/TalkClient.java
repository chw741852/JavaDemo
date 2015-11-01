package com.hong.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * socket client
 *
 * Created by Hongwei on 2015/10/31.
 */
public class TalkClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 4000)) {
            // 由系统标准输入设备构造BufferedReader对象
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            // 由socket对象得到输出流，并构造PrintWriter对象
            PrintWriter os = new PrintWriter(socket.getOutputStream());
            // 由socket对象得到输入流，并构造BufferedReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String readline = sin.readLine();
            while (!"bye".equals(readline)) {
                // 将字符串输出到server
                os.println(readline);
                // 刷新输出流，使server马上收到字符串
                os.flush();
                System.out.println("Client: " + readline);
                // 从server读入一字符串，并打印到标准输出上
                System.out.println("Server: " + is.readLine());
                readline = sin.readLine();
            }
            sin.close();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
