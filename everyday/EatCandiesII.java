package everyday;

import java.util.Arrays;

/**
 * @author 15304
 */
public class EatCandiesII {

    /**
     * 1103. 分糖果 II
     * @param candies 总糖果数
     * @param num_people 小朋友总人数
     * @return 每个人分到的糖果数量
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int i = 0;
        while (candies > 0) {
            result[i % num_people] += Math.min(candies, i + 1);
            candies -= i + 1;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int candies = 7;
        int num_people = 4;
        System.out.println(Arrays.toString(new EatCandiesII().distributeCandies(candies, num_people)));
    }
}
