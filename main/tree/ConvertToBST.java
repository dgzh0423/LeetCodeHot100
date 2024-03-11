package main.tree;

/**
 * BST 的中序遍历是升序的，本题等同于根据中序遍历的序列恢复二叉搜索树。因此我们可以以升序序列中的任一个元素作为根节点，以该元素左边的升序序列构建左子树，以该元素右边的升序序列构建右子树
 * 要求高度平衡，因此我们需要选择升序序列的中间元素作为根节点
 * @author 15304
 */
public class ConvertToBST {
    public TreeNode sortedArray(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        // 以升序数组的中间元素作为根节点 root。
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        // 递归的构建 root 的左子树与右子树。
        root.left = dfs(nums, low, mid - 1);
        root.right = dfs(nums, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {

    }
}
