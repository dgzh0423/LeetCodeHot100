package main.dynamic;

/**
 * 买卖股票的最佳时机
 * @author 15304
 */
public class BestTimeToBuyAndSellStock {

    // 122. 买卖股票的最佳时机 II，不限制操作次数(买入卖出为一次操作)
    public int maxProfitII(int[] prices) {
        int n = prices.length;
        // 第 i 天 有两种状态：不持有/持有股票
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 空间优化：dp[i] 只会从 dp[i−1] 转移得来，因此第一维可以去掉
        // int tmp = dp[0];
        // dp[0] = Math.max(dp[0], dp[1] + prices[i]);
        // dp[1] = Math.max(dp[1], tmp - prices[i]);
        for (int i = 1; i < n; i++) {
            // 第 i 天不持有，有两种情况：1.第 i-1 天也不持有；2.第 i-1 天持有，第 i 天卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第 i 天持有，有两种情况：1.第 i-1 天持有，且没卖出；2.第 i-1 天不持有，第 i 天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    // 123. 买卖股票的最佳时机 III，最多2次操作
    public int maxProfitIII(int[] prices) {
        int n = prices.length;
        // dp[i][j][k]: 第 i 天，已经买入 j 次(0/1/2)，状态为 k (不持有/持有)的最大利润
        int[][][] dp = new int[n][3][2];
        // 初始状态：dp[0][0][1], dp[0][1][0], dp[0][2][0] 这三种状态无意义，可设为默认值0
        dp[0][1][1] = -prices[0];
        // 由于必须在再次购买前出售掉之前的股票，所以dp[0][2][1]表示 第0天的股票买入，卖出，又买入，此时利润也为-prices[0]
        dp[0][2][1] = -prices[0];
        // 同理 第 i 天的状态只与前一天的状态有关，可以优化空间
        for (int i = 1; i < n; i++){
            // 两种情况：1.昨天就持有股票，且到今天也没卖出 2.之前都没有买入，今天买入
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            // 两种情况：1.昨天和今天不持有股票，且没买入 2.昨天持有股票，今天卖出
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            // 两种情况：1.昨天就持有股票，且到今天也没卖出 2.昨天不持有股票，今天买入
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            // 两种情况：1.昨天和今天不持有股票，且不能再买入 2.昨天持有股票，今天卖出
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        }
        // 取交易一次或交易两次的最大利润
        return Math.max(dp[n - 1][1][0], dp[n - 1][2][0]);
    }

    // 188. 买卖股票的最佳时机 IV，最多k次操作
    public int maxProfitIV(int k, int[] prices) {
        int n = prices.length;
        // dp[i][j][k]: 第 i 天，已经买入 j 次，状态为 k (不持有/持有)的最大利润
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i <= k; i++){
            // 第0天，对于任意交易次数，利润只有两种情况：不持有/持有股票
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        // 同理 第 i 天的状态只与前一天的状态有关，可以优化空间
        for (int i = 1; i < n; i++){
            for (int j = 1; j <= k; j++){
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    // 309. 买卖股票的最佳时机含冷冻期，不限制操作次数，但不能在卖出后的第二天再买入
    public int maxProfitWithCooldown(int[] prices) {
        int n = prices.length;
        // 状态 j 分三种情况：1.不持有(没有卖出) 2.持有 3.不持有(卖出之后)
        int[][] dp = new int[n][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        // 同理 第 i 天的状态只与前一天的状态有关，可以优化空间
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[n - 1][0], dp[n - 1][2]);
    }

    // 714. 买卖股票的最佳时机含手续费，不限制操作次数，但每次操作需要手续费
    public int maxProfitWithFee(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        // 同理 第 i 天的状态只与前一天的状态有关，可以优化空间
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        BestTimeToBuyAndSellStock time = new BestTimeToBuyAndSellStock();
        System.out.println(time.maxProfitII(prices));
        System.out.println(time.maxProfitIII(prices));
        System.out.println(time.maxProfitIV(2, prices));
        System.out.println(time.maxProfitWithCooldown(prices));
        System.out.println(time.maxProfitWithFee(prices, 2));
    }
}
