package main.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 216.组合总和 III
 * @author 15304
 */
public class CombinationSumIII {

    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯的路径
    LinkedList<Integer> track = new LinkedList<>();
    // 记录 track 中的路径和
    int trackSum = 0;

    /**
     * 只使用数字1到9，且每个数字最多使用一次
     * @param k k个数
     * @param n k个数的和为n
     * @return 所有可能的组合
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (k <= 0 || n <= 0 || k > n) {
            return res;
        }
        // n > k个数的最大和，无解
        if (n > (19 - k) * k / 2) {
            return res;
        }
        backtrack(k, n, 1);
        return res;
    }

    void backtrack(int k, int n, int start) {
        // base case，达到目标和，且路径元素个数等于k
        if (trackSum == n && track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > n) {
            return;
        }
        for (int i = start; i < 10; i++) {
            track.add(i);
            trackSum += i;
            backtrack(k, n, i + 1);
            track.removeLast();
            trackSum -= i;
        }
    }

    public static void main(String[] args) {
        CombinationSumIII combinationSumIII = new CombinationSumIII();
        System.out.println(combinationSumIII.combinationSum3(3, 7));
    }
}
