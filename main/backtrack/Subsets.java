package main.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 15304
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, res, new ArrayList<>());
        return res;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            System.out.println(" 递归之前 => " + tmp);
            dfs(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
            System.out.println(" 递归之后 => " + tmp);
        }
    }

    public static void main(String[] args) {
       int[] nums = {1, 2, 3};
       Subsets subsets = new Subsets();
       List<List<Integer>> lists = subsets.subsets(nums);
       System.out.println(" 输出 " + lists);
    }
}
