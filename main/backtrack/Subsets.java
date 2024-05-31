package main.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 15304
 */
public class Subsets {

    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();

    /**
     * @param nums 元素无重不可复选
     * @return 所有子集，注意子集内不考虑顺序的区别，[2,1]和[1,2]只算一个子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    // 回溯算法核心函数，遍历子集问题的回溯树
    private void backtrack(int[] nums, int start) {

        // 前序位置，每个节点的值都是一个子集
        res.add(new LinkedList<>(track));

        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
       int[] nums = {1, 2, 3};
       Subsets subsets = new Subsets();
       List<List<Integer>> lists = subsets.subsets(nums);
       System.out.println(" 输出 " + lists);
    }
}
