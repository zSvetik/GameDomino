package com.my.GameDomino;

import com.my.GameDomino.server.JettyServer;

public class StartGame {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        JettyServer server = new JettyServer(port);
        server.start();
        server.waitForInterrupt();
    }

}
