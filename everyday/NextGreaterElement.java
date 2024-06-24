package everyday;

import java.util.HashMap;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 * @author 15304
 */
public class NextGreaterElement {

    /**
     * @param nums1 无重复元素，且是num2的子集
     * @param nums2 无重复元素，
     * @return nums1 中每个元素在nums2里的下一个更大元素
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 记录 nums2 中每个元素的下一个更大元素
        int[] greater = nextGreaterElement(nums2);
        // map保存：元素 x -> x 的下一个最大元素
        HashMap<Integer, Integer> greaterMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            greaterMap.put(nums2[i], greater[i]);
        }
        // nums1 是 nums2 的子集，所以根据 greaterMap 可以得到结果
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = greaterMap.get(nums1[i]);
        }
        return res;
    }

    // 单调栈模板解决 下一个更大(后面第一个更大的)元素 问题
    private int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                // 矮个不需要考虑，出栈
                s.pop();
            }
            // nums[i] 身后的更大元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        NextGreaterElement solution = new NextGreaterElement();
        int[] res = solution.nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

}
