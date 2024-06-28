package main.dynamic;

import java.util.Arrays;

/**
 * 2742.给墙壁刷油漆
 * @author 15304
 */
public class PaintingTheWalls {

    /**
     * 转化为0-1背包问题：背包容量为n，物品价值为cost[i]，物品重量为time[i]+1，求从 n 个物品中选择重量和至少为 n 的物品，价值和最小是多少？
     * <a href="https://leetcode.cn/problems/painting-the-walls/solutions/2312808/xuan-huo-bu-xuan-de-dian-xing-si-lu-by-e-ulcd/?envType=daily-question&envId=2024-06-28"/a>
     * @param cost 付费刷油漆的开销，免费刷油漆的开销为0
     * @param time 付费刷油漆的时间，免费刷油漆的时间为1
     * @return 刷完n个墙最小的开销和
     */
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        // dp[i][j]表示对于前i个物品，凑出重量和为j的最小价值和
        int[][] dp = new int[n][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return dfs(n - 1, n, cost, time, dp);
    }

    private int dfs(int i, int j, int[] cost, int[] time, int[][] dp) {
        // 凑出重量和为0，也就是不选任何物体，重量和就是0
        if (j <= 0) {
            return 0;
        }
        //没有任何物体可选，但是要凑出重量和j，返回一个不可能的价值和（越大越好）
        if (i < 0) {
            return Integer.MAX_VALUE / 2;
        }
        // 防止重复计算
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        // 做选择：选或者不选第i个物品, 第i个物品的重量为time[i] + 1
        int res1 = dfs(i - 1, j - time[i] - 1, cost, time, dp) + cost[i];
        int res2 = dfs(i - 1, j, cost, time, dp);
        // 返回两种选择中的最小值
        return dp[i][j] = Math.min(res1, res2);
    }

    // 空间复杂度优化为 O(n)
    public int paintWalls2(int[] cost, int[] time) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n; j > 0; j--) {
                dp[j] = Math.min(dp[j], dp[Math.max(j - time[i] - 1, 0)] + cost[i]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int[] cost = {1, 2, 3, 2};
        int[] time = {1, 2, 3, 2};
        System.out.println(new PaintingTheWalls().paintWalls(cost, time));
        System.out.println(new PaintingTheWalls().paintWalls2(cost, time));
    }
}
