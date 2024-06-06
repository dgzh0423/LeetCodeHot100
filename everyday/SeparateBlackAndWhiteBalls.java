package everyday;

/**
 * @author 15304
 *
 * 2938. 区分黑球与白球
 */
public class SeparateBlackAndWhiteBalls {

    /**
     * @param s 1 代表黑球，0 代表白球，每一步只能交换相邻的球，使得 0 都在 1 的左边
     * @return 将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数
     */
    public long minimumSteps(String s) {
        int steps = 0;
        // 左边黑球的个数
        int cnt = 0;
        // 贪心算法：每遍历到一个白球，就将其交换到左边的位置上
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                cnt++;
            } else {
                // 遍历到白球时，它的左边总是 i-cnt 个白球和 cnt 个黑球，因此它需要交换cnt次
                steps += cnt;
            }
        }
        return steps;
    }

    // 倒序遍历：每遍历到一个黑球，就将其交换到右边的位置上
    public long minimumSteps2(String s) {
        long ans = 0;
        int right = s.length() - 1;
        int left = right;
        while (left >= 0) {
            // 遍历到黑球时，它的右边总是 right-left 个白球
            if (s.charAt(left) == '1') {
                ans += right - left;
                right--;
            }
            left--;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new SeparateBlackAndWhiteBalls().minimumSteps("0100000010"));
        System.out.println(new SeparateBlackAndWhiteBalls().minimumSteps2("0100000010"));
    }
}
