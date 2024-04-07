package main.dynamic;

import java.util.Stack;

/**
 * @author 15304
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // 用于栈顶出栈时判断 ')' 是否匹配
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            // 如果是 '('， 就将'('的下标入栈
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            // 如果是 ')'，就将栈顶出栈
            else if (s.charAt(i) == ')') {
                // 栈顶出栈分两种情况： == -1 和 != -1
                stack.pop();
                // == -1，说明栈里没有与之匹配的左括号，那么就将该右括号的下标入栈
                if (stack.empty()) {
                    stack.push(i);
                }
                // != -1，说明该右括号与栈顶的左括号匹配，那么就用该右括号的下标减去当前栈顶存的下标，得到当前合法序列的长度，然后更新一下最长长度
                else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    public int validDynamic(String s) {
        int max = 0;
        // dp[i] 代表以下标 i 结尾的合法序列的最长长度，以 ( 结尾的字符串一定是非法序列，所以 dp 是零
        // https://pic.leetcode-cn.com/ef288fb0ac3f22cdf1bfa0daae1d98bf479e85df48012778ba7bb434eaaa7e55-image.png
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // 以 ) 结尾的字符串，要看前面一个是 ( 还是 )
            if (s.charAt(i) == ')') {
                // 右括号前边是左括号
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // 右括号前边是右括号，即下标 i-1 也是 )
                // 与 i-1 匹配的左括号下标为 i - dp[i - 1]，有效括号长度为 dp[i - 1]
                // 此时要看下标为 i - dp[i - 1] - 1 是否为 (, 也就是与 i 匹配
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // https://pic.leetcode-cn.com/8a1aac6d646ba6e03269c8ed48592521d0e953bb848b0c0f4ba2cc77538522a8-image.png
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) >= 2 ? dp[(i - dp[i - 1] - 1) - 1] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses longest = new LongestValidParentheses();
        String s = ")())";
        System.out.println(longest.longestValidParentheses(s));
        System.out.println(longest.validDynamic(s));
    }
}
