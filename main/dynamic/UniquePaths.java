package main.dynamic;

import java.util.Arrays;

/**
 * @author 15304
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // dp[i][j] 是到达位置（i, j） 的最多路径数
        int[][] dp = new int[m][n];
        // 第一行 dp[0][j] 或者第一列 dp[i][0]，由于都是在边界,只能朝一个方向走，所以路径数为 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 要到达位置（i, j），只能从上面（i-1, j）或者左边 (i, j-1) 过来
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    public int improveUniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                // 当计算完一行，进入下一行时，对于下一行来说，此时的dp[j]保存的实际就是 dp[i-1][j]，此时只要再加上dp[i][j-1]更新即可
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        UniquePaths unique = new UniquePaths();
        int m = 3, n = 7;
        System.out.println(unique.uniquePaths(m, n));
        System.out.println(unique.improveUniquePaths(m, n));
    }
}
