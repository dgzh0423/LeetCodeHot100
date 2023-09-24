package main;

import java.util.*;

/** 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 思路：1-对每个单词按字母顺序重排序      2-按单词中每个字母出现的次数重排序
 *      然后顺序相同的单词即为字母异位词
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char [] array = str.toCharArray();
            Arrays.sort(array);//对每个单词按字母顺序重排序
            String key = new String(array);//重排序后的单词作为key
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }
}
