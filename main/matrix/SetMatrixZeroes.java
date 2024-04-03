package main.matrix;

import java.util.Arrays;

/**
 * @author 15304
 */
public class SetMatrixZeroes {
    /*
    时间复杂度：O(mn)  空间复杂度：O(m+n)
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //用两个标记数组分别记录每一行和每一列是否有零出现。
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        //首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置置为 true
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        //再次遍历该数组，用标记数组更新原数组即可
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
  时间复杂度：O(mn)  空间复杂度：O(1)
   */
    public static void setZeroesWithTwoFlag(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0Flag = false;
        boolean col0Flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0Flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                col0Flag = true;
                break;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    // 把第一行第一列作为标志位
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //最后将第一行，第一列置0
        if (row0Flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0Flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));

        int[][] matrix2 = {{1,1,1},{1,0,1},{1,1,1}};
        setZeroesWithTwoFlag(matrix2);
        System.out.println(Arrays.deepToString(matrix2));

    }
}
