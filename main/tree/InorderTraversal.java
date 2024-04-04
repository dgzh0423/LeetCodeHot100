package main.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历：中 - 左 - 右
 * 中序遍历：左 - 中 - 右
 * 后序遍历：左 - 右 - 中
 * @author 15304
 */
public class InorderTraversal {

    /**
     * 递归方法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        //保存遍历结果
        List<Integer> res = new ArrayList<>();
        dfs(res,root);
        return res;
    }

    void dfs(List<Integer> res, TreeNode root) {
        if(root==null) {
            return;
        }
        //按照 左-中-右的方式遍历
        dfs(res,root.left);
        res.add(root.val);
        dfs(res,root.right);
    }

    /**
     * 借用stack实现
     * @param root
     * @return
     */
    public List<Integer> inorderByStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null) {
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            if(root != null) {
                stack.add(root);
                root = root.left;
            }
            //当前节点为空，说明左边走到头了，从栈中弹出节点并保存
            //然后转向右边节点，继续上面整个过程
            else {
                TreeNode tmp = stack.pop();
                res.add(tmp.val);
                root = tmp.right;
            }
        }
        return res;
    }

    /**
     * 用递归和迭代的方式都使用了辅助的空间，而莫里斯遍历的优点是没有使用任何辅助空间。
     * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构。
     * @param root
     * @return
     */
    public List<Integer> morrisTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode pre;
        while(root != null) {
            //如果左节点不为空，就将当前节点连带右子树全部挂到左节点的最右子树下面
            if(root.left != null) {
                pre = root.left;
                while(pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root;

                //将root指向root的left
                TreeNode tmp = root;
                root = root.left;
                tmp.left = null;
            }
            //左子树为空，则打印这个节点，并向右边遍历
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
