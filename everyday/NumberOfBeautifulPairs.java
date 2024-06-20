package everyday;

/**
 * 2748. 美丽下标对的数目
 * @author 15304
 */
public class NumberOfBeautifulPairs {

    // 时间复杂度：O(n(10+logC))，C = max{nums} 空间复杂度：O(10)
    public int countBeautifulPairs(int[] nums) {
        int ans = 0;
        int[] cnt = new int[10];
        // 由 x = nums[j]去找符合条件的 nums[i]
        // 为了保证 i < j, 需要先更新结果，再更新cnt
        for (int x : nums){
            for (int y = 1; y < 10 ; y++) {
                // 如果 gcd(x, y) == 1 ，则认为 x 和 y 互质
                // x % 10 计算 nums[j] 的最后一位是多少，范围是[1,9]
                if (gcd(x % 10, y) == 1) {
                    ans += cnt[y];
                }
            }
            // 计算 x 的最高位是多少，很明显属于[1,9]
            while (x >= 10){
                x /= 10;
            }
            // 统计最高位的出现次数
            cnt[x]++;
        }
        return ans;
    }

    // 求最大公约数
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 4};
        NumberOfBeautifulPairs numberOfBeautifulPairs = new NumberOfBeautifulPairs();
        System.out.println(numberOfBeautifulPairs.countBeautifulPairs(nums));
    }
}
