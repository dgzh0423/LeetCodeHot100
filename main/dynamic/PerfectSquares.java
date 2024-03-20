package main.dynamic;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 15304
 */
public class PerfectSquares {

    public int numSquares(int n) {
        return numSquaresHelper(n, new HashMap<>());
    }

    private int numSquaresHelper(int n, HashMap<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n == 0) {
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            count = Math.min(count, numSquaresHelper(n - i * i, map) + 1);
        }
        map.put(n, count);
        return count;
    }

    // 这里的 static 为了保存 1~n 的计算结果，方便后续直接取用
    static ArrayList<Integer> dp = new ArrayList<>();
    public int numSquaresDynamic(int n) {
        // 第一次进入将 0 加入
        if(dp.isEmpty()){
            dp.add(0);
        }
        // 之前是否计算过 n
        if(dp.size() <= n){
            // 接着之前最后一个值开始计算
            for (int i = dp.size(); i <= n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    min = Math.min(min, dp.get(i - j * j) + 1);
                }
                dp.add(min);
            }
        }
        return dp.get(n);
    }

    public static void main(String[] args) {
        PerfectSquares perfect = new PerfectSquares();
        System.out.println(perfect.numSquares(12));
        System.out.println(perfect.numSquaresDynamic(12));
    }
}
