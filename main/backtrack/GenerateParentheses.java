package main.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        StringBuilder path = new StringBuilder();
        dfs(path, n, n, res);
        return res;

    }

    /**
     * <a href="https://pic.leetcode-cn.com/7ec04f84e936e95782aba26c4663c5fe7aaf94a2a80986a97d81574467b0c513-LeetCode%20%E7%AC%AC%2022%20%E9%A2%98%EF%BC%9A%E2%80%9C%E6%8B%AC%E5%8F%B7%E7%94%9F%E5%87%BA%E2%80%9D%E9%A2%98%E8%A7%A3%E9%85%8D%E5%9B%BE.png">...</a>
     * @param path  从根结点到任意结点的路径，全程只使用一份
     * @param left  左括号还有几个可以使用
     * @param right 右括号还有几个可以使用
     * @param res   有效的括号组合
     */
    void dfs(StringBuilder path, int left, int right, List<String> res){
        //当剩余的左，右括号都为0时，此时找到了一条合理的路径，保存到res中返回
        if (left == 0 && right == 0){
            //注意，这里path.toString()返回的是新的String对象
            res.add(path.toString());
            return;
        }
        //剪枝：当剩余的左括号比剩余的右括号多时，
        if (left > right){
            return;
        }
        // 当剩余的左括号>0时，可以生出左子节点
        if (left > 0){
            path.append("(");
            dfs(path,left - 1, right, res);
            path.deleteCharAt(path.length() - 1);
        }
        // 当剩余的右括号严格 > 剩余的左括号时，可以生出右子节点
        if (right > 0){
            path.append(")");
            dfs(path, left, right - 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses gs = new GenerateParentheses();
        System.out.println(gs.generateParenthesis(3));
    }
}
