package com.company;
import java.util.*;

/**
 * Created by wedeng on 2/6/15.
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class BSTIterator {

    TreeNode root;
    Stack<TreeNode> s;

    public BSTIterator(TreeNode root) {
        this.root = root;
        s = new Stack<TreeNode>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return root!=null || !s.empty();
    }

    /** @return the next smallest number */
    public int next() throws EmptyStackException {
        while(root!=null) {
            s.push(root);
            root = root.left;
        }
        if(s.empty())
            throw new EmptyStackException();
        root = s.pop();
        TreeNode result = root;
        root = root.right;
        return result.val;
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

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createBST();
        BSTIterator i = new BSTIterator(root);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}


//Post order traversal of bi-tree
class SolutionPostOrder {
    Stack<TreeNode> s1 = new Stack<TreeNode>();
    Stack<TreeNode> s2 = new Stack<TreeNode>();

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<Integer>();
        if(root!=null)
            s1.push(root);
        while(!s1.empty()) {
            root = s1.pop();
            s2.push(root);
            if(root.left!=null)
                s1.push(root.left);
            if(root.right!=null)
                s1.push(root.right);
        }
        while(!s2.empty())
            res.add(s2.pop().val);
        return res;
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

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = createBST();
        SolutionPostOrder sol = new SolutionPostOrder();
        List<Integer> res = sol.postorderTraversal(root);
        for(int i : res) {
            System.out.println(i);
        }
    }
}
