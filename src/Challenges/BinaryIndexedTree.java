package Challenges;

/**
 * Binary Indexed Tree
 * Created by wentaod on 4/20/16.
 */
public class BinaryIndexedTree {

    // Note, all index in BIT below is 1 based.
    static class BIT {
        static final int BIT_SIZE = 65536;
        int[] array = new int[BIT_SIZE];

        public BIT() {
        }

        public int getValue(int index) {
            return array[index];
        }

        // add element in index with difference
        public void add2(int index, int diff) {
            while(index < BIT_SIZE) {
                array[index] += diff;
                index += index & -index;
            }
        }

        // get sum of first (index) elements which are in range [0, index]
        public int sum2(int index) {
            int sum = 0;
            while(index>0) {
                sum += array[index];
                index -= index & -index;
            }
            return sum;
        }

        // add element in index with difference
        public void add(int index, int diff) {
            if (index >= BIT_SIZE)
                return;
            array[index] += diff;
            index += index & -index;
            add(index, diff);
        }

        // get sum of first (index) elements which are in range [0, index]
        public int sum(int index) {
            if (index <= 0)
                return 0;
            int v = array[index];
            index -= index & -index;
            return v + sum(index);
        }

        // Given a sum, find the highest index that satisfies sum(index) <= sum
        // This works only if all input array elements are positive so that we can do binary search for sums.
        public int findHighest(int sum) {
            int mask = (1<<15); // start from right most node in first level
            int index = 0;
            while(mask != 0 && index < BIT_SIZE) {
                int tmpIdx = index + mask; // try the right most child node of current node
                if(sum >= array[tmpIdx]) { // must be below this node (or to the right in BIT array)
                    sum -= array[tmpIdx];  // now we should find this updated sum in the children nodes
                    index = tmpIdx; // this is the index that we are trying now
                }
                // else, it must be on current node's left side neighbors (or to the left in BIT array)
                mask >>= 1; // go to current node's 1 left neighbor
            }
            return index;
        }

        // Given a sum, find the lowest index that satisfies sum(index) >= sum
        // This works only if all input array elements are positive so that we can do binary search for sums.
        int findLowest(int sum) {
            int mask = (1<<15);
            int index = BIT_SIZE;
            while(mask != 0 && index > 0) {
                int tmpIndex = index - mask; // try right most child node of current node
                if(sum <= array[tmpIndex])   // to the left of current node
                    index = tmpIndex;
                else                         // to the right, we should then drill down
                    sum -= array[tmpIndex];
                mask >>= 1;
            }
            return index;
        }
    }

    static public void mainI(String args[]) {
        int[] array = {2, 4, 6, 0, 0, 1, 6, 8, 2, 1, 3};
        BIT bit = new BIT();
        for(int i=0; i<array.length; i++) {
            bit.add(i+1, array[i]);
        }

        System.out.println(bit.findLowest(12));
        System.out.println(bit.findHighest(12));
        System.out.println(bit.findLowest(14));
        System.out.println(bit.findHighest(18));

        System.out.println(bit.sum(4));
        System.out.println(bit.sum(6));
        bit.add(5, 2);
        System.out.println(bit.sum(4));
        System.out.println(bit.sum(6));
    }

    /**
     * Floating Median
     * Given a list of numbers, there's a fixed size sliding window, output median in this window while it slides through the array.
     */
    static public void mainII(String args[]) {
        int[] array = {3, -4, 6, 0, 0, 0, 1, 0, -6, 8, 0, 2, -1, 3, 9, 7, 0};
        int win = 3; // window size is 3

        // normalize the array in range [min, max] to positive.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int x : array) {
            if(x > max)
                max = x;
            if(x < min)
                min = x;
        }
        min--;
        for(int i=0; i<array.length; i++)
            array[i] -= min;
        max -= min;

        BIT bit = new BIT();
        for(int i=0; i<win; i++) {
            bit.add(array[i], +1);
        }
        int median = bit.findLowest((win+1)/2) + min;
        System.out.println(median);

        for(int i=win; i<array.length; i++) {
            bit.add(array[i], +1); // insert the element that moves in
            bit.add(array[i-win], -1); // remove the element that moves out
            median = bit.findLowest((win + 1) / 2) + min;
            System.out.println(median);
        }
    }


    static class BIT2D {
        int[][] matrix;
        int height;
        int width;

        public BIT2D(int width, int height) {
            matrix = new int[height][width];
            this.height = height;
            this.width = width;
        }

        // add diff to cell matrix[y][x].
        public void add(int x, int y, int diff) {
            while(x <= width) {
                addY(x, y, diff);
                x += (x & -x);
            }
        }

        void addY(int x, int y, int diff) {
            while(y <= height) {
                matrix[y][x] += diff;
                y += (y & -y);
            }
        }

        // get sum of submatrix [0, 0] to [y, x]
        public int sum(int x, int y) {
            int sum = 0;
            while(x > 0) {
                sum += sumY(x, y);
                x -= x & -x;
            }
            return sum;
        }

        int sumY(int x, int y) {
            int sum = 0;
            while(y > 0) {
                sum += matrix[y][x];
                y -= y & -y;
            }
            return sum;
        }
    }

    static public void main(String args[]) {
        int array[][] = {
                {1, 3, 6, 1, 2, 8},
                {6, 9, 6, 3, 2, 5},
                {7, 1, 2, 0, 0, 4},
                {4, 7, 0, 0, 1, 5}};
        BIT2D bit = new BIT2D(array[0].length+1, array.length+1);
        for(int y=0; y<array.length; y++)
            for(int x=0; x<array[0].length; x++)
                bit.add(x+1, y+1, array[y][x]);
        System.out.println(bit.sum(3, 4));
        bit.add(2, 3, -4);
        System.out.println(bit.sum(3, 4));
    }
}
