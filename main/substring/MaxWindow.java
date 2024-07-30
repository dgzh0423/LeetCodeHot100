package main.substring;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 * @author 15304
 */
public class MaxWindow {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 2 || k < 2) {
            return nums;
        }

        // 双向队列 保存的是元素下标 i
        LinkedList<Integer> queue = new LinkedList<>();
        // 共有 n - k + 1 个滑动窗口
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++){
            // 保证下标i对应的元素按从大到小排序 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.offerLast(i);
            // [L,R]实际上就是[i+1-k,i]，判断队首元素的下标是否在窗口内
            if (queue.peek() < i + 1 - k){
                queue.poll();
            }
            // i = k - 1 时是第一个窗口，i++相当于窗口向右滑动一格
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
