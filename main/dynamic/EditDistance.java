package main.dynamic;

/**
 * @author 15304
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        // dp[i][j] 代表 word1 到位置 i 转换成 word2 到位置 j 需要的最少步数
        int[][] dp = new int[n1 + 1][n2 + 1];
        // 第一行 空word1 转换到 word2 的位置 j 的最小步数，即word每次插入一个
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        // 第一列 word1 从位置 i 转换到 空word2 的最小步数，即word每次删除一个
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // dp[i - 1][j - 1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j-1]) + 1;
                }
            }
        }
        return dp[n1][n2];
    }
    public static void main(String[] args) {
        EditDistance min = new EditDistance();
        String word1 = "";
        String word2 = "";
        System.out.println(min.minDistance(word1, word2));
    }
}
