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

    public String longestPalindromeByCenterSpread(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        char[] ch = s.toCharArray();
        int max = 0;
        int begin = 0;
        for (int i = 0; i < len - 1; i++) {
            int odd = maxLen(ch, i, i);
            int even = maxLen(ch, i, i + 1);
            if (Math.max(odd, even) > max) {
                max = Math.max(odd, even);
                begin = i - (max - 1) / 2;
            }
        }
        return s.substring(begin, begin + max);
    }

    // 利用中心扩散法找最长回文长度
    private int maxLen (char[] ch, int left, int right) {
        while (left >= 0 && right < ch.length && ch[left] == ch[right]) {
            left--;
            right++;
        }
        return (right - 1) - (left + 1) + 1;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longest = new LongestPalindromicSubstring();
        String s = "baba";
        System.out.println(longest.longestPalindrome(s));
        System.out.println(longest.longestPalindromeByCenterSpread(s));
    }
}
