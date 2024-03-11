package main.tree;

import java.util.HashMap;

/**
 * 根据前序和中序序列构造二叉树
 * @author 15304
 */
public class PrePlusInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //因为preorder 和 inorder 均无重复元素
        //故可用map将 inorder 的val值和对应的下标保存起来
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length, map);
    }

    /**
     * 左子树范围：[p_start, p_end)   [i_start, i_end)
     *
     * @param preorder
     * @param p_start
     * @param p_end
     * @param inorder
     * @param i_start
     * @param i_end
     * @param map
     * @return
     */
    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end,
                                     HashMap<Integer, Integer> map) {
        if (p_start == p_end) {
            return null;
        }
        //给定一个前序序列，第一个元素即为root
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        int i_root_index = map.get(root_val);
        // 左子树的node数
        int leftNum = i_root_index - i_start;
        // 递归构造左子树和右子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start  + 1 + leftNum, inorder, i_start, i_root_index, map);
        root.right = buildTreeHelper(preorder, p_start  + 1 + leftNum, p_end, inorder, i_root_index + 1, i_end, map);

        return root;
    }

    public static void main(String[] args) {

    }
}
