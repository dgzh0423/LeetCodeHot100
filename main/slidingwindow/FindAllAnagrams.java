package main.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p){

        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if (n < m) {
            return res;
        }

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        //1.用int[26]来保存p中字母出现的次数和s中前m个字母出现的次数
        for (int i = 0; i < m; i++) {
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        //2.若sCnt和pCnt相等，则找到第一个异位词索引0
        if(Arrays.equals(sCnt, pCnt)){
            res.add(0);
        }
        //3.从m处继续遍历s,每次增加一个新字母，都要去除一个旧字母
        for (int i = m; i < n ; i++) {
            sCnt[s.charAt(i) - 'a']++;
            sCnt[s.charAt(i - m) - 'a']--;
            if(Arrays.equals(sCnt, pCnt)){
                res.add(i - m + 1);
            }
        }
        return res;
    }

    public static List<Integer> findAnagramsWithTwoPoints(String s, String p){
        int n = s.length(), m = p.length();
        List<Integer> res = new ArrayList<>();
        if(n < m) {
            return res;
        }

        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        // 记录字符串p中每个字母和出现的次数
        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a'] ++;
        }

        //left和right表示滑动窗口在字符串s中的索引
        int left = 0;
        for (int right = 0; right < n; right++){
            //curLeft, curRight表示字符串s中索引为left和right的字符在数组中的索引
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            // 当right指向的字符超过p中出现的次数，或者根本不在p中出现过，就需要更新left的位置
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }
            //当滑动窗口的长度等于p的长度时，这时的s子字符串就是p的异位词。
            if(right - left + 1 == m){
                res.add(left);
            }
        }
        return res;

    }


    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s,p));
        System.out.println(findAnagramsWithTwoPoints(s,p));
    }
}
