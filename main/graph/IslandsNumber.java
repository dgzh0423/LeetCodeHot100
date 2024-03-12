package main.graph;

/**
 * <a href="https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/?envType=study-plan-v2&envId=top-100-liked">...</a>
 * @author 15304
 */
public class IslandsNumber {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        // 判断 base case 特别注意当遍历到海洋的时候也退出循环
        if (!inArea(grid, r, c) || grid[r][c] == '0') {
            return;
        }
        // 如果这个格子不是岛屿，直接返回
        // if (grid[r][c] != 1) {
        // return;
        // }

        // 将遍历过的陆地改为海洋，防止重复遍历
        grid[r][c] = '0';

        // 访问上、下、左、右四个相邻结点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(char[][] grid, int r, int c) {
        // 判断坐标 (r, c) 是否在网格中
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }

    public static void main(String[] args) {

    }
}
