package main.tree;

import java.util.HashMap;

/**
 * 105.根据前序和中序序列构造二叉树
 * @author 15304
 */
public class PrePlusInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 因为preorder 和 inorder 均无重复元素
        // 故可用map将 inorder 的val值和对应的下标保存起来，实现快速得到根节点（包括子树）下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    // [p_start, p_end) 和 [i_start, i_end) 都是左闭右开区间
    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end,
                                     HashMap<Integer, Integer> map) {
        if (p_start == p_end) {
            return null;
        }
        // 前序序列的第一个元素即为根节点
        TreeNode root = new TreeNode(preorder[p_start]);
        int i_root_index = map.get(root.val);
        // 就可以确定左子树有多少节点
        int leftNum = i_root_index - i_start;
        // 递归构造左子树和右子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start  + 1 + leftNum, inorder, i_start, i_root_index, map);
        root.right = buildTreeHelper(preorder, p_start  + 1 + leftNum, p_end, inorder, i_root_index + 1, i_end, map);
        // 返回根节点
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = new PrePlusInorderTraversal().buildTree(preorder, inorder);
        System.out.println(root.val);
    }
}
