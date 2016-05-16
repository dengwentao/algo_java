package Challenges;

/**
 * Segment Tree
 * Created by wentaod on 5/16/16.
 */
public class SegmentTree {

    int[] tree;
    int[] input;

    public SegmentTree(int[] input) {
        this.input = input;
        int height = (int)Math.ceil(Math.log(input.length) / Math.log(2)); // tree height
        int max = (int)Math.pow(2, height+1) - 1; // maximum nodes in tree
        tree = new int[max];
        construct(0, input.length-1, 0);
    }

    // construct subtree for range [start, end] and return the root. tree[index] represents the root.
    private int construct(int start, int end, int index) {
        //System.out.println("start=" + start + ", end=" + end + ", index=" + index);
        if (start == end) {
            tree[index] = input[start];
            return tree[index];
        }
        int mid = (start + end) / 2;
        tree[index] = Math.min(construct(start, mid, 2*index+1), construct(mid+1, end, 2*index+2));
        return tree[index];
    }

    // return min value of elements with input array index in range [qStart, qEnd]
    public int minRange(int qStart, int qEnd) {
        return minRangeUtil(qStart, qEnd, 0, input.length-1, 0);
    }

    // Query min value in input array index range [qStart, qEnd],
    // working on subtree with root at tree[index] that is responsible for input array index range [start, end].
    // tree[index] is responsible for min value in range [start, end]
    private int minRangeUtil(int qStart, int qEnd, int start, int end, int index) {
        if (qStart <= start && end <= qEnd) // query window fully covers the range that tree[index] is responsible for
            return tree[index];
        if (qStart > end || qEnd < start) // query window not overlapping with current range
            return Integer.MAX_VALUE;
        int mid = (start + end) / 2;
        return Math.min(minRangeUtil(qStart, qEnd, start, mid, index*2+1), minRangeUtil(qStart, qEnd, mid+1, end, index*2+2));
    }

    static public int minRange(int[] input, int start, int end) {
        SegmentTree tree = new SegmentTree(input);
        return tree.minRange(start, end);
    }

    static public void main(String args[]) {
        int[] array = {2, 7, 1, 0, 9, 4, 6, 0, 2};
        System.out.println(minRange(array, 4, 6));
    }
}
