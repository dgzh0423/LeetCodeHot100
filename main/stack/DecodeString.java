package main.stack;

import java.util.LinkedList;

/**
 * @author 15304
 */
public class DecodeString {
    // 难点是需要从内向外生成与拼接字符串
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> numStack = new LinkedList<>();
        LinkedList<String> resStack = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                numStack.addLast(multi);
                resStack.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int curMulti = numStack.removeLast();
                for(int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(resStack.removeLast() + tmp);
            }
            //遇到数字，保存到 multi
            else if(c >= '0' && c <= '9') {
                multi = multi * 10 + c - '0';
            } else {
                // 遇到字母
                res.append(c);
            }
        }
        return res.toString();
    }
    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        String s = "3[ab2[c]]";
        System.out.println(decodeString.decodeString(s));
    }
}
