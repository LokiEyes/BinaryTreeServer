package com.ropoocha.server;

import com.ropoocha.tree.BinaryTree;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HandlerServer extends Thread {
  Socket client;
  BufferedReader in;
  PrintWriter out;
  ObjectOutputStream treePass;
  BinaryTree<Integer> treeInteger;
  BinaryTree<Double> treeDouble;
  BinaryTree<String> treeString;
  int treeType = 0;

  public HandlerServer(Socket client) {
    this.client = client;
  }

  @Override
  public void run() {
    try {
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//      out = new PrintWriter(client.getOutputStream(), true);
      treePass = new ObjectOutputStream(client.getOutputStream());

      String request;
      while ((request = in.readLine()) != null) {
        handleRequest(request, treePass);
      }

    } catch (IOException | InputMismatchException e) {
      e.printStackTrace();
    }
  }

  public synchronized void handleRequest(String request, ObjectOutputStream treePass) {
    String command = request.split(" ")[0];
    String value = request.split(" ")[1];

    try {
      switch (command) {
        case "SET":
          treeType = Integer.parseInt(value);
          setupTree(treeType);
          if (treeType == 0) {
            treePass.writeObject(treeInteger);
          } else if (treeType == 1) {
            treePass.writeObject(treeDouble);
          } else {
            treePass.writeObject(treeString);
          }
          break;
        case "INSERT":
          if (treeType == 0) {
            treeInteger.insert(Integer.parseInt(value));
          } else if (treeType == 1) {
            treeDouble.insert(Double.parseDouble(value));
          } else {
            treeString.insert(value);
          }
      }
    } catch (IOException ioe) {
      ioe.getStackTrace();
    }


  }

  private <T extends Comparable<T>> void insertTree(BinaryTree<T> bt, T value) {
    bt.insert(value);
  }

  public synchronized void setupTree(int value) {
    switch (value) {
      case 0:
        treeInteger = new BinaryTree<>();
        treeType = 0;
        System.out.println("Stworzono int");
        break;
      case 1:
        treeDouble = new BinaryTree<>();
        treeType = 1;
        System.out.println("Stworzono double");
        break;
      case 2:
        treeString = new BinaryTree<>();
        treeType = 2;
        System.out.println("Stworzono string");
        break;
      default:

        break;
    }
  }
}
