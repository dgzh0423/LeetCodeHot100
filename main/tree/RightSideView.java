package main.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 15304
 */
public class RightSideView {

    /**
     *利用 BFS 进行层次遍历，记录下每层的最后一个元素
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        //addLast
        queue.add(root);
        while (!queue.isEmpty()) {
            //获取当前队列的长度，即当前层的节点个数
            int size= queue.size();

            for(int i = 0; i < size; i++) {
                //removeFirst
                TreeNode cur = queue.remove();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                //将当前层的最后一个节点放入结果列表
                if (i == size - 1){
                    res.add(cur.val);
                }
            }
        }
        return res;
    }

    List<Integer> ans = new ArrayList<>();
    /**
     * DFS 但是按 中-右-左 的顺序遍历
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        // 从根节点开始访问，根节点深度是0
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // res中保存每层最右边的节点，等价于在每个depth访问的第一个节点
        if (depth == ans.size()) {
            ans.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

    public static void main(String[] args) {

    }
}
