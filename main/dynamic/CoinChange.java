package main.dynamic;

import java.util.Arrays;

/**
 * @author 15304
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // dp[amount] = min(dp[amount], dp[amount - coin] + 1) for coin in coins if coin <= amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange change = new CoinChange();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(change.coinChange(coins, amount));
    }
}
