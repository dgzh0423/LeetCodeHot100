package everyday;

/**
 * @author 15304
 */
public class DistributeCandies {

    /**
     * 2928.给小朋友们分糖果 I
     * @param n n 颗糖果分给 3 位小朋友
     * @param limit 每个小朋友不超过多少颗糖果
     * @return 分配方案数
     */
    public int distributeCandies(int n, int limit) {
        // 不考虑limit，共有 C(n+2,2) 种方案
        // 至少一个小朋友分到的糖果超过 limit = (A超过limit + B超过limit + C超过limit) - (A和B都超过limit + B和C都超过limit + A和C都超过limit) + 三人都超过limit
        return cal(n + 2) - 3 * cal(n - (limit + 1) + 2) + 3 * cal(n - 2 * (limit + 1) + 2) - cal(n - 3 * (limit + 1) + 2);
    }

    /**
     * @param x 从x个不同元素中选2个
     * @return 组合数 C(x,2) = x! / 2!(x-2)! = x * (x - 1) / 2
     */
    private int cal (int x){
        return x > 1 ? x * (x - 1) / 2 : 0;
    }

    public static void main(String[] args) {
        int n = 5, limit = 2;
        DistributeCandies distributeCandies = new DistributeCandies();
        System.out.println(distributeCandies.distributeCandies(n, limit));
    }
}
