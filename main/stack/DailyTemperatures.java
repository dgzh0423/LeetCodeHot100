package main.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author 15304
 */
public class DailyTemperatures {

    // 最坏情况下时间复杂度为 O(n^2)
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        int[] res = new int[length];
        // ⭐从后面开始查找⭐
        // res[temperatures.length - 1] = 0 因为是最后一天
        for (int i = length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < length) {
                if (temperatures[j] > temperatures[i]) {
                    //如果找到就停止while循环
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    //如果没找到，并且res[j]==0。说明第j个元素后面没有比第j个元素大的值了，直接终止while循环
                    res[i] = 0;
                    break;
                } else {
                    //如果没找到，并且res[j] != 0说明第j个元素后面有比第j个元素大的值，
                    //然后我们让j跳到那个值，再和第i个温度值比较
                    j += res[j];
                }
            }
        }
        return res;
    }

    // 单调栈解法 时间复杂度O(n)
    public int[] dailyTemperaturesII(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // stack保存的是数组下标
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--){
            int temp = temperatures[i];
            // 在将当前温度压入栈之前，先弹出栈中所有小于等于当前温度的元素，这样保证栈内元素从栈顶向下是递增的
            while (!stack.isEmpty() && temp >= temperatures[stack.peek()]){
                stack.pop();
            }
            // 计算索引差距，也就是所求天数
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(temperatures)));
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperaturesII(temperatures)));
    }
}
