package main;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        // key为字符, value为字符的index
        Map<Character, Integer> map = new HashMap<>();

        for (int end = 0, start = 0; end < s.length(); end++) {
            char element = s.charAt(end);
            //遇到重复的字符时，start需要从该重复的字符的后一个字符开始(index+1)
            if (map.containsKey(element)) {
                start = Math.max(map.get(element) + 1, start);
            }
            //计算区间长度
            res = Math.max(res, end - start + 1);
            //
            map.put(element, end);
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
