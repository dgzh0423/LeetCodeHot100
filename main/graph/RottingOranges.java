package main.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994.腐烂的橘子
 * @author 15304
 */
public class RottingOranges {

    /**
     * BFS求腐烂橘子到所有新鲜橘子的最短路径
     * @param grid m * n网格，0表示空白，1表示新鲜橘子，2表示腐烂橘子
     * @return 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    // 记录新鲜橘子的数量
                    fresh++;
                } else if (grid[r][c] == 2) {
                    // 统计腐烂橘子坐标，如[[0, 1], [1, 0]],加到尾部
                    queue.add(new int[]{r, c});
                }
            }
        }

        // round 表示腐烂的轮数
        int round = 0;
        while (fresh > 0 && !queue.isEmpty()) {
            round++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // rot表示腐烂的橘子
                int[] rot = queue.poll();
                int r = rot[0], c = rot[1];

                //腐蚀上，下，左，右四个方向上的新鲜橘子
                if (r-1 >= 0 && grid[r-1][c] == 1) {
                    grid[r-1][c] = 2;
                    fresh--;
                    queue.add(new int[]{r-1, c});
                }
                if (r+1 < m && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    fresh--;
                    queue.add(new int[]{r+1, c});
                }
                if (c-1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    fresh--;
                    queue.add(new int[]{r, c-1});
                }
                if (c+1 < n && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    fresh--;
                    queue.add(new int[]{r, c+1});
                }
            }
        }
        // 返回结果
        if (fresh > 0) {
            return -1;
        } else {
            return round;
        }
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(new RottingOranges().orangesRotting(grid));
    }
}
