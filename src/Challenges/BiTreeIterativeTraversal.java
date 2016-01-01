package Challenges;
import java.util.*;

/**
 * Created by wentaod on 12/28/15.
 */
public class BiTreeIterativeTraversal {

    static private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
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

        return root;
    }

    static public TreeNode createBST2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(-1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        root.right.right = new TreeNode(10);

        return root;
    }

    static public void preOrder(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        if(root != null)
            s.push(root);
        while(!s.isEmpty()) {
            TreeNode node = s.pop();
            System.out.print(node.val + ", ");
            if(node.right != null)
                s.push(node.right);
            if(node.left != null)
                s.push(node.left);
        }
    }

    static public void inOrder(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        pushAllLeft(root, s);
        while(!s.isEmpty()) {
            TreeNode node = s.pop();
            System.out.print(node.val + ", ");
            pushAllLeft(node.right, s);
        }
    }

    // push all the way down to the left.
    static private void pushAllLeft(TreeNode root, Stack<TreeNode> stack) {
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    static public void postOrder(TreeNode root) {
        Stack<TreeNode> children = new Stack<>();
        Stack<TreeNode> parent = new Stack<>();
        if(root != null)
            children.push(root);
        while(!children.isEmpty()) {
            TreeNode node = children.pop();
            parent.push(node);
            if(node.left != null)
                children.push(node.left);
            if(node.right != null)
                children.push(node.right);
        }
        while(!parent.isEmpty())
            System.out.print(parent.pop().val + ", ");
    }

    // Print two BST's in sorted order.
    static public void mergeBst(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        pushAllLeft(root1, s1);
        pushAllLeft(root2, s2);
        while(!s1.isEmpty() && !s2.isEmpty()) {
            TreeNode node1 = s1.peek();
            TreeNode node2 = s2.peek();
            if(node1.val <= node2.val) {
                System.out.print(s1.pop().val + ", ");
                pushAllLeft(node1.right, s1);
            }
            else {
                System.out.print(s2.pop().val + ", ");
                pushAllLeft(node2.right, s2);
            }
        }

        while(!s1.isEmpty())
            System.out.print(s1.pop().val + ", ");
        while(!s2.isEmpty())
            System.out.print(s2.pop().val + ", ");
    }

    static public void main(String[] args) {
        TreeNode root = createBST();
        //print(root);
        //preOrder(root);
        //inOrder(root);
        //postOrder(root);
        TreeNode root2 = createBST2();
        mergeBst(root, root2);
    }


}
