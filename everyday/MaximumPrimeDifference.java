package everyday;

/**
 * 3115.质数的最大距离
 * @author 15304
 */
public class MaximumPrimeDifference {

    // 时间复杂度O(n * sqrt(max(nums)))，空间复杂度O(1)
    public int maximumPrimeDifference(int[] nums) {
        int i = 0;
        while (!isPrime(nums[i])) {
            i++;
        }
        int j = nums.length - 1;
        while (!isPrime(nums[j])) {
            j--;
        }
        return j - i;
    }

    /**
     * 判断一个数是否是质数: 如果一个数 n 是合数（非质数），那么它必然有一个不大于sqrt(n)的因子
     * @param n  本题中 1 <= n <= 100，1 不是质数
     * @return true/false
     */
    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        // 偶数一定不是质数
        if (n % 2 == 0) {
            return false;
        }
        int sqrtN = (int) Math.sqrt(n) + 1;
        // 只需要检查奇数
        for (int i = 3; i < sqrtN; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 10, 20, 3};
        System.out.println(new MaximumPrimeDifference().maximumPrimeDifference(nums));
    }
}
