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

      out.println("Na jakim typie drzewa chciałbyś operować? (1/2/3)");
      out.println("1. Liczby całkowite - Integer");
      out.println("2. Liczby zmiennoprzecinkowe - Double");
      out.println("3. Łańcuchy znaków - String");

    } catch (IOException | InputMismatchException e) {
      e.printStackTrace();
    }
  }
}
