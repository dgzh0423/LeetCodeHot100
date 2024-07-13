package main.substring;

/**
 * 76.最小覆盖子串
 * @author 15304
 */
public class MinWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        //因为字符串中可以有大小写字母，所以是128而不是26
        int[] tCnt = new int[128];
        //记录t中出现的字母和出现次数
        for (int i = 0; i < t.length(); i++) {
            tCnt[t.charAt(i)]++;
        }
        // l,r窗口左右边界，min保存符合条件的子串的长度,start记录符合条件的子串的首字母的index,need记录还需要多少字母才满足条件
        int l = 0, r = 0, min = Integer.MAX_VALUE, start = 0, need = t.length();
        //开始遍历
        while (r < s.length()){
            char c = s.charAt(r);
            if (tCnt[c] > 0) {
                //如果当前字母在t中，need--
                need--;
            }
            //把当前字母加入窗口中
            tCnt[c]--;
            //当窗口已经包含t中所有字母时，开始尝试缩小左边界
            if (need == 0){
                while ( l < r && tCnt[s.charAt(l)] < 0){
                    // 窗口左指针的字母不在t中时，将其放回tCnt，并且左指针右移
                    tCnt[s.charAt(l)]++;
                    l++;
                }
                // 更新min，start
                if (r-l+1 < min){
                    min = r-l+1;
                    start = l;
                }
                //左指针再右移就不满足条件，重新寻找
                tCnt[s.charAt(l)]++;
                l++;
                need++;
            }
            r++;
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }

    /**
     * 判断 s2 是否包含 s1 的排列 等价于 s2 中是否存在一个子串，包含 s1 中所有字符且不包含其他字符？
     * @param s1 1 <= s1.length, s2.length <= 10^4
     * @param s2 1 <= s1.length, s2.length <= 10^4
     * @return true/false
     */
    public static boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length(), length2 = s2.length();
        if (length2 < length1){
            return false;
        }
        // s1 和 s2 仅包含小写字母
        int[] s1count = new int[26];
        // 记录s1中出现的字母和出现次数
        for (int i = 0; i < s1.length(); i++) {
            s1count[s1.charAt(i) - 'a']++;
        }
        // l,r窗口左右边界
        int l = 0, r = 0;
        while (l <= length2 - length1){
            // 固定 l，让 r 向右探测
            while (r < l + length1 && s1count[s2.charAt(r) - 'a'] > 0){
                s1count[s2.charAt(r) - 'a']--;
                r++;
            }
            // 当s2[l,r] 包含s1时，返回true
            if (r == l + length1){
                return true;
            }
            // 窗口不符合条件(有其他字符，或者字符个数不对)，左边界右移，右边界不动
            // 这里加到s1count里的字符并不符合条件，由于右边界不同，会由上面的while循环处理，将这些不符合条件的字符从s1count中再减去
            s1count[s2.charAt(l) - 'a']++;
            l++;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2));
    }
}
