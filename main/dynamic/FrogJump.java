package main.dynamic;

/**
 * @author 15304
 */
public class FrogJump {
    public boolean canCross(int[] stones) {
        int len = stones.length;
        // 第一步只能跳一步到第2块石子上
        if (stones[1] != 1){
            return false;
        }
        // dp[i][k] 表示能否以 k步 跳到 第i块 石子上
        boolean[][] dp = new boolean[len + 1][len + 1];
        // base case
        dp[1][1] = true;

        for (int i = 2; i < len; i++) {
            for (int j = 1; j < i; j++) {
                int k = stones[i] - stones[j];
                // 从第j个位置起跳的步数不超过 j+1， 因为每次跳跃，下标至少增加 1，而步长最多增加 1
                if (k <= j + 1){
                    // dp[j][k - 1]：先跳 k−1步 到第j块石子，再跳 k步 到第i块石子
                    // dp[j][k]：先跳 k步 到第j块石子，再跳 k步 到第i块石子
                    // dp[j][k + 1]：先跳 k+1步 到第j块石子，再跳 k步 到第i块石子
                    dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                }
            }
        }
        // 最后检查能否以 k步 跳到最后一块石头上
        for (int k = 1; k < len; k++) {
            if (dp[len - 1][k]){
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();
        //int [] stones = {0,1,3,5,6,8,12,17};
        int [] stones = {0,1,2,3,4,8,9,11};
        System.out.println(frogJump.canCross(stones));
    }
}
