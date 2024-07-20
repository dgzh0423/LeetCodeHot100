package everyday;

import java.util.ArrayList;
import java.util.List;

/**
 * 2850. 将石头分散到网格图的最少移动次数
 * @author 15304
 */
public class MinMovesToSpreadStones {

    /**
     * 时间复杂度 O(mn⋅(mn)!)，空间复杂度 O(mn)，其中 m=n=3
     * @param grid 3 * 3 网格
     * @return 使得每个格子都有一个石头的最小移动次数
     */
    public int minimumMoves(int[][] grid) {
        // from 记录石头个数 > 1 的格子需要移出几个石头，移出几个石头就记录几次该格子的坐标
        // to 记录石头个数 = 0 的位置
        List<int[]> from = new ArrayList<>();
        List<int[]> to = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 1) {
                    for (int k = 1; k < grid[i][j]; k++) {
                        from.add(new int[]{i, j});
                    }
                } else if (grid[i][j] == 0) {
                    to.add(new int[]{i, j});
                }
            }
        }

        // 计算 from 的全排列，与 to 匹配，即累加从 from[i] 到 to[i] 的曼哈顿距离(二维平面中任意两点的曼哈顿距离为 |x1 - x2| + |y1 - y2|)
        // 所有距离之和的最小值就是答案
        int ans = Integer.MAX_VALUE;
        for (List<int[]> from2 : permutations(from)) {
            int total = 0;
            for (int i = 0; i < from2.size(); i++) {
                int[] f = from2.get(i);
                int[] t = to.get(i);
                total += Math.abs(f[0] - t[0]) + Math.abs(f[1] - t[1]);
            }
            ans = Math.min(ans, total);
        }
        return ans;
    }

    private List<List<int[]>> permutations(List<int[]> arr) {
        List<List<int[]>> result = new ArrayList<>();
        permute(arr, 0, result);
        return result;
    }

    private void permute(List<int[]> arr, int start, List<List<int[]>> result) {
        if (start == arr.size()) {
            result.add(new ArrayList<>(arr));
        }
        for (int i = start; i < arr.size(); i++) {
            swap(arr, start, i);
            permute(arr, start + 1, result);
            swap(arr, start, i);
        }
    }

    private void swap(List<int[]> arr, int i, int j) {
        int[] temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 0},
                {1, 0, 0},
                {1, 0, 3}
        };
        System.out.println(new MinMovesToSpreadStones().minimumMoves(grid));
    }
}
