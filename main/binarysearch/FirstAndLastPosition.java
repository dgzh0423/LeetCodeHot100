package main.binarysearch;

import java.util.Arrays;

/**
 * @author 15304
 */
public class FirstAndLastPosition {
    /* 二分查找插入点（存在重复元素） */
    int binarySearchInsertion(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {
        int start = binarySearchInsertion(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        // 如果 start 存在，那么 end 必定存在
        // ⭐通过确定 target + 1 的位置 来间接确定 end的位置⭐
        int end = binarySearchInsertion(nums, target + 1) - 1;
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        FirstAndLastPosition flp = new FirstAndLastPosition();
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        System.out.println(Arrays.toString(flp.searchRange(nums, target)));
    }
}
