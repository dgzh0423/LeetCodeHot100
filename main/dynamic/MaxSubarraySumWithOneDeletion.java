package main.dynamic;

import java.util.Arrays;

/**
 * 1186. 删除一次得到子数组最大和
 * @author 15304
 */
public class MaxSubarraySumWithOneDeletion {

    /**
     *  dp[i][0] = Math.max(dp[i-1][0], 0) + arr[i];
     *  dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]);
     *  观察到，当 i = 0 时， 无法计算 dp[i-1][j] = dp[-1][j] = -∞
     *  因此，把dp长度+1，用dp[i][j] 表示 dp[i-1][j]
     */
    public int maximumSum(int[] arr) {
        int res = Integer.MIN_VALUE;
        int n = arr.length;
        // 表示子数组的右端点是 arr[i]，删/不删一个数字的情况下，子数组元素和的最大值。
        int[][] dp = new int[n + 1][2];
        Arrays.fill(dp[0], Integer.MIN_VALUE / 2);
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = Math.max(dp[i][0], 0) + arr[i];
            dp[i + 1][1] = Math.max(dp[i][0], dp[i][1] + arr[i]);
            res = Math.max(res, Math.max(dp[i + 1][0], dp[i + 1][1]));
        }
        return res;
    }

    public int maximumSum2(int[] arr) {
        int ans = Integer.MIN_VALUE / 2;
        int f0 = ans;
        int f1 = ans;
        for (int x : arr) {
            // 注意必须先算f1，再算f0，因为f1会用到f0
            f1 = Math.max(f1 + x, f0);
            f0 = Math.max(f0, 0) + x;
            ans = Math.max(ans, Math.max(f0, f1));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,-2,0,3};
        System.out.println(new MaxSubarraySumWithOneDeletion().maximumSum(arr));
        System.out.println(new MaxSubarraySumWithOneDeletion().maximumSum2(arr));
    }
}
