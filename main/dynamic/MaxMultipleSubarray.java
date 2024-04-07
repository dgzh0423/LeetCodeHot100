package main.dynamic;

/**
 * @author 15304
 */
public class MaxMultipleSubarray {
    public int maxProduct(int[] nums) {

        int len = nums.length;
        if (len == 1){
            return nums[0];
        }

        // maxDp[i] 表示：以 nums[i] 结尾的连续子序列的乘积的最大值
        // minDp[i] 表示：以 nums[i] 结尾的连续子序列的乘积的最小值
        int[] maxDp = new int[len];
        int[] minDp = new int[len];

        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        // dp[i] = max(dp[i - 1] * nums[i], nums[i])
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                maxDp[i] = Math.max(nums[i], maxDp[i - 1] * nums[i]);
                minDp[i] = Math.min(nums[i], minDp[i - 1] * nums[i]);
            } else {
                maxDp[i] = Math.max(nums[i], minDp[i - 1] * nums[i]);
                minDp[i] = Math.min(nums[i], maxDp[i - 1] * nums[i]);
            }
        }

        int res = maxDp[0];
        for (int i = 1; i < len; i++){
            res = Math.max(res, maxDp[i]);
        }
        // 返回 maxDp[]的最大值
        return res;
    }

    public static void main(String[] args) {
        MaxMultipleSubarray max = new MaxMultipleSubarray();
        int[] nums = {2,3,2,4};
        System.out.println(max.maxProduct(nums));
    }
}
