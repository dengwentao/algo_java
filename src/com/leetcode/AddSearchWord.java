package com.leetcode;

import java.util.HashMap;

/**
 * Created by WentaoD on 5/17/15.
 * Design a data structure that supports the following two operations:
 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string
 containing only letters a-z or '.' . A '.' means it can represent any one
 letter.
 You may assume that all words are consist of lowercase letters a-z.
 */

class WordDictionary {

  class Node {
    char c;
    HashMap<Character, Node> children;

    public Node(char x) {
      c = x;
      children = new HashMap<>();
    }

    // Add a char to current node as its child. Put it in the x-'a' index,
    // and return it.
    // If the char is already there, just return it.
    public Node addChild(char x) {
      Node child = getChild(x);
      if(child != null)
        return child;
      child = new Node(x);
      children.put(x, child);
      return child;
    }

    // find char x from children of current node. return null if none.
    public Node getChild(char x) {
      return children.get(x);
    }
  }

  Node root;

  WordDictionary() {
    root = new Node('^');
  }

  // Adds a word into the data structure.
  public void addWord(String word) {
    Node node = root;
    for(char c : word.toCharArray()) {
      node = node.addChild(c);
    }
    node.addChild('$');
  }

  // Returns if the word is in the data structure. A word could
  // contain the dot character '.' to represent any one letter.
  public boolean search(String word) {
    return searchRecursively(word, root);
   }

  // search recursively, starting from node.
  private boolean searchRecursively(String word, Node node) {
    if(node==null)
      return false;
    if(word.length()==0)
      return node.getChild('$') != null;
    char c = word.charAt(0);
    String sub = word.substring(1);
    if(c=='.') {
      //for(char x='a'; x<='z'; x++) {
      //  if(searchRecursively(word.substring(1), node.getChild(x)))
      //    return true;
      for(Node child : node.children.values()) {
        if(child.c=='$')
          continue;
        if(searchRecursively(sub, child))
          return true;
      }
      return false;
    }
    else {
      Node n = node.getChild(c);
      if(n==null)
        return false;
      return searchRecursively(sub, n);
    }
  }


}

public class AddSearchWord {
  private static void validate(boolean exp) {
    if(exp==false)
      System.out.println("Wrong validation.");
    else
      System.out.println("Correct validation.");
  }

  //no word, add '', search '',
  public static void main(String args[]) {
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.addWord("");
    wordDictionary.addWord("mad");

    validate(true == wordDictionary.search(""));
    validate(false == wordDictionary.search("."));
    validate(false == wordDictionary.search("pad"));
    validate(true == wordDictionary.search("bad"));
    validate(true == wordDictionary.search(".ad"));
    validate(true == wordDictionary.search("b.."));
    validate(false == wordDictionary.search("d..."));

  }
}
