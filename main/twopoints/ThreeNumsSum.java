package main.twopoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class ThreeNumsSum {
    public static List<List<Integer>> threeSum(int[] nums){

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        int len = nums.length;
        if (len < 3) {
            return result;
        }
        // 先对nums进行排序
        Arrays.sort(nums);
        // 最后一个元素都小于0，说明全是负数，不可能找到
        if(nums[len - 1] < 0){
            return result;
        }
        // 三个数里先固定选一个nums[i], 再去找 nums[left] 和 nums[right]
        for (int i = 0; i < len; i++) {
            // 第一个元素都大于0，就不用再看后面的了
            if(nums[i] > 0) {
                break;
            }
            // 去掉重复的结果，因为i-1循环中已经找过一遍了
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int left = i + 1, right = len - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) {
                        left++; // 去重
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right--; // 去重
                    }
                    left++;
                    right--;
                }
                else if (sum < 0) {
                    left++;
                } else {
                    right--; //sum > 0
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
