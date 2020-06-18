package com.ropoocha.server;

import com.ropoocha.tree.SpecifiedTree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandlerServer extends Thread {

  Socket client;
  BufferedReader in;
  ObjectOutputStream out;
  SpecifiedTree tree;
  Message msg;

  public HandlerServer(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      out = new ObjectOutputStream(client.getOutputStream());

      String request;
      while ((request = in.readLine()) != null) {
        handleRequest(request);
        out.writeObject(msg);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void handleRequest(String request) {
    try {
      String req =   request.split(" ")[0];
      String value = request.split(" ")[1];
      boolean result = false;

      switch (req) {
        case "SET":
          if (!value.equals("Integer") && !value.equals("Double") && !value.equals("String")) {
            throw new IllegalArgumentException("Żądanie zostało źle sformuowane.");
          } else {
            tree = new SpecifiedTree(value);
          }
          break;
        case "INSERT":
          tree.insert(value);
          break;
        case "DELETE":
          tree.delete(value);
          break;
        case "SEARCH":
          result = tree.search(value);
          break;
        default:
          throw new IllegalArgumentException("Nie rozpoznano żądania");
      }

      msg = new Message(tree, result);

    } catch (ArrayIndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }
}
