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
        // dp[i]表示以 nums[i] 结尾的最长递增子序列长度
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
            //保存dp[]中的最大值，也就是最长递增子序列长度
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 动态规划 + 二分查找
     * <a href="https://leetcode.cn/problems/longest-increasing-subsequence/solutions/14796/dong-tai-gui-hua-she-ji-fang-fa-zhi-pai-you-xi-jia/?envType=study-plan-v2&envId=top-100-liked">...</a>
     */
    public int lengthOfLISPlus(int[] nums) {
        // tails 类似于牌堆
        // 若给的nums[]就是递增的，则最后就会有 n 个牌堆
        int[] tails = new int[nums.length];
        // 初始牌堆数为 0
        int res = 0;
        // 由于牌堆顶的牌是有序的，可以通过二分查找来确定每张牌应该放在哪个牌堆顶
        for(int num : nums) {
            int left = 0, right = res;
            while(left < right) {
                int mid = (left + right) / 2;
                if(tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 没找到合适的牌堆，新建一个牌堆
            if(res == right) {
                res++;
            }
            // 把这张牌放到牌堆顶
            tails[left] = num;
        }
        // 最后的牌堆个数就是要求的最长递增子序列长度
        return res;
    }

    public static void main(String[] args) {
        LongestIncreaseSubsequence longest = new LongestIncreaseSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(longest.lengthOfLIS(nums));
        System.out.println(longest.lengthOfLISPlus(nums));
    }
}
