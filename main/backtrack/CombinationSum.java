package main.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class CombinationSum {

    /**
     * candidates里的元素都不重复，但可以重复使用。 子集里的元素不区分顺序，比如 {4,5} 和 {5,4} 是同一个子集。
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 符合条件的元素集合
        List<Integer> path = new ArrayList<>();
        // 对 candidates 进行排序⭐⭐
        // 1. 为了方便后续子集和超过 target时 ，能直接结束循环
        // 2. 当遍历到 candidates[i]，在下一步选择时，可以直接排除candidates[i]之前的元素
        Arrays.sort(candidates);
        int start = 0;
        List<List<Integer>> res = new ArrayList<>();
        backtrack(path, target, candidates, start, res);
        return res;
    }

    private void backtrack(List<Integer> path, int target, int[] candidates, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，保存path
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 当遍历到较大的元素时，在下一步选择时，比该元素小的都可以排除了
        for (int i = start; i < candidates.length; i++) {
            // 当子集和超过 target ，则直接结束循环
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            // 进行下一轮选择
            backtrack(path, target - candidates[i], candidates, i, res);
            // 回退：撤销选择，恢复到之前的状态
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {3,4,5};
        int target = 9;
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(candidates, target));
    }
}
