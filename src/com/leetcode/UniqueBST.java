package com.leetcode;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by wedeng on 2/9/15.
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

 For example,
 Given n = 3, your program should return all 5 unique BST's shown below.

 1         3     3      2      1
  \       /     /      / \      \
   3     2     1      1   3      2
  /     /       \                 \
 2     1         2                 3
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */

class SolutionUniqBST {

    List<TreeNode>[][] map;

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> l = new LinkedList<TreeNode>();
        map = (List<TreeNode>[][]) Array.newInstance(l.getClass(), n, n);
        return genTree(0, n-1);
    }

    //check if i==j==0 ???
    //return all tree roots of BST generated from i to j
    List<TreeNode> genTree(int i, int j) {
        if(i>j) {
            List<TreeNode> l = new LinkedList<TreeNode>();
            l.add(null);
            return l;
        }
        if(map[i][j]!=null)
            return map[i][j];

        List<TreeNode> result = new LinkedList<TreeNode>();
        for(int k=i; k<=j; k++) {
            List<TreeNode> left = genTree(i, k-1);
            List<TreeNode> right = genTree(k+1, j);
            for(TreeNode l_child : left)
                for(TreeNode r_child : right) {
                    TreeNode root = new TreeNode(k+1);
                    root.left = l_child;
                    root.right = r_child;
                    result.add(root);
                }
        }

        map[i][j] = result;
        return map[i][j];
    }

    void display(TreeNode root) {
        if(root==null) {
            System.out.print("# ");
            return;
        }
        else
            System.out.print(root.val + " ");
        display(root.left);
        display(root.right);
    }

    public void test() {
        List<TreeNode> l = generateTrees(3);
        for(TreeNode t : l) {
            display(t);
            System.out.println();
        }
    }
}


public class UniqueBST {
    public static void main(String[] args) {
        SolutionUniqBST sol = new SolutionUniqBST();
        sol.test();
    }
}
