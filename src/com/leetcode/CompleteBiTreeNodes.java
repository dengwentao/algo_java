package com.leetcode;

/**
 * Created by wentaod on 7/28/15.
 * Given a complete binary tree, count the number of nodes.
 */
public class CompleteBiTreeNodes {

    static class TreeNode {
             int val;
             TreeNode left;
             TreeNode right;
             TreeNode(int x) { val = x; }
    }

    static class Solution {

        private int depth(TreeNode root) {
            if(root == null)
                return 0;
            return 1 + depth(root.left);
        }

        // count lowest level nodes only. depth is depth of this tree
        public int countFloorNodes(TreeNode root, int depth) {
            if(root == null)
                return 0;
            int dl = depth - 1;
            int dr = depth(root.right);
            if(dl == 0)
                return 1;
            if(dl == dr)
                return countFloorNodes(root.right, dr) + (int)Math.pow(2, dl-1);
            else
                return countFloorNodes(root.left, dl);
        }

        public int countNodes(TreeNode root) {
            if(root == null)
                return 0;
            int d = depth(root);
            return (int)Math.pow(2, d-1) -1 + countFloorNodes(root, d);
        }

            public void test() {

        }
    }

    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.test();
    }
}
