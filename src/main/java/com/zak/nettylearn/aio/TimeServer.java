package com.zak.nettylearn.aio;

public class TimeServer {
    public static void main(String[] args) {
        int port = 18080;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
