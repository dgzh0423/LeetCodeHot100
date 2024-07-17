package main.greedy;

/**
 * 134. 加油站
 * @author 15304
 */
public class GasStation {

    /**
     * @param gas 在站点i可以获得gas[i]的汽油
     * @param cost 从站点i到站点i+1需要消耗cost[i]的汽油
     * @return 存在一个起始位置，从该位置开始，可以按照顺时针方式一次遍历整个环
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0, minSum = 0;
        int start = 0;
        // 找到sum最小的加油站
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            if (sum < minSum){
                minSum = sum;
                start = i + 1;
            }
        }
        // 总油量小于总消耗，则无解
        if (sum < 0){
            return -1;
        }
        // 环形加油站
        return start == n ? 0 : start;
    }

    // 贪心算法：如果选择站点 i 作为起点「恰好」无法走到站点 j，那么 i 和 j 中间的任意站点 k 都不可能作为起点。
    int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
        }
        if (sum < 0) {
            // 总油量小于总的消耗，无解
            return -1;
        }
        // 记录油箱中的油量，开始时油箱为空
        int tank = 0;
        // 记录起点
        int start = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                // 无法从 start 到达 i + 1
                // 所以站点 i + 1 应该是起点
                tank = 0;
                start = i + 1;
            }
        }
        return start == n ? 0 : start;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        GasStation gasStation = new GasStation();
        int i = gasStation.canCompleteCircuit(gas, cost);
        System.out.println(i);
        int i1 = gasStation.canCompleteCircuit2(gas, cost);
        System.out.println(i1);
    }
}
