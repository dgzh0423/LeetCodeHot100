package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    /*
    时间复杂度：O(nlogn)，空间复杂度：O(logn)
     */
    public static int[][] merge(int[][] intervals) {

        if (intervals.length == 0) return new int[0][2];

        //先按照区间左端点升序排序 Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));

        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int L = interval[0], R = interval[1];
            //将第一个区间加入 merged 数组中
            //如果当前区间的左端点L > 数组 merged 中最后一个区间的右端点merged.get(merged.size() - 1)[1]，那么它们不会重合,可以直接加入merged
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else { //否则重合，需要比较当前区间的右端点与数组 merged 中最后一个区间的右端点，取较大者作为数组 merged 中最后一个区间的右端点
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }
}
