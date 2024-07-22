package main.graph;

/**
 * 463. 岛屿的周长
 * @author 15304
 */
public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += dfs(grid, i, j);
                }
            }
        }
        return perimeter;
    }

    // 检查陆地 grid[i][j]上下左右四个方向上有无相邻的海洋，有一个相邻海洋，该陆地的有效边长就+1
    private int dfs(int[][] grid, int i, int j){
        int perimeter = 0;
        if (i - 1 < 0 || grid[i-1][j] != 1) {
            perimeter++;
        }
        if (i + 1 >= grid.length || grid[i+1][j] != 1) {
            perimeter++;
        }
        if (j - 1 < 0 || grid[i][j-1] != 1) {
            perimeter++;
        }
        if (j + 1 >= grid[0].length || grid[i][j+1] != 1) {
            perimeter++;
        }
        return perimeter;
    }

    public int islandPerimeter2(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1) {
                    // 上
                    if (i == 0 || grid[i - 1][j] != 1){
                        perimeter++;
                    }
                    // 下
                    if (i == grid.length - 1|| grid[i + 1][j] != 1){
                        perimeter++;
                    }
                    // 左
                    if (j == 0 || grid[i][j - 1] != 1){
                        perimeter++;
                    }
                    // 右
                    if (j == grid[0].length - 1 || grid[i][j + 1] != 1){
                        perimeter++;
                    }
                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(new IslandPerimeter().islandPerimeter(grid));
        System.out.println(new IslandPerimeter().islandPerimeter2(grid));
    }
}
