package main.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for(int i = 0; i < len; i++){
            //回文子串有奇数型和偶数型 例如：“aba”, "abba"
            prePro(s, i, i, dp);
            prePro(s, i, i + 1, dp);
        }
        helper(res, new ArrayList<>(), s, 0, dp);
        return res;
    }

    //进行预处理，利用中心扩展 将所有回文子串的位置存储到 dp 中，dp[i][j]表示子串 s[i..j] 是否为回文子串
    private void prePro(String s, int left , int right, boolean[][] dp){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            dp[left][right] = true;
            left--;
            right++;
        }
    }

    private void helper(List<List<String>> res, List<String> list, String s, int index, boolean[][] dp){
        if(index == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = index; i < s.length(); i++){
            //利用预处理结果就不用再去判断该字符串是否是回文串
            if(!dp[index][i]){
                continue;
            }
            list.add(s.substring(index, i + 1));
            helper(res, list, s, i + 1, dp);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) {
        String s = "aab";
        PalindromePartition p = new PalindromePartition();
        System.out.println(p.partition(s));
    }
}
