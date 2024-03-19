package main.greedyalgorithm;

/**
 * @author 15304
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if (nums.length == 1){
            return true;
        }
        // cover 最大可达位置
        int cover = 0;
        for (int i = 0; i < nums.length; i++){
            // cover >= i，此时可以继续往前更新cover
            if (cover >= i){
                cover = Math.max(cover, i + nums[i]);
            }
            // 若 cover < i，则可以提前返回false
            else {
                return false;
            }
        }
        // 若能遍历完，则一定能到达
        return true;
    }
    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = {3,2,1,0,4};
        System.out.println(jumpGame.canJump(nums));
    }
}
