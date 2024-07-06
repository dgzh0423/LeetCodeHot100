package main.dynamic;

/**
 * 3101.交替子数组计数
 * @author 15304
 */
public class CountAlternatingSubarrays {

    /**
     * @param nums 二进制数组，只有0或1
     * @return 交替子数组的数量，一个子数组中不存在两个相邻元素的值相同
     */
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        // dp[i]表示以nums[i]结尾的交替子数组的数量
        // long[] dp = new long[n];
        // nums[0]一定是交替子数组
        // dp[0] = 1;
        int cnt = 1;
        long ans = cnt;
        for (int i = 1; i < n; i++){
            // 可以把 nums[i] 加到所有以 i−1 结尾的交替子数组的末尾，且 nums[i] 本身也是一个交替子数组
            if (nums[i] != nums[i - 1]){
                // 观察可知，dp[i] 只与 dp[i - 1] 有关，所以可以优化空间，用一个变量 cnt 表示 dp[i]
                cnt++;
            }else {
                cnt = 1;
            }
            ans += cnt;
        }
        return ans;
    }

    public long countAlternatingSubarrays2(int[] nums) {
        long res = 0, cur = 0;
        // pre = -1 是因为 nums[0]一定是交替子数组，保证初始化 cur = 1
        int pre = -1;
        for (int i : nums) {
            cur = (pre != i) ? cur + 1 : 1;
            pre = i;
            res += cur;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,0};
        System.out.println(new CountAlternatingSubarrays().countAlternatingSubarrays(nums));
        System.out.println(new CountAlternatingSubarrays().countAlternatingSubarrays2(nums));
    }
}

