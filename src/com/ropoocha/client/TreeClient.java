package com.ropoocha.client;

import com.ropoocha.server.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TreeClient {
  private Socket client;
  private PrintWriter out;
  private ObjectInputStream in;

  public void startConnection(String ip, int port) {
    try {
      client = new Socket(ip, port);
      out = new PrintWriter(client.getOutputStream(), true);
      in = new ObjectInputStream(client.getInputStream());

    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }

  public Message sendRequest(String request) {
    try {
      out.println(request);
      return (Message) in.readObject();
    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
      return null;
    }
  }


  public void stopConnection() {
    try {
      in.close();
      out.close();
      client.close();
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }
  }
}
