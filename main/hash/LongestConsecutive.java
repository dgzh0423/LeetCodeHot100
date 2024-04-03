package main.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 思路：关键在什么情况下才进行长度统计（当不存在num-1时）
 * @author 15304
 */

public class LongestConsecutive {
    public static int solution(int[] nums) {
        // 1.去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 2.循环查找最长的连续序列
        int longestLength = 0;
        for (int num : set) {
            // 当不存在num-1时，才进行循环统计长度
            if (!set.contains(num - 1)){
                int currentNum = num;
                int currentLength = 1;
                while (set.contains(currentNum + 1)){
                    currentNum += 1;
                    currentLength += 1;
                }
                longestLength = Math.max(currentLength, longestLength);
            }
        }
        // 3.返回最长的连续序列
        return longestLength;
    }

    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(solution(nums));
    }
}
