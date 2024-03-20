package main.dynamic;

/**
 * @author 15304
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++){
            dp[i] = Math.max(dp[i-1], nums[i-1] + dp[i-2]);
        }
        return dp[n];
    }
    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        int[] nums = {2,7,9,3,1};
        System.out.println(robber.rob(nums));

    }
}
