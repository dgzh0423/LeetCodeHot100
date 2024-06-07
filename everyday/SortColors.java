package everyday;

import java.util.Arrays;

/**
 * 75. 颜色分类(荷兰国旗问题) 要求空间复杂度O(1), 且只遍历一次
 * @author 15304
 */
public class SortColors {

    /**
     * @param nums 待排序数组
     */
    public void sortColors(int[] nums) {
        // left左边都是0，right右边都是2
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        // 遍历数组
        while (i <= right) {
            // 当前元素为0，它应该在left的左边
            if (nums[i] == 0) {
                swap(nums, i, left);
                i++;
                left++;
            }
            // 当前元素为2，它应该在right右边
            else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
                // 注意这里不用i++，因为交换后，i位置的元素不确定，所以需要继续检查nums[i]
            }
            // 当前元素为1，位置不变，继续遍历下一个元素
            else {
                i++;
            }
        }
    }

    // 都是双指针但是不同思路：类似刷墙
    public void sortColors2(int[] nums) {
        // zero表示数字0的右侧边界，one表示数字1的右侧边界
        // 即[0, zero)都是0，[zero, one)都是1，[one, n)都是2
        int zero = 0, one = 0;
        for(int i = 0; i < nums.length; i++){
            // 1.先将当前元素保存到tmp中，然后将当前元素变为2
            int tmp = nums[i];
            nums[i] = 2;
            // 2. 根据当前元素原来的值来决定将当前元素放到哪个区间
            if(tmp <= 1){
                nums[one] = 1;
                one++;
            }
            if(tmp == 0){
                nums[zero] = 0;
                zero++;
            }
        }
   }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] color = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(color);
        System.out.println(Arrays.toString(color));
        int[] color2 = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors2(color2);
        System.out.println(Arrays.toString(color2));
    }
}
