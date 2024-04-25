package main.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 15304
 */
public class SubsetsII {

    List<List<Integer>> res = new LinkedList<>();

    LinkedList<Integer> track = new LinkedList<>();

    /**
     * @param nums 元素可重但不可复选
     * @return res
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        SubsetsII s = new SubsetsII();
        System.out.println(s.subsetsWithDup(nums));
    }
}
