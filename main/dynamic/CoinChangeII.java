package main.dynamic;

/**
 * 518. 零钱兑换 II
 * @author 15304
 */
public class CoinChangeII {

    public int change(int amount, int[] coins){
        int n = coins.length;
        // dp[i][j]表示若只使用 coins 中的前 i 个（i 从 1 开始计数）硬币的面值，若想凑出金额 j，有 dp[i][j] 种凑法。
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 对于第i枚硬币coins[i-1]，要凑到金额 j，我们有两种选择：选或者不选
                if (j - coins[i-1] >= 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int changePlus(int amount, int[] coins){
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // dp 数组的转移只和 dp[i][..] 和 dp[i-1][..] 有关，所以可以优化空间
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                // 对于当前硬币coin，要凑到amount = j，我们有两种选择：选或者不选
                // 如果选择当前硬币，则相当于先凑出金额 j - coin，再加上一枚当前硬币
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[amount];
    }


    public static void main(String[] args) {
        CoinChangeII cc = new CoinChangeII();
        int amount = 5;
        int [] coins = {1,2,5};
        System.out.println(cc.change(amount, coins));
        System.out.println(cc.changePlus(amount, coins));
    }
}
