package Challenges;

/**
 * Created by wentaod on 1/1/16.
 */
public class MaxBst {
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

    int count = 0;

    // find maximum bst from a bi-tree. Max bst must have all the bi-tree's descendants under bst root.
    int maxBst(TreeNode root) {
        countBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return count;
    }

    // return count of tree nodes if this is a BST, else return -1.
    // update max on the go.
    int countBst(TreeNode root, int min, int max) {
        if(root==null)
            return 0;
        if(root.val > min && root.val < max) {
            int left = countBst(root.left, min, root.val);
            int right = countBst(root.right, root.val, max);
            if(left==-1 || right==-1)
                return -1;
            else {
                int count = left + right + 1;
                if(this.count < count)
                    this.count = count;
                return count;
            }
        }
        return -1;
    }

    int countNotAllDescendant = 0;

    // find max BST in Bi-tree where BST doesn't need to include all its root's bi-tree descendants.
    int maxBstNotAllDescendant(TreeNode root) {
        maxBstNotAllDescendant(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return countNotAllDescendant;
    }

    // Return node count of max BST starting from root.
    // Return 0 if root itself doesn't follow BST.
    int maxBstNotAllDescendant(TreeNode root, int min, int max) {
        if(root == null)
            return 0;
        if(root.val > min && root.val < max) {
            int left = maxBstNotAllDescendant(root.left, min, root.val);
            int right = maxBstNotAllDescendant(root.right, root.val, max);
            int count = 1 + left + right;
            if(countNotAllDescendant < count)
                countNotAllDescendant = count;
            return count;
        }
        else {
            maxBstNotAllDescendant(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
            return 0;
        }
    }


    static public void main(String[] args) {
        TreeNode root = createBST();
        MaxBst solution = new MaxBst();
        System.out.println(solution.maxBst(root));
        System.out.println(solution.maxBstNotAllDescendant(root));
    }

}
