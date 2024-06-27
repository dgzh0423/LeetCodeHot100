package everyday;

/**
 * 2734.执行子串操作后的字典序最小字符串
 * @author 15304
 */
public class LexicographicallySmallestString {

    // 贪心算法 时间复杂度O(n) 空间复杂度O(1)
    public String smallestString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 遍历字符串，从第一个不为‘a’的字符开始，每个字符都减一，直到遇到’a’为止
        for (int i = 0; i < n; i++) {
            if (chars[i] > 'a'){
                for (; i < n && chars[i] > 'a'; i++) {
                    chars[i]--;
                }
                return new String(chars);
            }
        }
        // 特例：当字符串全为'a'时，将最后一个字符变为'z'即可
        chars[n - 1] = 'z';
        return new String(chars);
    }
    public static void main(String[] args) {
        LexicographicallySmallestString solution = new LexicographicallySmallestString();
        System.out.println(solution.smallestString("leetcode"));
    }
}
