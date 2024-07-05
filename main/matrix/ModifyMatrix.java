package main.matrix;

/**
 * 3033.修改矩阵
 * @author 15304
 */
public class ModifyMatrix {

    public int[][] modifiedMatrix(int[][] matrix) {
        int n = matrix[0].length;
        // 按列遍历，遍历两次
        for (int col = 0; col < n; col++) {
            int max = 0;
            // 第一次遍历找到当前列的最大值 max
            for(int[] row : matrix){
                max = Math.max(max, row[col]);
            }
            // 第二次遍历，将当前列的 -1 替换为 max
            for (int[] row : matrix) {
                if (row[col] == -1){
                    row[col] = max;
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, -1},
                {4, -1, 6, -1},
                {7, 8, 9, 1}
        };
        matrix = new ModifyMatrix().modifiedMatrix(matrix);
        for (int[] row : matrix) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
