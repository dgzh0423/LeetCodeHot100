package main.hash;

import java.util.*;

/**
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 思路：1-对每个单词按字母顺序重排序      2-按单词中每个字母出现的次数重排序
 *      然后顺序相同的单词即为字母异位词
 * @author 15304
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char [] array = str.toCharArray();
            //对每个单词按字母顺序重排序
            Arrays.sort(array);
            //重排序后的单词作为key, 字母异位词在重排序后key一定是相同的
            String key = new String(array);
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
