package everyday;

/**
 * 2786.访问数组中的位置使分数最大
 * @author 15304
 */
public class VisitPositionWithMaxScore {

    // 自底向上 时间复杂度 O(n), 空间复杂度 O(1)
    public long maxScore(int[] nums, int x) {
        // 默认nums[0]是必选的
        long res = nums[0];
        // dp[0]表示最后移动的元素为偶数时得分的最大值 ，dp[1]表示最后移动的元素为奇数时得分的最大值
        long[] dp = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        // 根据nums[0]的奇偶性，初始化dp[0]或者dp[1]
        dp[nums[0] % 2] = nums[0];
        // 遍历nums数组
        for (int i = 1; i < nums.length; i++) {
            // 计算当前元素的奇偶性
            int parity = nums[i] % 2;
            // 选择当前元素：奇偶性相同，则加上当前元素即可，奇偶性不同，则还要减去x
            long cur = Math.max(dp[parity] + nums[i], dp[1 - parity] + nums[i] - x);
            // 更新最大分数
            res = Math.max(res, cur);
            // 更新dp[parity]
            dp[parity] = Math.max(dp[parity], cur);
        }
        return res;
    }

    //自顶向下
    public long maxScore2(int[] nums, int x) {
        long[] dp = new long[2];
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = nums[i] % 2;
            dp[j] = Math.max(dp[j] + nums[i], dp[1 - j] + nums[i] - x);
        }
        //因为nums[0]是必选的，故最后结果应该返回dp[nums[0] % 2]
        return dp[nums[0] % 2];
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 1, 9, 2};
        System.out.println(new VisitPositionWithMaxScore().maxScore(nums, 5));
        System.out.println(new VisitPositionWithMaxScore().maxScore2(nums, 5));
    }
}
