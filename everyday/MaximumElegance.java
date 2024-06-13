package everyday;

import java.util.*;

/**
 * 2813.子序列最大优雅度 时间复杂度O(nlogn) 空间复杂度O(n)
 * @author 15304
 */
public class MaximumElegance {

    /**
     * @param items items[i] = [profit_i, category_i]，profit_i 优先级更高
     * @param k 子序列的长度
     * @return 最大优雅度
     */
    public long findMaximumElegance(int[][] items, int k) {
        // 根据profit降序排序，很关键
        Arrays.sort(items, (a,b) -> b[0] - a[0]);

        long res = 0;
        long totalProfit = 0;
        Set<Integer> categories = new HashSet<>();
        Deque<Integer> duplicate = new ArrayDeque<>();

        // 整体思路：先将最大利润的K个项目加起来，然后遍历剩余项目，重点考虑能否让 distinctCategories 变大
        for (int i = 0; i < items.length; i++) {
            int profit = items[i][0];
            int category = items[i][1];
            if (i < k){
               // 先将前k个最大利润的项目加上
               totalProfit += profit;
               // 遇到重复类别的项目，将其利润入栈，且该利润是重复项目中较小的
               if (!categories.add(category)){
                   duplicate.push(profit);
               }
            }
           // 从第 k + 1 个项目开始，如果该类别与前k个项目类别都不同且前k个项目中有重复类别，则从重复项目中选择最小利润的项目删掉
            else if (!duplicate.isEmpty() && categories.add(category)) {
                // 由于profit降序排序，所以后入栈的利润是最小的
                totalProfit += profit - duplicate.pop();
            }
            // 其他情况则无需替换
            // 更新最大优雅度
            res = Math.max(res, totalProfit + (long) categories.size() * categories.size());
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] items = {{3,2},{5,1},{10,1}};
        System.out.println(new MaximumElegance().findMaximumElegance(items, 2));
    }
}
