package main.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class SpiralMatrix {
    /*
    时间复杂度：O(mn)  空间复杂度：O(1)
    */
    public static List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();

        //定义矩阵 左、右、上、下 四个边界 l , r , t , b ，用于打印的结果列表 res
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, index = 0;
        Integer[] res = new Integer[(r + 1) * (b + 1)];

        //“从左向右、从上向下、从右向左、从下向上” 四个方向循环打印
        //不建议频繁使用System.out.println() 因为输出操作需要占用CPU和内存资源，因为它会将数据写入缓存区中，然后再输出到控制台，且使用了同步锁synchronized
        while (true) {
            for (int i = l; i <= r; i++) res[index++] = matrix[t][i]; // left to right
            if (++t > b) break;
            for (int i = t; i <= b; i++) res[index++] = matrix[i][r]; // top to bottom
            if (l > --r) break;
            for (int i = r; i >= l; i--) res[index++] = matrix[b][i]; // right to left
            if (t > --b) break;
            for (int i = b; i >= t; i--) res[index++] = matrix[i][l]; // bottom to top
            if (++l > r) break;
        }
        return Arrays.asList(res);
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(matrix));
    }
}
