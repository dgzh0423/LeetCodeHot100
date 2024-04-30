package main.dynamic;

import java.util.Arrays;

import static main.dynamic.LongestIncreaseSubsequence.lengthOfLIS;

/**
 * @author 15304
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        // 实现二维降到一维
        Arrays.sort(envelopes, (int[] a, int[] b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        // 对高度数组寻找 LIS
        return lengthOfLIS(height);
    }

    public static void main(String[] args) {
        RussianDollEnvelopes rde = new RussianDollEnvelopes();
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println(rde.maxEnvelopes(envelopes));
    }
}
