package main.binarysearch;

/**
 * @author 15304
 */
public class SearchRotation {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                return mid;
            }
            // 先根据 nums[mid] 与 nums[left] 的关系判断 mid 是在左段还是右段 
            if (nums[mid] >= nums[left]) {
                // 再判断 target 是在 mid 的左边还是右边，从而调整左,右边界 left 和 right
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchRotation sr = new SearchRotation();
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(sr.search(nums, target));
    }
}

