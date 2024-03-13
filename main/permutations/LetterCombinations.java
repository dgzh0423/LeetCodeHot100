package main.permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class LetterCombinations {
    //电话按键的数字与字母的映射关系，2-9才有字母表示
    static final String[] LETTER_MAP = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>();
        // 用来保存每次拼接的结果
        StringBuilder sb = new StringBuilder();
        dfs(digits, ans, 0, sb);

        return ans;
    }

    /**
     * 
     * @param digits
     * @param ans
     * @param index digits的索引位置
     * @param sb
     */
    public void dfs(String digits, List<String> ans, int index, StringBuilder sb){
        // 判断是否到递归尽头：即是否索引位置到了最后
        // 若到底，则把拼好的字符串 加入到结果集合中，并返回到上一层递归
        if(index == digits.length()){
            ans.add(sb.toString());
            return;
        }
        
        // 取出数字对应的字母字符串 
        String letters = LETTER_MAP[(digits.charAt(index)-'0')];
        // 得到字母的个数
        int lettersNum = letters.length();

        // 循环拼接字符串
        for(int i = 0; i < lettersNum; i++){
            sb.append(letters.charAt(i));
            dfs(digits, ans, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "234";
        LetterCombinations letterCombinations = new LetterCombinations();
        List<String> letterList = letterCombinations.letterCombinations(digits);
        System.out.println(letterList);
    }
}
