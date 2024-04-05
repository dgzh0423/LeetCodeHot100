package main.binarysearch;

/**
 * @author 15304
 */
public class BinarySearchMatrix {
    /* 将二维矩阵看成一维有序数组，再用二分查找 */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // mid / n 代表在哪一行，mid % n 代表在哪一列，注意行，列下标都从0开始
            if (matrix[mid / n][ mid % n ] < target) {
                left = mid + 1;
            }
            else if (matrix[mid / n][ mid % n ] > target) {
                right = mid - 1;
            }
            // 找到目标元素
            else if (matrix[mid / n][ mid % n ] == target){
                return true;
            }
        }
        // 未找到目标元素
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
        int target = 13;
        BinarySearchMatrix bsm = new BinarySearchMatrix();
        System.out.println(bsm.searchMatrix(matrix, target));
    }
}
