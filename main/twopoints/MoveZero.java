package main.twopoints;

import java.util.Arrays;

/**
 * @author 15304
 */
public class MoveZero {

    public static int[] moveZeroes(int[] nums){
        if (nums == null || nums.length == 0) {
            return null;
        }
        //双指针
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 未遇到第一个0元素时，i=j，此时没必要进行交换
                if (i > j) {
                    // 只需要把 num[i] 的值赋给 num[j] 并把原位置的值置0 实现了把交换操作换成了赋值操作
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
        return nums;
    }

    // 核心思路是 “赋值”，不是交换
    public static int[] moveZeroes2(int[] nums){
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 遍历每个元素，遇到非0元素，就赋值给noneZeroLength的位置，再 noneZeroLength++
        int noneZeroLength = 0;
        for(int num : nums){
            if(num != 0){
                nums[noneZeroLength++] = num;
            }
        }
        // 数组剩余的位置全部置0
        while (noneZeroLength < nums.length){
            nums[noneZeroLength++] = 0;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,0,4,5};
        System.out.println(Arrays.toString(moveZeroes(nums)));
        int[] nums2 = {0,1,2,0,4,5};
        System.out.println(Arrays.toString(moveZeroes2(nums2)));
    }
}
