package Challenges;

/**
 * Created by wentaod on 12/28/15.
 */
public class RemoveBSTNode {
    static private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // remove a node whose value is x from the given tree
    static public TreeNode remove(TreeNode root, int x) {
        if(root == null)
            return null;
        if(root.val < x)
            root.right = remove(root.right, x);
        else if(root.val > x)
            root.left = remove(root.left, x);
        else { // found the node
            if(root.left == null && root.right == null) // it's a leaf
                root = null;
            else if(root.left == null) // no left child
                root = root.right;
            else { // has left child
                TreeNode node = root.left;
                while(node.right != null)
                    node = node.right;
                node.right = root.right;
                root = root.left;
            }
        }
        return root;
    }

    // find the node whose value is least larger than x from the given tree
    // return MIN or MAX if none.
    static public int nextBiggerNode(TreeNode root, int x) {
        return nextBiggerNode(root, x, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static private int nextBiggerNode(TreeNode root, int x, int min, int max) {
        if(root==null)
            return Integer.MIN_VALUE;
        if(root.val < x)
            return nextBiggerNode(root.right, x, root.val, max);
        else if(root.val > x)
            return nextBiggerNode(root.left, x, min, root.val);
        else {
            if(root.right == null)
                return max;
            TreeNode node = root.right;
            while(node.left != null)
                node = node.left;
            return node.val;
        }
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

    static public void print(TreeNode node) {
        if(node == null)
            return;
        print(node.left);
        System.out.println(node.val);
        print(node.right);
    }

    static public void main(String[] args) {
        TreeNode root = createBST();
        root = remove(root, 5);
        System.out.println("New root is: " + root.val);
        print(root);
        System.out.println("Next bigger is: " + nextBiggerNode(root, 4));
    }
}
