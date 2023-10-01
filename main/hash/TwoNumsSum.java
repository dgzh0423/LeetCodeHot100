package main.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 思路：关键在于如何找到target - nums[i]
 * map的key存数值，value存该值的数组下标
 */
public class TwoNumsSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i){
            if (map.containsKey(target - nums[i])){
                return new int[] { map.get(target - nums[i]), i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }
    public static void main(String[] args) {
//        int[] nums = {2,7,11,15};
        int[] nums = {3, 3, 3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
