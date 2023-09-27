package main;

import java.util.Arrays;

public class RotateArray {

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];//创建新数组 空间复杂度O(n)
        for (int i = 0; i < n; ++i) {//时间复杂度： O(n)
            newArr[(i + k) % n] = nums[i];//原数组下标为 i的元素放至新数组下标为 (i + k) % n 的位置
        }
        System.arraycopy(newArr, 0, nums, 0, n);
        System.out.println(Arrays.toString(newArr));
    }

    public static void rotateWithReverse(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);//翻转所有元素
        reverse(nums, 0, k - 1);//翻转 [0, k%(n−1)] 区间的元素
        reverse(nums, k, nums.length - 1);//翻转 [k%n, n−1] 区间的元素
        System.out.println(Arrays.toString(nums));
    }
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7}; int k = 3;
        rotate(nums, k);

        int[] nums2 = {1,2,3,4,5,6,7};
        rotateWithReverse(nums2, k);
    }
}
