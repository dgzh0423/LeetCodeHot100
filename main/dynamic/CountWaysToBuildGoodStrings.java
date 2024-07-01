package main.dynamic;

/**
 * 2466. 统计构造好字符串的方案数
 * @author 15304
 */
public class CountWaysToBuildGoodStrings {

    public static final int MOD = 1_000_000_007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int count = 0;
        // dp[i] 表示长度为 i 的好字符串的方案数
        int[] dp = new int[high + 1];
        dp[0] = 1;
        for (int i = 1; i <= high; i++) {
            // 若字符串长度 >= zero，则可以在长度为 i - zero 的字符串后面拼接 zero 个 0
            if (i >= zero){
                dp[i] = (dp[i] + dp[i - zero]) % MOD;
            }
            // 同理
            if (i >= one){
                dp[i] = (dp[i] + dp[i - one]) % MOD;
            }
            // 两种方案数相加 dp[i] = dp[i - zero] + dp[i - one]
            if (i >= low){
                count = (count + dp[i]) % MOD;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountWaysToBuildGoodStrings countWaysToBuildGoodStrings = new CountWaysToBuildGoodStrings();
        System.out.println(countWaysToBuildGoodStrings.countGoodStrings(3, 3, 1, 1));
    }
}

