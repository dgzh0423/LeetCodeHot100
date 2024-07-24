package main.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 下一个更大元素 I, II
 * @author 15304
 */
public class NextGreaterElement {

    /**
     * @param nums1 无重，是nums2的子集
     * @param nums2 无重
     * @return nums1中每个元素的下一个更大元素
     */
    public int[] nextGreaterElementI(int[] nums1, int[] nums2) {
        // 1.获取nums2中每个元素的下一个更大元素 单调栈实现
        int[] greaterElement = nextGreaterElement(nums2);
        // 2.map key为 nums2[i], value为 nums2[i]的下一个更大元素
        Map<Integer, Integer> map = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], greaterElement[i]);
        }
        // 3.遍历nums1, 获取nums1中每个元素的下一个更大元素
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    private int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    /**
     * @param nums 循环数组。相当于把两个nums拼在一起
     * @return nums中每个元素的下一个更大元素
     */
    public int[] nextGreaterElementII(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        // 数组长度加倍模拟环形数组
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        NextGreaterElement nextGreaterElement = new NextGreaterElement();
        System.out.println(Arrays.toString(nextGreaterElement.nextGreaterElementI(nums1, nums2)));
        System.out.println(Arrays.toString(nextGreaterElement.nextGreaterElementII(nums2)));
    }
}
