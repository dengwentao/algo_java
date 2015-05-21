package com.leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.lang.StringBuilder;
/**
 * Created by WentaoD on 5/18/15.
 * Given a 2D board and a list of words from the dictionary, find all words in the board. Only a-z is allowed.
 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 */

class WordsInBoardSolution {

  class Trie {

    class Node {
      char c;
      Node parent; // point to parent node.
      Node[] children = new Node[27]; // a-z and $

      public Node(char c) {
        this.c = c;
      }

      // find child node of this node. If not found, return null.
      public Node findChild(char c) {
        return c == '$' ? children[26] : children[c - 'a'];
      }

      // add c as a child of this node. If already exists, just return it.
      public Node addChild(char c) {
        Node child = findChild(c);
        if(child != null)
          return child;
        else {
          int pos = c=='$' ? 26 : c-'a';
          children[pos] = new Node(c);
          children[pos].parent = this;
          return children[pos];
        }
      }

      // Path from parent down to this node
      public String getPath() {
        StringBuilder path = new StringBuilder();
        Node node = this;
        while(node.c != '^') {
          path.append(node.c);
          node = node.parent;
        }
        return path.reverse().toString();
      }
    }// end Node

    Node root;

    public Trie() {
      root = new Node('^');
    }

    public Node getRoot() {return root;}

    public void addWord(String word) {
      Node node = root;
      for(char c : word.toCharArray())
        node = node.addChild(c);
      node.addChild('$');
    }

    public boolean findWord(String word) {
      Node node = root;
      for(char c : word.toCharArray()) {
        node = node.findChild(c);
        if(node == null)
          return false;
      }
      return node.findChild('$') != null;
    }

    // Find the child node of a node. If not found, return null.
    public Node findChild(Node node, char c) {
      return node.findChild(c);
    }
  }// end Trie

  private Trie trie;
  private char[][] board;
  private HashSet<String> result = new HashSet<>();
  private boolean[][] visited;

  private void cleanVisited() {
    for(int i=0; i<visited.length; i++)
      Arrays.fill(visited[i], false);
  }

  public List<String> findWords(char[][] board, String[] words) {
    if(board==null || board.length==0 || board[0].length==0 || words==null || words.length==0)
      return new LinkedList<>();

    this.board = board;
    this.visited = new boolean[board.length][board[0].length];

    this.trie = new Trie();
    for(String word : words)
      trie.addWord(word);

    for(int row=0; row<board.length; row++) {
      for(int col=0; col<board[0].length; col++) {
        cleanVisited();
        visit(row, col, trie.getRoot());
      }
    }

    List<String> res = new ArrayList<>();
    for(String word : result)
      res.add(word);
    return res;
  }

  // start searching word from position [row][col];
  // whenever found a word, put it into result list.
  // when all paths returns, return; A path returns when no possible word can be found.
  private void visit(int row, int col, Trie.Node node) {
    if(row<0 || col<0 || row==board.length || col==board[0].length)
      return;
    if(visited[row][col])
      return;
    visited[row][col] = true;

    Trie.Node child = node.findChild(board[row][col]);
    if(child == null) {
      visited[row][col] = false;
      return;
    }
    if(child.findChild('$') != null)
      result.add(child.getPath()); // found one word.
    visit(row+1, col, child);
    visit(row-1, col, child);
    visit(row, col+1, child);
    visit(row, col-1, child);
    visited[row][col] = false;
  }

  public void test() {
    char[][] board = {{'o','a','a','n'},
                      {'e','t','a','e'},
                      {'i','h','k','r'},
                      {'i','f','l','v'}};
    String[] words = {"oath","pea","eat","rain", "oat"};

    //char[][] board = {{'a', 'b'}, {'c', 'd'}};
    //String[] words = {"cdba"};
    for(String str : findWords(board, words))
      System.out.println(str);
  }
}

public class WordsInBoard {
  public static void main(String args[]) {
    WordsInBoardSolution sol = new WordsInBoardSolution();
    sol.test();
  }
}
