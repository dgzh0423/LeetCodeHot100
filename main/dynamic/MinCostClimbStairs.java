package main.dynamic;

/**
 * 746.使用最小花费爬楼梯
 * @author 15304
 */
public class MinCostClimbStairs {

    /**
     * 从下标为 0 或下标为 1 的台阶开始爬楼梯，每次可以爬 1 或 2 个台阶，但需要支付 cost[i] 的费用
     * @param cost cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用
     * @return 爬到 n=cost.length 阶台阶的最低花费。
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // dp[i] 表示爬到第 i 个台阶的最低花费
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n ; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public int minCostClimbingStairs2(int[] cost) {
        // 观察到状态转移方程只与 dp[i - 1] 和 dp[i - 2] 有关，因此可以使用两个变量"滚动更新"
        int dp_i_2 = 0, dp_i_1 = 0;
        for (int i = 1; i < cost.length; i++) {
            int dp_i = Math.min(dp_i_1 + cost[i], dp_i_2 + cost[i - 1]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(new MinCostClimbStairs().minCostClimbingStairs(cost));
        System.out.println(new MinCostClimbStairs().minCostClimbingStairs2(cost));
    }
}
