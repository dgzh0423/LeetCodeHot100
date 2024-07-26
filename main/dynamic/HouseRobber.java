package main.dynamic;

/**
 * @author 15304
 */
public class HouseRobber {

    // 198. 打家劫舍
    public int rob(int[] nums) {
        int n = nums.length;
        // dp[i] 表示从 i 间房子中能偷的最大金额
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++){
            // 有两种选择：偷前i-1个房子，最后一间不偷 / 偷前i-2间房子，偷最后一间
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[n];
    }

    // 空间优化
    private int robHelper(int[] nums, int start, int end){
        int dp_i_1 = 0, dp_i_2 = 0, dp_i;
        // 房屋区间左闭右开 [start, end)
        for (int i = start; i < end; i++){
            dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i_1;
    }

    // 213. 打家劫舍 II 第一间和最后一间是相邻的
    public int robII(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }
        // 偷 nums[0]，那么 nums[1] 和 nums[n−1] 不能偷，问题变成从 nums[2] 到 nums[n−2]的打家劫舍问题
        // 不偷 nums[0]，那么 nums[1] 和 nums[n−1] 就可以偷，问题变成从 nums[1] 到 nums[n−1]的打家劫舍问题
        return Math.max(robHelper(nums, 1, n), nums[0] + robHelper(nums, 2, n-1));
    }

    // 337. 打家劫舍 III 树形房屋
    public int robIII(TreeNode root) {
        int[] res = dfs(root);
        // 根节点选或不选的最大值
        return Math.max(res[0], res[1]);
    }

    // int[0]表示选择当前节点的最大金额，int[1]表示不选择当前节点的最大金额
    private int[] dfs(TreeNode node) {
        // 没有节点，怎么选都是 0
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        // 选当前节点，那么当前节点的左右孩子节点都不能选
        int rob = left[1] + right[1] + node.val;
        // 不选当前节点，那么左右孩子节点都可以选
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{rob, notRob};
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        int[] nums = {2,7,9,3,1};
        System.out.println(robber.rob(nums));
        System.out.println(robber.robHelper(nums, 0, nums.length));
        nums = new int[]{2,3,2};
        System.out.println(robber.robII(nums));
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(1);
        System.out.println(robber.robIII(root));
    }
}
