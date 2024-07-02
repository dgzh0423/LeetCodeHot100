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
     * 判断一个数是否是质数
     * @param n  1 <= n <= 100，但 1 不是质数
     * @return true/false
     */
    private boolean isPrime(int n) {
        // 对于一个数 n，只要它不能被某个整数 i 整除（1 < i <= sqrt(n)），则它就是质数
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 10, 20, 3};
        System.out.println(new MaximumPrimeDifference().maximumPrimeDifference(nums));
    }
}
