package main.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 15304
 */
public class LongestSubstring {

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
     * @param s 给定一个字符串
     * @return lengthOfLongestSubstring
     */
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        // key为字符, value为字符的index
        Map<Character, Integer> map = new HashMap<>();

        for (int end = 0, start = 0; end < s.length(); end++) {
            char element = s.charAt(end);
            //遇到重复的字符时，start需要从该重复的字符的后一个字符开始
            if (map.containsKey(element)) {
                start = Math.max(map.get(element) + 1, start);
            }
            //计算区间的长度
            res = Math.max(res, end - start + 1);
            //更新当前字符的index下标
            map.put(element, end);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
