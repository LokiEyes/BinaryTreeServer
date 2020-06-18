package com.ropoocha;

import com.ropoocha.GUI.TreeFrame;
import com.ropoocha.client.TreeClient;
import com.ropoocha.tree.BinaryTree;

public class Main {

  public static void main(String[] args) {
    TreeFrame tf = new TreeFrame();
    tf.setVisible(true);

    String IP = tf.getIP();
    int port = tf.getPort();

    int treeType = tf.getTreeType();

    TreeClient tc = new TreeClient();
    tc.startConnection(IP, port);

    if (treeType == 0) {
      BinaryTree<Integer> treeInteger = (BinaryTree<Integer>) tc.sendMessage("SET " + treeType);
      while (true) {
        int status = tf.checkStatus();
        switch (status) {
          case 0:
            System.out.println("xd");
            break;
          case 1:
            System.out.println("dodano");
            treeInteger.insert(Integer.parseInt(tf.getValue()));
            tf.resetStatus();
            break;
          case 2:
            treeInteger.delete(Integer.parseInt(tf.getValue()));
            tf.resetStatus();
            break;
          case 3:
            treeInteger.search(Integer.parseInt(tf.getValue()));
            tf.resetStatus();
            break;
        }
      }
    } else if (treeType == 1) {
      BinaryTree<Double> treeDouble = (BinaryTree<Double>) tc.sendMessage("SET " + treeType);
      while (true) {
        switch (tf.checkStatus()) {
          case 1:
            // insert
            break;
          case 2:
            // delete
            break;
          case 3:
            // search
            break;
        }
      }
    } else {
      BinaryTree<String> treeString = (BinaryTree<String>) tc.sendMessage("SET " + treeType);
      while (true) {
        switch (tf.checkStatus()) {
          case 1:
            // insert
            break;
          case 2:
            // delete
            break;
          case 3:
            // search
            break;
        }
      }
    }
  }
}
