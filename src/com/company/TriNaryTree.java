package com.company;

/**
 * Created by wedeng on 1/28/15.
 *  Implement insert and delete in a tri-nary tree.  Much like a binary-tree but with 3 child nodes for each parent instead of two
 *  -- with the left node being values < parent, the right node values > parent, and the middle node values == parent.  For example, if I added the following nodes to the tree in this

 order:  5, 4, 9, 5, 7, 2, 2 --  the tree would look like this:

   5
  /|\
 4 5 9
/    /
2    7
|
2
 */

public class TriNaryTree {
    int v;
    TriNaryTree left;
    TriNaryTree mid;
    TriNaryTree right;

    public TriNaryTree(int i) {
        v=i;
    }

    //Insert i into current sub-tree, recursively.
    public void insert(int i) {
        if(i < v)
            if(left == null)
                left = new TriNaryTree(i);
            else
                left.insert(i);
        else if(i > v)
            if(right == null)
                right = new TriNaryTree(i);
            else
                right.insert(i);
        else
            if(mid == null)
                mid = new TriNaryTree(i);
            else
                mid.insert(i);
    }

    //Delete a node from current sub-tree, recursively. Return updated root.
    //If not found, print and no change; just return current root in this case.
    public TriNaryTree delete(int i) {
        if(i < v)
            if(left == null)
                System.out.format("Not found: %d\n", i);
            else
                left = left.delete(i);
        else if(i > v)
            if(right == null)
                System.out.format("Not found: %d\n", i);
            else
                right = right.delete(i);
        else
            if(mid == null)
                //Remove current node and combine its left and right subtree.
                //Note, mid child may have mid child but won't have right or left child.
                return merge(left, right);
            else
                mid = mid.delete(i);
        return this;
    }

    //Recursively merge two trees into one by
    //putting right subtree to right-most of left subtree, and return left root, if both trees are valid.
    private static TriNaryTree merge(TriNaryTree left, TriNaryTree right) {
        if(left == null)
            return right;
        else if(right == null)
            return left;

        //If both left and right valid.
        left.right = merge(left.right, right);
        return left;
    }

    //Display subtree in pre-order similar to bi-tree.
    public void display() {
        System.out.println(v);
        if(left != null)
            left.display();
        if(mid != null)
            mid.display();
        if(right != null)
            right.display();
    }

    //Create a tree from input array.
    public static TriNaryTree createTree(int[] input) {
        TriNaryTree root = null;
        for(int v : input) {
            if(root == null)
                root = new TriNaryTree(v);
            else
                root.insert(v);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] input = {5, 4, 9, 5, 7, 2, 2, 6, 2, 4, 1, 12, 4};
        TriNaryTree root = createTree(input);
        root.insert(8);
        root.display();
        System.out.println("----------");
        root = root.delete(5);
        root = root.delete(5);
        root = root.delete(9);
        root.display();
    }
}
