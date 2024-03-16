package main.binarysearch;

/**
 * @author 15304
 */
public class FindMinimumInRotation {
    //给定一个元素值互不相同，且旋转过n次的数组 nums，找出数组中的最小元素
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 中值 > 右值，说明最小值在右半边，收缩左边界
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // 中值 < 右值，最小值在左半边(包括中值)，收缩右边界
            else {
                //中值也可能是最小值，故 right = mid 而不是 mid - 1
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotation f = new FindMinimumInRotation();
        int[] nums = {3,4,5,1,2};
        System.out.println(f.findMin(nums));
    }
}
