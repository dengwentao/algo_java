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
public class TriaryTree {
    int v = 0;
    TriaryTree left = null;
    TriaryTree mid = null;
    TriaryTree right = null;

    public TriaryTree(int i) {v=i;};

    public void insert(int i)
    {
        if(i < v)
            if(left == null)
                left = new TriaryTree(i);
            else
                left.insert(i);
        else if(i > v)
            if(right == null)
                right = new TriaryTree(i);
            else
                right.insert(i);
        else
            if(mid == null)
                mid = new TriaryTree(i);
            else
                mid.insert(i);
    }

    //delete a node from current sub-tree. return updated root.
    //if not found, print and no change; just return current root.
    public TriaryTree delete(int i)
    {
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

    //put right subtree to right-most of left subtree, and return left root;
    private static TriaryTree merge(TriaryTree left, TriaryTree right)
    {
        if(left == null)
            return right;
        else if(right == null)
            return left;

        //have both left and right valid.
        left.right = merge(left.right, right);
        return left;
    }

    public void display()
    {
        System.out.println(v);
        if(left != null)
            left.display();
        if(mid != null)
            mid.display();
        if(right != null)
            right.display();
    }

    public static TriaryTree createTree(int[] input)
    {
        TriaryTree root = null;
        for(int v : input)
        {
            if(root == null)
                root = new TriaryTree(v);
            else
                root.insert(v);
        }
        return root;
    }

    public static void main(String[] args)
    {
        int[] input = {5, 4, 9, 5, 7, 2, 2};
        TriaryTree root = createTree(input);
        root.insert(8);
        root.display();
        root = root.delete(5);
        root = root.delete(5);
        root = root.delete(9);
        root.display();
    }
}
