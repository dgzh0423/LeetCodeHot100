package main.greedyalgorithm;

/**
 * @author 15304
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int cover = 0, end = 0, step = 0;
        // nums.length - 1 是因为当跳到末尾时，不需要再跳了
        for (int i = 0; i < nums.length - 1; i++){
            cover = Math.max(cover, i + nums[i]);
            // 第一次起跳 或 到达跳跃的边界
            if (i == end){
                end = cover;
                step++;
            }
        }
        return step;
    }
    public static void main(String[] args) {
        JumpGameII step = new JumpGameII();
        int[] nums = {2,3,1,1,4};
        System.out.println(step.jump(nums));
    }
}
