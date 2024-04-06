package main.stack;

import java.util.Arrays;

/**
 * @author 15304
 */
public class DailyTemperatures {
    
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

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperatures.dailyTemperatures(temperatures)));
    }
}
