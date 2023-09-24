package main;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualK {

    /*
       前缀和数组 时间复杂度为O(n^2)，空间复杂度O(n)
     */
    public static int subarraySum(int[] nums, int k) {

        int len = nums.length;
        //构建前缀和数组，以快速计算区间和
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];//如：preSum[1] = nums[0], preSum[2] = preSum[1] + nums[1].
        }

        int count = 0;

        //时间复杂度为O(n^2)，空间复杂度O(n)
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移，因为nums[2]到nums[4]的和等于preSum[5]-preSum[2]
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }


    /*
       前缀和 + hashmap 时间复杂度为O(n)，空间复杂度O(n)
     */
    public static int subarraySumWithMap(int[] nums, int k){
        if (nums.length == 0) return 0;

        //map存放前缀和以及该前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);//例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和 1+1+0=2的情况

        int count = 0, preSum = 0;
        for (int i: nums) {
            preSum += i;
            //检查map中是否含有 preSum - k，有的话 count加上 preSum - k出现的次数
            if (map.containsKey(preSum - k)){
                count += map.get(preSum -k);
            }
            //更新map
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int k = 2;
        System.out.println(subarraySum(nums, k));
        System.out.println(subarraySumWithMap(nums, k));

    }
}
