package everyday;

import java.util.Arrays;

/**
 * 3011.判断一个数组是否可以变为有序
 * @author 15304
 */
public class FindIfArrayCanBeSorted {

    // 时间复杂度 O(nlogn) 空间复杂度 O(1)
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        // 将数组按照 nums[i] 的二进制表示中 1 的个数进行分组 [start,i]，然后组内排序
        for (int i = 0; i < n;) {
            int start = i;
            int ones_count = Integer.bitCount(nums[i]);
            // do ... while(表达式) do至少执行一次，直到表达式为 false
            do {
                i++;
            } while (i < n && Integer.bitCount(nums[i]) == ones_count);
            Arrays.sort(nums, start, i);
        }
        // 判断整个数组是否为升序
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean canSortArray2(int[] nums) {
        int n = nums.length;
        // 将数组按照 nums[i] 的二进制表示中 1 的个数进行分组 [start,i]，并且组间也要有序，才能实现非递减
        int preMax = 0;
        for (int i = 0; i < n;) {
            int curMax = 0;
            int ones_count = Integer.bitCount(nums[i]);
            while (i < n && Integer.bitCount(nums[i]) == ones_count){
                if (nums[i] < preMax) {
                    return false;
                }
                curMax = Math.max(curMax, nums[i]);
                i++;
            }
            preMax = curMax;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3,16,8,4,2};
        int[] nums2 = {8,4,2,30,15};
        System.out.println(new FindIfArrayCanBeSorted().canSortArray(nums));
        System.out.println(new FindIfArrayCanBeSorted().canSortArray2(nums2));
    }
}
