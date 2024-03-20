package main.dynamic;

import java.util.Arrays;

/**
 * @author 15304
 */
public class LongestIncreaseSubsequence {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        // dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                // 严格递增 nums[i] 才可以接在 nums[j] 之后
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestIncreaseSubsequence longest = new LongestIncreaseSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(longest.lengthOfLIS(nums));
    }
}
