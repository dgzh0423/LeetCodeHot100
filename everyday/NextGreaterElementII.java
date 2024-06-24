package everyday;

import java.util.Stack;

/**
 * 503.下一个更大元素 II
 * @author 15304
 */
public class NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 数组长度加倍(不需要实际构造长度翻倍的新数组)模拟环形数组
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 索引 i 要 求模(取余)，其他的和模板一样
            while (!s.isEmpty() && s.peek() <= nums[i % n]) {
                s.pop();
            }
            res[i % n] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i % n]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 4, 3};
        NextGreaterElementII nextGreaterElementII = new NextGreaterElementII();
        int[] res = nextGreaterElementII.nextGreaterElements(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
