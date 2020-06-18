package com.ropoocha.server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultipleServer {

  ServerSocket server;

  public void start(int port) {
    try {
      server = new ServerSocket(port);
      while (true) {
        new HandlerServer(server.accept()).start();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public void stop() {
    try {
      server.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static void main(String[] args) {
    int port;
    MultipleServer server = new MultipleServer();
    if (args.length == 0) {
      port = 5555;
    } else {
      port = Integer.parseInt(args[0]);
    }
    server.start(port);
  }
}
