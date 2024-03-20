package main.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                // 每行的首尾都是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 第 i 行的第 j 个数等于第 i−1 行的第 j−1 个数和第 j 个数之和
                    row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        PascalsTriangle triangle = new PascalsTriangle();
        System.out.println(triangle.generate(4));
    }
}
