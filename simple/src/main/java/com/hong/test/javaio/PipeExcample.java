package com.hong.test.javaio;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * java io 管道通信
 * Created by caihongwei on 14/08/2017 10:47 AM.
 */
public class PipeExcample {
    static class Send implements Runnable {
        private final PipedOutputStream output;
        Send(PipedOutputStream output) {
            this.output = output;
        }

        @Override
        public void run() {
            String s = "Hello World.";
            try {
                output.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Receive implements Runnable {
        private final PipedInputStream input;

        Receive(PipedInputStream input) {
            this.input = input;
        }

        @Override
        public void run() {
            int l;
            try {
                l = input.read();
                while (l != -1) {
                    System.out.print((char) l);
                    l = input.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream(outputStream);

        Send send = new Send(outputStream);
        Receive receive = new Receive(inputStream);

        new Thread(receive).start();
        new Thread(send).start();
    }
}
