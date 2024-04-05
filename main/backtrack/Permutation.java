package main.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class Permutation {

    /**
     * nums里元素不重复，每个全排列包含nums的所有元素
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] isVisited = new boolean[nums.length];
        dfs(res, nums, new ArrayList<>(), isVisited);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, boolean[] isVisited) {
        // 当所有数字都在排列中了，终止递归
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        //在还未选择的数中依次选择一个加入排列tmp
        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i]) {
                continue;
            }

            tmp.add(nums[i]);
            isVisited[i] = true;
            System.out.println(" 递归之前 => " + tmp);

            dfs(res, nums, tmp, isVisited);

            //回溯
            isVisited[i] = false;
            tmp.remove(tmp.size() - 1);
            System.out.println(" 递归之后 => " + tmp);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permutation permutation = new Permutation();
        System.out.println(" 输出 " + permutation.permute(nums));
    }
}
