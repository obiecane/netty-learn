package com.zak.nettylearn.bio;

import lombok.AllArgsConstructor;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @author zak
 */
@AllArgsConstructor
public class TimeServerHandler implements Runnable {

    private Socket socket;

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
//                System.out.println(body);
                System.out.println(body.length());
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order: " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new Date().toString()
                        : "BAD ORDER";
                out.println(currentTime);
                out.flush();
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    this.socket = null;
                }
            }
        }
    }
}
