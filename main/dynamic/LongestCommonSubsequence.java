package main.dynamic;

/**
 * @author 15304
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // dp[i][j] 表示 text1前 i 个字符 和 text2前 j 个字符 的最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];
        // 当 i=0 时，text1[0, i]为空，dp[0][j]=0。同理，当 j=0 时，text2[0, j]为空，dp[i][0]=0
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
    public static void main(String[] args) {
        LongestCommonSubsequence longest = new LongestCommonSubsequence();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longest.longestCommonSubsequence(text1, text2));
    }
}
