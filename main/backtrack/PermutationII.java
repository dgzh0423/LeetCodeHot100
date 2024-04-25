package main.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 15304
 */
public class PermutationII {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    boolean[] used;

    /**
     * @param nums 元素可重不可复选
     * @return res
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置, 例如：2' 只有在 2 已经被使用的情况下才会被选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        PermutationII p = new PermutationII();
        System.out.println(p.permuteUnique(nums));
    }
}
