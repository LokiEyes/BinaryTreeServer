package com.ropoocha.GUI;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TreeFrame extends JFrame {

  JPanel context = new JPanel();
  String IP = "";
  Object[] options = {"Integer", "Double", "String"};
  int result;
  int status = 0;
  public static final int INSERT = 1;
  public static final int DELETE = 2;
  public static final int SEARCH = 3;
  public static final int NONE   = 0;
  String value;

  public TreeFrame() {
    initUI();
  }

  private void initUI() {
    setTitle("Drzewo Binarne");
    setSize(1024, 720);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File...");
    JMenu edit = new JMenu("Edit");

    JMenuItem close = new JMenuItem("Close");

    JMenuItem insert = new JMenuItem("Insert...");
    JMenuItem delete = new JMenuItem("Delete...");
    JMenuItem search = new JMenuItem("Search...");

    close.addActionListener(e -> System.exit(0));

    insert.addActionListener(e -> {
      value = JOptionPane.showInputDialog(context, "Prosze podac warosc do dodania:", "Insert", JOptionPane.QUESTION_MESSAGE);
      status = INSERT;
    });
    delete.addActionListener(e -> {
      value = JOptionPane.showInputDialog(context, "Prosze podac warosc do usunięcia:", "", JOptionPane.QUESTION_MESSAGE);
      status = DELETE;
    });
    search.addActionListener(e -> {
      value = JOptionPane.showInputDialog(context, "Prosze podac warosc do sprawdzenia:", "", JOptionPane.QUESTION_MESSAGE);
      status = SEARCH;
    });

    file.add(close);

    edit.add(insert);
    edit.add(delete);
    edit.add(search);

    menuBar.add(file);
    menuBar.add(edit);

    setJMenuBar(menuBar);
    add(context);

    IP = JOptionPane.showInputDialog(context, "Wprowadź IP serwera oraz port (np. 127.0.0.1:5555)");
    result = JOptionPane.showOptionDialog(context, "Prosze wybrać typ drzewa", "Wybór drzewa",
        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
  }

  public String getIP() {
    return IP.equals("") ? null : IP.split(":")[0];
  }

  public int getPort() {
    return Integer.parseInt(IP.equals("") ? "0" : IP.split(":")[1]);
  }

  public int getTreeType() {
    return result;
  }

  public int checkStatus() {
    return status;
  }

  public String getValue() {
    return value;
  }

  public void resetStatus() {
    status = 0;
  }
}
