package main;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 对于长度为 n 的数组 nums 而言，窗口的数量为 n−k+1
 * 对于两个相邻（只差了一个位置）的滑动窗口，它们共用着 k−1 个元素，而只有 1 个元素是变化的
 */
public class MaxWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 2 || k < 2) return nums;

        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList<>();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++){
            // 后面小于改成小于等于也可以
            // 有等于的时候，提前让栈内相同的数出栈，就不会在判断queue.peek() < L 时出栈了
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.offerLast(i);
            // [L,R]实际上就是[i+1-k,i]，判断队首元素的位置是否小于L
            if (queue.peek() < i + 1 - k){
                queue.poll();
            }
            //当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k){
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k)));
    }
}
