package everyday;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * @author 15304
 */
public class NonOverlappingIntervals {

    // 时间复杂度O(nlogn)，空间复杂度O(1)
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 1){
            return 0;
        }
        // 按区间的start升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // 需要删除的区间个数
        int count = 0;
        // 记录当前无重叠区间里最大的end
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 如果当前区间的start小于前一个区间的end，说明有重叠，则删除end较大的区间，更新end
            if (intervals[i][0] < end){
                end = Math.min(end, intervals[i][1]);
                count++;
            }else {
                // 如果没有重叠，就不需要移除，只需要更新尾部的位置即可
                end = intervals[i][1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(new NonOverlappingIntervals().eraseOverlapIntervals(intervals));
    }
}
