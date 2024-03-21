package main.dynamic;

/**
 * @author 15304
 */
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < m; i++){
            // 左边界
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            // 上边界
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <n ; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public int improveMinPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0){
                    continue;
                }
                else if (i == 0){
                    grid[i][j] += grid[i][j-1];
                }
                else if (j == 0){
                    grid[i][j] += grid[i-1][j];
                }
                else {
                    grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
                }
            }
        }
        return grid[m-1][n-1];
    }
    public static void main(String[] args) {
        MinPathSum min = new MinPathSum();
        int[][]grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(min.minPathSum(grid));
        System.out.println(min.improveMinPathSum(grid));
    }
}
