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
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // dp[i][j] = (s[i] == s[j]) && dp[i + 1][j - 1]
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // (j−1) − (i+1) + 1 < 2 , 说明子串 s[i + 1, j - 1] 的长度严格小于 2,此时 dp[i + 1][j - 1] 只看 (s[i] == s[j])
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

    public static void main(String[] args) {
        LongestPalindromicSubstring longest = new LongestPalindromicSubstring();
        String s = "baba";
        System.out.println(longest.longestPalindrome(s));
    }
}
