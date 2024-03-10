package main.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 15304
 */
public class BreadthFirstSearch {
    /**
     * BFS
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //初始化： 队列 queue （加入根节点 root ），层数 res = 0
        List<TreeNode> queue = new LinkedList<>() , tmp;
        queue.add(root);
        int res = 0;

        while (!queue.isEmpty()) {
            //初始化一个空列表 tmp ，用于临时存储下一层节点
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            //遍历完一层，继续下一层
            queue = tmp;
            res++;
        }
        return res;
    }

    /**
     * 二叉树层序遍历 （即逐层地，从左到右访问所有节点）
     * @param root
     * @return
     */
    public List<List<Integer>> bfs(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            //获取当前队列的长度，即当前层的节点个数
            int size= queue.size();
            //初始化 tmp ，用于储存当前层的节点值
            List<Integer> tmp = new ArrayList<>();
            for(int i=0; i<size; ++i) {
                TreeNode cur = queue.remove();
                tmp.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
