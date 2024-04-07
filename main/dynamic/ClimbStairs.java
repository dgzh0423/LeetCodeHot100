package main.dynamic;

/**
 * @author 15304
 * 动态规划的的四个解题步骤是：
 * 1. 定义子问题
 * 2. 写出子问题的递推关系 DP公式
 * 3. 确定 DP 数组的计算顺序
 * 4. 空间优化（可选）
 */
public class ClimbStairs {
    public int climbStairsDynamic(int n) {
        // dp[n] = dp[n−1] + dp[n−2]，n ≥ 3， n为正整数
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //空间复杂度优化
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        // 由于 dp[n] 只与 dp[n−1] 和 dp[n−2]有关，因此我们无须使用一个数组 dp 来存储所有子问题的解，而只需两个变量 "滚动" 前进即可
        int a = 1, b = 2, sum;
        for (int i = 3; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
    public static void main(String[] args) {
        ClimbStairs c = new ClimbStairs();
        System.out.println(c.climbStairsDynamic(4));
        System.out.println(c.climbStairs(4));
    }
}
