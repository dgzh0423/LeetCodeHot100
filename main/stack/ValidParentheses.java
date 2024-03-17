package main.stack;

import java.util.Stack;

/**
 * @author 15304
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if(s.isEmpty()) {
            return true;
        }
        Stack<Character> stack= new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='(') {
                stack.push(')');
            } else if(c=='{') {
                stack.push('}');
            } else if(c=='[') {
                stack.push(']');
            }
            // 这里的 stack.empty() 可以判断多出来的右括号，c != stack.pop() 用来判断右括号位置是否正确，因为内括号先出栈，外括号最后再出栈
            else if(stack.empty() || c != stack.pop()) {
                return false;
            }
        }
        //这里的 stack.empty() 可以判断多出来的左括号
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        String s = "()}";
        System.out.println(v.isValid(s));
    }
}
