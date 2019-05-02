package com.zak.nettylearn.bio;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author zak
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 18080;

        if (ArrayUtils.isNotEmpty(args)) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
                // 使用默认值
            }
        }

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.println("QUERY TIME ORDER");
            out.flush();
            System.out.println("Send order 2 server succeed");
            String resp = in.readLine();
            System.out.println("Now is： " + resp);
        } catch (Exception ignore) {
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
