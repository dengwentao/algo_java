package Challenges;

/**
 * Created by wentaod on 8/9/16.
 */
public class FindKth {
    static public void main(String args[]) {
        int[] nums = {3, 65, 11, 11, 8, 29, 41, 21, 4, 6, 11, 11};
        int k = 0;
        System.out.println("Find " + k + "-th: " + findKth(nums, k));
        for (int x : nums)
            System.out.print(x + ", ");
    }

    // return the element which is k-th
    static public int findKth(int[] nums, int k) {
        if (k <0 || nums.length - 1 < k)
            return -1;
        return nums[findKth(nums, k, 0, nums.length - 1)];
    }

    // return index of kth in range [i, j]
    static int findKth(int[] nums, int k, int i, int j) {
        if (i == j) {
            return i;
        }

        int p = partition(nums, i, j);
        if (p < k)
            return findKth(nums, k, p + 1, j);
        else if (p > k)
            return findKth(nums, k, i, p - 1);
        else
            return p;
    }

    //
    static void swap(int[] nums, int i, int j) {
        int x = nums[i];
        nums[i] = nums[j];
        nums[j] = x;
    }

    // partition [i, j] so that pivot is in the correct place, and left elements less than pivot and right larger.
    // return the index of pivot
    static public int partition(int[] nums, int i, int j) {
        if (i == j)
            return i;

        int p = (int)((j - i + 1) * Math.random()) + i;
        int pivot = nums[p];
        swap(nums, p, j);
        p = j;

        while (i < j) {
            while (nums[i] < pivot)
                i ++;
            while (i < j && nums[j] >= pivot)
                j --;
            swap(nums, i, j);
        }
        swap(nums, i, p);

        return i;
    }

}
