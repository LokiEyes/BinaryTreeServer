package com.ropoocha.server;

import com.ropoocha.tree.SpecifiedTree;
import java.io.Serializable;

public class Message implements Serializable {
  private SpecifiedTree st;
  private boolean result;

  public Message(SpecifiedTree st, boolean result) {
    this.st = st;
    this.result = result;
  }

  public boolean getResult() {
    return result;
  }

  public SpecifiedTree getTree() {
    return st;
  }
}
