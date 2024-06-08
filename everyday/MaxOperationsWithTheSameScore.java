package everyday;

import java.util.Arrays;

/**
 * 3040.相同分数的最大操作数目 II
 * @author 15304
 */
public class MaxOperationsWithTheSameScore {

    private int[] nums;
    private int[][] memo;
    // done = true 表示在某一种情况中，得出的最大操作数已经是 n/2 了，不需要再继续看其他的情况下的最大操作数了，可以提前返回了
    private boolean done;

    public int maxOperations(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.memo = new int[n][n];

        // 以开头两个数的和作为每次操作的分数
        int res1 = helper(2, n - 1, nums[0] + nums[1]);
        // 以最后两个数的和作为每次操作的分数
        int res2 = helper(0, n - 3, nums[n - 2] + nums[n - 1]);
        // 以第一个数和最后一个数的和作为每次操作的分数
        int res3 = helper(1, n - 2, nums[0] + nums[n - 1]);
        // 返回三种情况中最大的操作数
        return Math.max(Math.max(res1, res2), res3) + 1;
    }

    public int helper(int i, int j, int target) {
        if (done) {
            return 0;
        }
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(i, j, target);
    }

    // 以target为分数的情况下，计算在[i, j]范围内的最大操作数
    public int dfs(int i, int j, int target) {
        if (done) {
            return 0;
        }
        if (i >= j) {
            done = true;
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int ans = 0;
        if (nums[i] + nums[i + 1] == target) {
            ans = Math.max(ans, dfs(i + 2, j, target) + 1);
        }
        if (nums[j - 1] + nums[j] == target) {
            ans = Math.max(ans, dfs(i, j - 2, target) + 1);
        }
        if (nums[i] + nums[j] == target) {
            ans = Math.max(ans, dfs(i + 1, j - 1, target) + 1);
        }
        memo[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,6,1,4};
        System.out.println(new MaxOperationsWithTheSameScore().maxOperations(nums));
    }
}
