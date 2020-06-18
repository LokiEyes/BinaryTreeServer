package com.ropoocha.tree;

import java.io.Serializable;

public class BinaryTree<T extends Comparable<T>> implements Serializable {

  private Node<T> root;

  public BinaryTree() {

  }

  private Node<T> insertRecursive(Node<T> current, T value) {
    if (current == null) {
      return new Node<T>(value);
    }

    if (value.compareTo(current.value) < 0) {
      current.left = insertRecursive(current.left, value);
    } else if (value.compareTo(current.value) > 0) {
      current.right = insertRecursive(current.right, value);
    } else {
      return current;
    }

    return current;
  }

  public void insert(T value) {
    root = insertRecursive(root, value);
    System.out.println("Dodano: " + value.toString());
  }

  private Node<T> deleteRecursive(Node<T> current, T value) {
    if (current == null) {
      return null;
    }

    if (value.compareTo(current.value) == 0) {
      if (current.left == null && current.right == null) {
        return null;
      } else if (current.right == null) {
        return current.left;
      } else if (current.left == null) {
        return current.right;
      } else {
        T smallestValue = findSmallestValue(current.right);
        current.value = smallestValue;
        current.right = deleteRecursive(current.right, smallestValue);
        return current;
      }
    } else if (value.compareTo(current.value) < 0) {
      current.left = deleteRecursive(current.left, value);
      return current;
    } else {
      current.right = deleteRecursive(current.right, value);
      return current;
    }
  }

  public void delete(T value) {
    root = deleteRecursive(root, value);
    System.out.println("Usunięto: " + value.toString());
  }

  private T findSmallestValue(Node<T> root) {
    return root.left == null ? root.value : findSmallestValue(root.left);
  }

  private boolean searchRecursive(Node<T> current, T value) {
    if (current == null) {
      return false;
    }
    if (value.compareTo(current.value) == 0) {
      return true;
    }

    return value.compareTo(current.value) < 0
        ? searchRecursive(current.left, value)
        : searchRecursive(current.right, value);
  }

  public boolean search(T value) {
    System.out.println("Wyszukano: " + value.toString());
    return searchRecursive(root, value);
  }

}
