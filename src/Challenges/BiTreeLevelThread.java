package Challenges;
import apple.laf.JRSUIUtils;

import java.util.*;

/**
 * Created by wentaod on 12/31/15.
 */
public class BiTreeLevelThread {
    static private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;
        TreeNode(int x) { val = x; }
    }

    static public TreeNode createBST() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(10);

        return root;
    }

    static public void print(TreeNode node) {
        if(node == null)
            return;
        print(node.left);
        System.out.println(node.val);
        print(node.right);
    }

    static public void printLevels(TreeNode root) {
        if(root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(null); // indicate the beginning of a new level
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                queue.offer(null); // indicate the beginning of a new level
                if(queue.peek() == null)
                    return;
                printLevel(queue.peek());
            }
            else {
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
    }

    static void printLevel(TreeNode root) {
        TreeNode node = root;
        while(node!=null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }

    static public void main(String[] args) {
        TreeNode root = createBST();
        //thread(root, 0);
        thread(root);
        printLevels(root);
    }


    static List<TreeNode> tails = new LinkedList<>();

    static public void thread(TreeNode root, int level) {
        if(root==null)
            return;
        if(level+1 > tails.size())
            tails.add(new TreeNode(Integer.MIN_VALUE)); // dummy head
        thread(root.left, level+1);
        tails.get(level).next = root;
        tails.set(level, root);
        thread(root.right, level+1);
    }

    static public void thread(TreeNode root) {
        TreeNode leftMost = root; // indicate left most node of this level
        while(leftMost != null) {
            TreeNode node = leftMost; // node iterate through all node in this level
            TreeNode child = null; // child iterate through all node in next level
            leftMost = null; // now let leftMost points to left most child in next level
            while(node != null) {
                if(node.left != null) {
                    if(child != null) {
                        child.next = node.left;
                        child = node.left;
                    }
                    else {
                        child = node.left;
                        leftMost = node.left;
                    }
                }
                if(node.right != null) {
                    if(child != null) {
                        child.next = node.right;
                        child = node.right;
                    }
                    else {
                        child = node.right;
                        leftMost = node.right;
                    }
                }

                node = node.next;
            }
        }
    }
}
