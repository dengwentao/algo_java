package Challenges;

/**
 * Created by wentaod on 8/9/16.
 */
public class FindKth {
    static public void main(String args[]) {
        int[] nums = {3, 65, 11, 11, 8, 29, 41, 21, 4, 6, 11, 11};
        int k = 9;
        //System.out.println(partition(nums, 0, nums.length - 1));
        System.out.println(findKth(nums, k));
        for (int x : nums)
            System.out.print(x + ", ");
    }

    static int findKth(int[] nums, int k) {
        if (nums.length - 1 < k)
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
    static int partition(int[] nums, int i, int j) {
        int p = (int)((j - i) * Math.random()) + i;
        int pivot = nums[p];
        swap(nums, p, j);

        while (i < j) {
            while (i < j && nums[i] < pivot)
                i ++;
            if (i == j)
                break;
            nums[j --] = nums[i];
            while (i < j && nums[j] >= pivot) {
                j --;
            }
            if (i == j)
                break;
            nums[i ++] = nums[j];
        }

        nums[i] = pivot;
        return i;
    }

}
