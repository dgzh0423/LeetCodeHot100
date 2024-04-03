package main.array;

/**
 * @author 15304
 */
public class FirstMissPositive {

    //没有空间复杂度的限制，可以将数组所有的数放入哈希表，随后从 1 开始依次枚举正整数，并判断其是否在哈希表中
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //时间复杂度O(n) 空间复杂度O(1)
        for (int i = 0; i < n; i++) {
            //对于 x = nums[i], x∈[1,n],  x 应该放在数组中索引为 x-1 的位置上
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i,nums[i] - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //如果nums正好保存了1到n, 则结果为 n+1
        return n + 1;
    }
    public static void swap(int[] nums, int index1, int index2) {
        // 互换位置
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        int[] nums ={3,4,-1,1};
        System.out.println(firstMissingPositive(nums));
    }
}
