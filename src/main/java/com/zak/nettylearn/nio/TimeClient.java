package com.zak.nettylearn.nio;

import com.zak.nettylearn.bio.TimeServerHandler;

public class TimeClient {

    public static void main(String[] args) {
        int port = 18080;

        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001").start();
    }
}
