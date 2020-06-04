package com.ropoocha.server;

import com.ropoocha.tree.BinaryTree;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HandlerServer extends Thread {
  Socket client;
  Scanner in;
  PrintWriter out;

  public HandlerServer(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try {
      in = new Scanner(client.getInputStream());
      out = new PrintWriter(client.getOutputStream(), true);

      out.println("On which tree's type would you like to operate? (1/2/3)");
      out.println("1. Integers");
      out.println("2. Doubles");
      out.println("3. Strings");

      // todo - initialize tree with the chosen type

    } catch (IOException | InputMismatchException e) {
      e.printStackTrace();
    }
  }
}
