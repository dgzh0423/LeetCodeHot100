package main.array;

import java.util.Arrays;

/**
 * @author 15304
 */
public class MultipleExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }
        int[] ans = new int[len];
        ans[0] = 1;
        int tmp = 1;
        //时间复杂度： O(n) 空间复杂度O(1)
        for (int i = 1; i < len; i++) {
            //先求每个位置的前缀积，类似前缀和
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            //再求每个位置的后缀积
            tmp *= nums[i + 1];
            //同时合并结果，前缀积 * 后缀积
            ans[i] *= tmp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
}
