package main.substring;

/**
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

    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
