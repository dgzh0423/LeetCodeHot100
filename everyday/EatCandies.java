package everyday;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 15304
 */
public class EatCandies {

    /**
     * 575. 分糖果
     * @param candyType 每颗糖的种类数组
     * @return 仅吃掉 n / 2 枚糖的情况下，可以吃到糖的最多种类数
     */
    public int distributeCandies(int[] candyType) {
        // 核心点在于如何对candyType去重：先想到用set
        Set<Integer> set = new HashSet<>();
        for (int candy : candyType) {
            set.add(candy);
        }
        return Math.min(set.size(), candyType.length / 2);
    }

    public int distributeCandies2(int[] candyType) {
        // 优化：使用数组实现set去重
        // new int[200001]是因为 -10^5 <= candyType[i] <= 10^5
        int[] count = new int[200001];
        int res = 0;
        for (int candy : candyType) {
            if (count[candy] == 0) {
                res++;
            }
            count[candy]++;
        }
        return Math.min(res, candyType.length / 2);
    }


    public static void main(String[] args) {
        int[] candyType = {6,6,6,6};
        System.out.println(new EatCandies().distributeCandies(candyType));
        System.out.println(new EatCandies().distributeCandies2(candyType));
    }
}
