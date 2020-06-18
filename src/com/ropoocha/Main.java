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

    tc.sendRequest("SET Integer");
    tc.sendRequest("INSERT 102");
    tc.sendRequest("INSERT 203");
    tc.sendRequest("INSERT 405");
    tc.sendRequest("DELETE 102");
    System.out.println(tc.sendRequest("SEARCH 203").getResult());
  }
}
