package everyday;

/**
 * 反转字符串中的单词 I, III
 * @author 15304
 */
public class ReverseWordsInTheString {

    // 时间复杂度O(n) 空间复杂度O(n)
    public String reverseWords(String s) {
        // 删除首尾空格
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        // 倒序遍历每个单词
        int j = s.length() - 1, i = j;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            // 注意append最后一个单词时，会多加一个空格，返回结果时需要再trim一下
            sb.append(s, i + 1, j + 1).append(" ");
            // 如果单词之间有多个空格，则跳过这些空格
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            // j 指向每个单词的最后一个字符
            j = i;
        }
        return sb.toString().trim();
    }

    // 时间复杂度O(n) 空间复杂度O(1)
    public String reverseWordsIII(String s) {
        char[] chars = s.toCharArray();
        // start记录每个单词的起始位置
        int start = 0;
        for (int i = 0; i < chars.length; i++){
            // i 指向每个单词之间的空格
            if (chars[i] == ' '){
                reverse(chars, start, i - 1);
                start = i + 1;
                continue;
            }
            // 注意最后一个单词的情况
            if (i == chars.length - 1){
                reverse(chars, start, i);
            }
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println("原字符串为：" + s);
        System.out.println("反转单词顺序后：" + new ReverseWordsInTheString().reverseWords(s));
        System.out.println("反转每个单词后：" + new ReverseWordsInTheString().reverseWordsIII(s));
    }
}
