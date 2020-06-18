package com.ropoocha.tree;

import java.io.Serializable;

public class SpecifiedTree implements Serializable {

  private final String treeType;
  private BinaryTree<Integer> intTree;
  private BinaryTree<Double> doubleTree;
  private BinaryTree<String> stringTree;

  public SpecifiedTree(String treeType) {
    this.treeType = treeType;

    switch (treeType) {
      case "Integer":
        intTree = new BinaryTree<>();
        break;
      case "Double":
        doubleTree = new BinaryTree<>();
        break;
      case "String":
        stringTree = new BinaryTree<>();
        break;
      default:
        throw new IllegalArgumentException("Niepoprawny typ drzewa.");
    }
  }

  public void insert(String value) {
    switch (treeType) {
      case "Integer":
        intTree.insert(Integer.parseInt(value));
        break;
      case "Double":
        doubleTree.insert(Double.parseDouble(value));
        break;
      case "String":
        stringTree.insert(value);
        break;
      default:
        throw new IllegalArgumentException("Niepoprawny typ drzewa.");
    }
  }

  public void delete(String value) {
    switch (treeType) {
      case "Integer":
        intTree.delete(Integer.parseInt(value));
        break;
      case "Double":
        doubleTree.delete(Double.parseDouble(value));
        break;
      case "String":
        stringTree.delete(value);
        break;
      default:
        throw new IllegalArgumentException("Niepoprawny typ drzewa.");
    }
  }

  public boolean search(String value) {
    switch (treeType) {
      case "Integer":
        return intTree.search(Integer.parseInt(value));
      case "Double":
        return doubleTree.search(Double.parseDouble(value));
      case "String":
        return stringTree.search(value);
      default:
        throw new IllegalArgumentException("Niepoprawny typ drzewa.");
    }
  }

}
