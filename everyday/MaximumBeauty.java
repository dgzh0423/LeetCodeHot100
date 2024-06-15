package everyday;

import java.util.Arrays;

/**
 * 2779. 数组的最大美丽值
 * @author 15304
 */
public class MaximumBeauty {

    // 时间复杂度：O(nlogn)，主要是排序，空间复杂度：O(1)
    public int maximumBeauty(int[] nums, int k) {
        // 先对数组进行升序排序，每个元素对应一个取值范围[x - k, x + k]
        Arrays.sort(nums);
        int res = 0;
        // 对于窗口[left, right], 由相等元素组成的最长子序列 等价于 窗口内的任意一个元素的取值区间和其他元素的取值区间都有交集
        // 又因为 窗口[left, right] 内的元素是有序的，故只需要看窗口内的最大值与最小值的取值区间是否有交集即可
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // 对于窗口内的元素，如果窗口内的最大值与最小值之间的差值大于2k，则需要移动窗口的左边界
            while (nums[right] - nums[left] > 2 * k) {
                left++;
            }
            // 更新当前窗口内的最大美丽值
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4,6,1,2};
        System.out.println(new MaximumBeauty().maximumBeauty(nums, 2));
    }
}
