package com.hong.test.javaio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by caihongwei on 14/08/2017 2:58 PM.
 */
public class InputStreamExcample {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://key.sangotek.com/wifikey/tempauth?mac=aa&ssid=sss&bssid=c2:44:07:5c:67:12");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            int data = inputStream.read();
            while (data != -1) {
                System.out.print((char) data);
                data = inputStream.read();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
