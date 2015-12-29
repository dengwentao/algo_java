package Challenges;
import java.util.*;

/**
 * Created by wentaod on 12/29/15.
 */
public class BiTreePathSumToValue {
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
        root.right.left.right.left = new TreeNode(-6);
        root.right.left.right.left.left = new TreeNode(-8);

        return root;
    }

    static public void print(TreeNode node) {
        if(node == null)
            return;
        print(node.left);
        System.out.println(node.val);
        print(node.right);
    }

    List<List<Integer>> results = new ArrayList<>();

    // return all paths sums to x, with the first element in the path x.
    public List<List<Integer>> pathsSum(TreeNode root, int x) {
        if(root != null) {
            List<List<Integer>> paths = new ArrayList<>();
            pathsSum(root, x, paths);
        }
        return results;
    }

    // For paths, the first element is the sum and remaining parts are path
    // indicated by node values from whole tree root to this root's parent.
    // paths won't change because we only pass cloned version down.
    void pathsSum(TreeNode root, int x, final List<List<Integer>> paths) {
        if(root == null)
            return;

        List<List<Integer>> clonePaths = new ArrayList<>();
        clonePaths.add(Arrays.asList(root.val, root.val)); // add current node as a new path
        if(root.val == x)
            results.add(Arrays.asList(root.val, root.val)); // this single node may be x

        for(List<Integer> path : paths) {
            List<Integer> clonePath = new ArrayList<>();
            int sum = path.get(0) + root.val;
            clonePath.add(sum);
            for(int i=1; i<path.size(); i++)
                clonePath.add(path.get(i));
            clonePath.add(root.val);
            if(sum == x)
                results.add(clonePath); // found a path
            clonePaths.add(clonePath); // still need this path because later we could meet -y and +y
        }

        pathsSum(root.left, x, clonePaths);
        pathsSum(root.right, x, clonePaths);
    }

    public void test() {
        TreeNode root = createBST();
        List<List<Integer>> paths = pathsSum(root, 7);
        for(List<Integer> p : paths) {
            for(int i : p)
                System.out.print(i + ", ");
            System.out.println();
        }
    }

    static public void main(String[] args) {
        BiTreePathSumToValue solution = new BiTreePathSumToValue();
        solution.test();
    }
}
