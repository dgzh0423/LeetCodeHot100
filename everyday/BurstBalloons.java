package everyday;

/**
 * 312. 戳气球 hard
 * @author 15304
 */
public class BurstBalloons {

    // 时间复杂度 O(n^3) 空间复杂度 O(n^2)
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        // 添加两侧的虚拟气球
        points[0] = points[n + 1] = 1;
        System.arraycopy(nums, 0, points, 1, n);
        // dp[i][j] = x 表示，戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高分数为 x
        // base case：对于气球 i，i∈[0, n+1]，如果气球 j <= i+1，那么气球 i 和气球 j 之间就不会有气球，即 dp[i][j] = 0
        int[][] dp = new int[n + 2][n + 2];
        // i 应该从下往上
        for (int i = n - 1; i >= 0; i--) {
            // j 应该从左往右
            for (int j = i + 2; j < n + 2; j++) {
                // 正向思考：穷举戳破气球 i 和气球 j 之间的气球，类似回溯算法
                // 反向思考：最后戳破的气球是哪个？假设为k，那么就先戳破 (i,k) 和 (k,j) 之间的所有气球，最后戳破气球 k
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(
                            dp[i][j],
                            dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]
                    );
                }
            }
        }
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8};
        System.out.println(new BurstBalloons().maxCoins(nums));
    }
}
