package main;

public class SubarrayWithMaxSum {
    public static int maxSubArray(int[] nums) {

        // preSum保存以第 i-1 个数结尾的连续子数组的最大和
        int max = nums[0], preSum = nums[0];

        //例如：以-2结尾的连续子数组只有 [-2] = num[0] = preSum,初始max为-2，
        //     以1结尾的连续子数组有[-2,1] = preSum + nums[1]和 [1]，max为1
        //     以-3结尾的连续子数组有[-2,1,-3], [1,-3], [-3],max为1
        for (int i = 1; i < nums.length; i++){
            preSum = Math.max(preSum + nums[i], nums[i]);
            max = Math.max(preSum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums ={-2,1,-3,4,-1,2,1,-5,4};
        int [] nums2 = {5,4,-1,7,8};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray(nums2));
    }
}
