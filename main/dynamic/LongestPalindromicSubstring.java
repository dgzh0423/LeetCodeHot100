package main.dynamic;

/**
 * @author 15304
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            //子串只有一个字符，一定是回文
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // dp[i][j] = (s[i] == s[j]) && dp[i + 1][j - 1]
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                }
                // s[i] == s[j]为true的情况下, 看 dp[i + 1][j - 1]，根据子串 s[i + 1, j - 1] 的长度分情况
                else if (charArray[i] == charArray[j]){
                    // (j−1) − (i+1) + 1 < 2 , 说明子串 s[i + 1, j - 1] 的长度严格小于 2, 此时 dp[i + 1][j - 1] 为 true
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // substring左闭右开
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 双指针中心扩散法找字符串的最长回文子串
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindromeByCenterSpread(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String s1 = palindrome(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心的最长回文子串
            String s2 = palindrome(s, i, i + 1);
            // res = longest(res, s1, s2)
            // 这里的 > 是考虑有多个最长回文子串是，会选择更靠后的
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    /**
     * @param s 字符串
     * @param l 中心下标 l = r，找的是奇数长度的最长回文子串
     * @param r 中心下标 r = l + 1，找的是偶数长度的最长回文子串
     * @return 以 s[l] 和 s[r] 为中心的最长回文串
     */
    String palindrome(String s, int l, int r) {
        // 防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // 双指针，向两边展开
            l--;
            r++;
        }
        // 返回结果，substring左闭右开
        return s.substring(l + 1, r);
    }

    /**
     * 双指针法判断是否是回文串
     * @param s 字符串
     * @return true/false
     */
    boolean isPalindrome(String s) {
        // 一左一右两个指针相向而行
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longest = new LongestPalindromicSubstring();
        // bab 或 aba 都符合题意
        String s = "baba";
        System.out.println(longest.longestPalindrome(s));
        System.out.println(longest.longestPalindromeByCenterSpread(s));
        System.out.println(longest.isPalindrome(s));
    }
}
