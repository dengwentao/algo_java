package com.leetcode;

import java.util.*;

/**
 * Binary Tree Maximum Path Sum
 * Given a binary tree, find the maximum path sum.
 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
 * Created by wentaod on 6/20/16.
 */
public class BinaryTreeMaximumPathSum {
    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static public class Solution {

        Map<TreeNode, Integer> m;
        Map<TreeNode, Integer> M;

        public int maxPathSum(TreeNode root) {
            if (root == null)
                return 0;
            m = new HashMap<>();
            M = new HashMap<>();
            return M(root);
        }

        Integer m(TreeNode root) {
            Integer max = m.get(root);
            if (max != null)
                return max;

            Integer maxChild = -1;
            if (root.left != null) {
                maxChild = Math.max(maxChild, m(root.left));
            }
            if (root.right != null) {
                maxChild = Math.max(maxChild, m(root.right));
            }

            max = root.val + (maxChild > 0 ? maxChild : 0);
            m.put(root, max);
            return max;
        }

        int M(TreeNode root) {
            Integer max = M.get(root);
            if (max != null)
                return max;

            max = Integer.MIN_VALUE;
            Integer rootValue = root.val; // value passing root
            if (root.left != null) {
                max = Math.max(M(root.left), max);
                rootValue += m(root.left);
            }
            if (root.right != null) {
                max = Math.max(M(root.right), max);
                rootValue += m(root.right);
            }

            max = Math.max(max, Math.max(rootValue, m(root)));
            M.put(root, max);
            return max;
        }

        static TreeNode createBST() {
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(3);
            root.right = new TreeNode(8);
            root.left.left = new TreeNode(0);
            root.left.right = new TreeNode(4);
            root.right.left = new TreeNode(6);
            root.right.left.right = new TreeNode(7);
            root.right.right = new TreeNode(9);
            root.right.left.right.left = new TreeNode(-6);
            root.right.left.right.left.left = new TreeNode(-8);

            //print(root);
            return root;
        }

        static void print(TreeNode node) {
            if(node == null)
                return;
            print(node.left);
            System.out.println(node.val);
            print(node.right);
        }

        public void test() {
            TreeNode root = createBST();
            System.out.println(maxPathSum(root));
        }

    }

    static public void main(String[] args) {
        Solution solution = new Solution();
        solution.test();
    }
}
