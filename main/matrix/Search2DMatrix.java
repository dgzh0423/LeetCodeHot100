package main.matrix;

/**
 * @author 15304
 */
public class Search2DMatrix {
    /*
   时间复杂度：O(m+n)  空间复杂度：O(1)
   */
    public static boolean searchMatrix(int[][] matrix, int target) {
        //利用矩阵 “从上到下递增、从左到右递增” 的特点将矩阵逆时针旋转 45°, 发现其类似于二叉搜索树
        //以 matrix 中的 左下角元素 为标志数, 该元素为其所在列的最大值，所在行的最小值
        int i = matrix.length - 1, j = 0;

        while(i >= 0 && j < matrix[0].length) {
            if(matrix[i][j] > target) i--; //当 matrix[i][j] > target 时，执行 i-- ，即消去第 i 行元素。
            else if(matrix[i][j] < target) j++; //当 matrix[i][j] < target 时，执行 j++ ，即消去第 j 列元素。
            else return true; //当 matrix[i][j] = target 时，找到目标值。
        }

        return false;
    }
    public static void main(String[] args) {
        int[][] matrix ={{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }
}
