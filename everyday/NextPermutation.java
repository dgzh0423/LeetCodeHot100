package everyday;

import java.util.Arrays;

/**
 * @author 15304
 * leetcode 31. 下一个排列
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int n = nums.length, k = n - 1;
        // 1. 从后往前找到第一个满足 nums[k-1] < nums[k] 的位置 k
        while (k > 0 && nums[k - 1] >= nums[k]) {
            k--;
        }
        if (k == 0) {
            // 2. 如果找不到，说明是最大的全排列了（如：6 5 4 3 2 1），直接翻转数组即可
            reverse(nums, 0, n - 1);
        } else {
            // 3. 从 [k, n-1] 范围内找到第一个比 num[k-1] 大的数，交换它们
            int l = n - 1;
            while (l > k && nums[l] <= nums[k - 1]) {
                l--;
            }
            swap(nums, k - 1, l);
            // 4. 将 k 之后的数组翻转，变成升序排列
            reverse(nums, k, n - 1);
        }
    }

    private void reverse(int[] nums, int a, int b) {
        int l = a, r = b;
        while (l < r) {
            swap(nums, l++, r--);
        }
    }
    private void swap(int[] nums, int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,2,1};
        NextPermutation np = new NextPermutation();
        np.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
