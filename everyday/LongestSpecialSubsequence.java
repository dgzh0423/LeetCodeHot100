package everyday;

/**
 * 522. 最长特殊序列 II
 * @author 15304
 */
public class LongestSpecialSubsequence {

    // 时间复杂度O(n^2*m)，m为字符串的平均长度，空间复杂度O(1)
    public int findLUSlength(String[] strs) {
        int n = strs.length;
        // 保存当前最长的特殊序列的长度
        int ans = -1;
        // 需要枚举每个字符串的所有子序列吗？不需要，因为子序列越长，越不可能是其它字符串的子序列
        // 所以只需要枚举字符串 s=strs[i]，判断 s 是否为其它字符串的子序列，如果不是（check = true），则用 s 的长度更新答案的最大值
        for (int i = 0; i < n; i++) {
            // 剪枝：如果当前strs[i]不会使ans变大，可以直接跳过
            if (strs[i].length() <= ans) {
                continue;
            }
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (i != j && isSubsequence(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans = Math.max(ans, strs[i].length());
            }
        }
        return ans;
    }

    // 贪心 + 双指针判断字符串s是否是字符串t的子序列
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                ++i;
            }
            ++j;
        }
        return i == m;
    }

    public static void main(String[] args) {
        String[] strs = {"aba", "cdc", "eae"};
        LongestSpecialSubsequence longestSpecialSubsequence = new LongestSpecialSubsequence();
        System.out.println(longestSpecialSubsequence.findLUSlength(strs));
    }
}
