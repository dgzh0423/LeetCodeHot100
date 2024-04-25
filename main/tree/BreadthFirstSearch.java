package main.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 15304
 */
public class BreadthFirstSearch {
    /**
     * BFS求最大深度
     * @param root
     * @return maxDepth
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 初始化： 队列 queue （加入根节点 root ），深度 depth = 0
        List<TreeNode> queue = new LinkedList<>() , tmp;
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            // 初始化一个空列表 tmp ，用于临时存储下一层节点
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }
            // 遍历完一层，继续下一层
            queue = tmp;
            // 深度+1
            depth++;
        }
        return depth;
    }

    /**
     * BFS算法
     * @param root
     * @return res
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

    /**
     * BFS求最小深度
     * @param root
     * @return minDepth
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 将当前队列中的所有节点向四周扩散
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                // 判断是否到达终点（也就是叶子节点）
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                /* 将 cur 的相邻节点加入队列 */
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // 深度+1
            depth++;
        }
        return depth;
    }


    public static void main(String[] args) {

    }
}
