package main.graph;

/**
 * 1020. 飞地的数量
 * @author 15304
 */
public class EnclavesNumber {

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 从左，右边界上的陆地出发，将其淹没，dfs继续淹没相邻的陆地，直至无法继续淹没
        for (int i = 0; i < m; i++){
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        // 从上，下边界上的陆地出发，将其淹没，dfs继续淹没相邻的陆地，直至无法继续淹没
        for (int j = 0; j < n; j++){
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        // 记录剩下的陆地数量，就是无法跳到边界的陆地数量
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) {
            return;
        }
        // 将当前陆地淹没
        grid[i][j] = 0;
        // dfs继续将四个方向的陆地淹没
        if (i > 0) {
            dfs(grid, i - 1, j);
        }
        if (i < grid.length - 1) {
            dfs(grid, i + 1, j);
        }
        if (j > 0) {
            dfs(grid, i, j - 1);
        }
        if (j < grid[0].length - 1) {
            dfs(grid, i, j + 1);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };
        System.out.println(new EnclavesNumber().numEnclaves(grid));
    }
}
