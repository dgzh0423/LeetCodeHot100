package main.dynamic;

/**
 * 494. 目标和
 * @author 15304
 */
public class TargetSum {

    // 时间复杂度 O(n * positiveSum) 空间复杂度 O(n * positiveSum)
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // positiveSum + negativeSum(绝对值) = sum，target = positiveSum - negativeSum
        int positiveSum = (sum + target) / 2;
        // positiveSum 为非负整数
        if (Math.abs(target) > sum || (sum + target) % 2 == 1) {
            return 0;
        }
        // 转化为0-1背包问题 从nums前n个物品中选择一些物品，恰好装满背包容量为positiveSum的方案数
        // dp[i][j] 表示前i个数中，能恰好装满背包容量为j的方案数
        int[][] dp = new int[n+1][positiveSum +1];
        // dp[0][0] 表示前0个数中，和为0的方案数，显然不需要选择任何物品，所以为1
        dp[0][0] = 1;
        for (int i = 0; i < n; i++){
            for(int j = 0; j <= positiveSum; j++){
                // 背包容量 < 当前物品体积，无法选择当前物品
                if (j < nums[i]){
                    dp[i+1][j] = dp[i][j];
                }else {
                    dp[i+1][j] = dp[i][j] + dp[i][j-nums[i]];
                }
            }
        }
        return dp[n][positiveSum];
    }

    // 空间优化到1维数组 O(positiveSum)
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int positiveSum = (sum + target) / 2;
        if (Math.abs(target) > sum || (sum + target) % 2 == 1) {
            return 0;
        }
        int[] dp = new int[positiveSum + 1];
        dp[0] = 1;
        for (int num : nums){
            for (int j = positiveSum; j >= num; j--){
                dp[j] += dp[j-num];
            }
        }
        return dp[positiveSum];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(new TargetSum().findTargetSumWays(nums, target));
        System.out.println(new TargetSum().findTargetSumWays2(nums, target));
    }
}
