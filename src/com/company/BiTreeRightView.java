package com.company;
import apple.laf.JRSUIUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
*/


class BiTreeSolution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new LinkedList<>();

        if(root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curCount = 1;
        int nextCount = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curCount --;

            if(node.left != null) {
                queue.offer(node.left);
                nextCount ++;
            }
            if(node.right != null) {
                queue.offer(node.right);
                nextCount ++;
            }

            //now start a new level
            if(curCount==0) {
                curCount = nextCount;
                nextCount = 0;
                list.add(node.val);
            }

        }

        return list;
    }

    TreeNode createBiTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        //root.right.left.right = new TreeNode(7);
        root.left.left.right = new TreeNode(9);
        return root;
    }

    public void test() {
        TreeNode root = createBiTree();
        List<Integer> list = rightSideView(root);
        for(int i : list) {
            System.out.println(i);
        }
    }
}

public class BiTreeRightView {
    public static void main(String[] args) {
        BiTreeSolution sol = new BiTreeSolution();
        sol.test();
    }

}
