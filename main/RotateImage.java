package main;

import java.util.Arrays;

/**
 * @author 15304
 */
public class RotateImage {
    /*
    时间复杂度：O(n^2)  空间复杂度：O(1)
    */
    public static void rotate(int[][] matrix) {
        // 设矩阵行列数为 n
        int n = matrix.length;
        // 起始点范围为 0 <= i < n / 2 , 0 <= j < (n + 1) / 2 因为当n为奇数时，沿着对角线对称的元素移动过程是一样的，故可以省去一半
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 暂存 A 至 tmp
                int tmp = matrix[i][j];
                // 元素旋转操作
                matrix[i][j] = matrix[n - 1 - j][i]; //A <- D
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];//D <- C
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];//C <- B
                matrix[j][n - 1 - i] = tmp;//B <- tmp(A)
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}
