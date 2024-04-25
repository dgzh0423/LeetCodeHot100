package main.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class CombinationSum {

    /**
     * @param candidates 元素无重可复选。 子集里的元素不区分顺序，比如 {4,5} 和 {5,4} 是同一个子集。
     * @param target 目标和
     * @return res
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 对 candidates 进行排序 为了方便后续子集和超过 target时 ，能直接结束循环
        Arrays.sort(candidates);

        int start = 0;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(path, target, candidates, start, res);
        return res;
    }

    private void backtrack(List<Integer> path, int target, int[] candidates, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，保存path
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 从 start 开始遍历，避免生成重复子集
        for (int i = start; i < candidates.length; i++) {
            // 当子集和超过 target ，则直接结束循环
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            System.out.println(" 递归之前 => " + path);
            // 进行下一轮选择
            backtrack(path, target - candidates[i], candidates, i, res);
            // 回退：撤销选择，恢复到之前的状态
            path.remove(path.size() - 1);
            System.out.println(" 递归之后 => " + path);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
