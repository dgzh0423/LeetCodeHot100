package main.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 106.从中序与后序遍历序列构造二叉树
 * @author 15304
 */
public class InorderPlusPostOrderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        Map<Integer, Integer> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(0, n, postorder, 0, n, map);
    }

    // [i_start, i_end) 和 [p_start, p_end) 都是左闭右开区间
    private TreeNode buildTreeHelper(int i_start, int i_end, int[] postorder, int p_start, int p_end,
                                     Map<Integer, Integer> map) {
        if (i_start >= i_end || p_start >= p_end) {
            return null;
        }
        // 后序遍历的最后一个元素就是根节点
        TreeNode root = new TreeNode(postorder[p_end - 1]);
        // 根节点在中序遍历中的位置
        int root_inorder = map.get(root.val);
        // 就可以确定左子树有多少节点
        int left_size = root_inorder - i_start;
        // 然后递归构建左右子树
        root.left = buildTreeHelper(i_start, root_inorder, postorder, p_start, p_start + left_size, map);
        root.right = buildTreeHelper(root_inorder + 1, i_end, postorder, p_start + left_size, p_end - 1, map);
        // 返回根节点
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = new InorderPlusPostOrderTraversal().buildTree(inorder, postorder);
        System.out.println(root.val);
    }
}
