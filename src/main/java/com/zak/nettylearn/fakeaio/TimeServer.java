package com.zak.nettylearn.fakeaio;

import com.zak.nettylearn.bio.TimeServerHandler;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zak
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 18080;
        if (ArrayUtils.isNotEmpty(args)) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignore) {
                // 使用默认端口
            }
        }

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecutor =
                    new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
