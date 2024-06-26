package main.dynamic;

import java.util.Arrays;

/**
 * @author 15304
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        // dp[i] 表示凑出 amount = i 需要的最少硬币数
        int[] dp = new int[amount + 1];
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);
        // base case
        dp[0] = 0;
        // 外层 for 循环在遍历所有状态的所有取值
        for (int i = 0; i < dp.length; i++) {
            // 内层 for 循环求所有选择的最小值
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);

            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange change = new CoinChange();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(change.coinChange(coins, amount));
    }
}
