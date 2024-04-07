package main.dynamic;

/**
 * @author 15304
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }

        // 这里的 target 就是要装满的背包容量
        int target = sum / 2;
        // dp[i][j] 表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j
        // dp[0][0] = false，因为 nums 都是正整数
        boolean[][] dp = new boolean[len][target + 1];

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            // 若 nums[] 中存在 nums[i] > target, 则不可能分成两个和相等的子集
            if(nums[i] > target){
                return false;
            }

            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    // 1. 不选择 num[i], 检查 dp[i - 1][j]
                    // 2. 选择 num[i], 检查 dp[i - 1][j - nums[i]]
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    /**
     * 0-1背包问题 <a href="https://www.hello-algo.com/chapter_dynamic_programming/knapsack_problem/">...</a>
     */
    public boolean canPartitionPlus(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        int[] dp = new int[target + 1];

        // 对于每个物品num， 它的重量是num， 价值也是num
        for (int num : nums) {
            // 倒序
            for (int j = target; j >= num; j--) {
                dp[j] = Math.max(dp[j], dp[j - num] + num);
            }
            if (dp[target] == target) {
                return true;
            }
        }
        return dp[target] == target;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum partition = new PartitionEqualSubsetSum();
        int[] nums = {1,5,10,6};
        System.out.println(partition.canPartition(nums));
        System.out.println(partition.canPartitionPlus(nums));
    }
}
