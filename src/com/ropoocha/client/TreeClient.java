package com.ropoocha.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TreeClient {
  private Socket client;
  private PrintWriter out;
//  private BufferedReader in;
  private ObjectInputStream treeAcceptor;

  public void startConnection(String ip, int port) {
    try {
      client = new Socket(ip, port);
      out = new PrintWriter(client.getOutputStream(), true);
//      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      treeAcceptor = new ObjectInputStream(client.getInputStream());

    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }

  public Object sendMessage(String msg) {
    try {
      out.println(msg);
      return treeAcceptor.readObject();
    } catch (IOException | ClassNotFoundException ioe) {
      return null;
    }
  }

//  public String getMessage() {
//    try {
//      return in.readLine();
//    } catch (IOException e) {
//      e.printStackTrace();
//      return null;
//    }
//  }

  public void stopConnection() {
    try {
      treeAcceptor.close();
      out.close();
      client.close();
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }
}
