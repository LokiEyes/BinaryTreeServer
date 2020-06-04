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
}
