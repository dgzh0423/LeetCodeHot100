package main.tree;

import java.util.*;

/**
 * @author 15304
 */
public class BreadthFirstSearch {

    /**
     * 102.二叉树的层序遍历
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
            int size = queue.size();
            //初始化 tmp ，用于储存当前层的节点值
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < size; i++) {
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

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 107.二叉树的层次遍历 II (自底向上输出每层节点)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return res;
        }
        List<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        traverse(nodes);
        return res;
    }

    private void traverse(List<TreeNode> curLevelNodes) {
        // base case
        if (curLevelNodes.isEmpty()) {
            return;
        }
        // 保存当前层的节点的值和下一层的节点列表
        List<Integer> nodeValues = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        for (TreeNode node : curLevelNodes) {
            nodeValues.add(node.val);
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }
        // 前序位置添加结果，可以得到自顶向下的层序遍历
        // res.add(nodeValues);
        traverse(nextLevelNodes);
        // 后序位置添加结果，可以得到自底向上的层序遍历结果
        res.add(nodeValues);
    }

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
            // 每遍历完一层，深度+1
            depth++;
        }
        return depth;
    }

    /**
     * 752.打开转盘锁
     * @param deadends 死亡数字列表
     * @param target 目标密码
     * @return 解开密码锁的最少次数
     */
    int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int size = q.size();
            // 将当前队列中的所有节点向周围扩散
            for (int i = 0; i < size; i++) {
                String cur = q.poll();

                // 遇到死亡密码则跳过
                if (deads.contains(cur)) {
                    continue;
                }
                // 如果找到目标密码，提前返回
                if (cur.equals(target)) {
                    return step;
                }

                // 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 在这里增加步数
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    // 将 s[i] 向上拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    // 将 s[j] 向下拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    public static void main(String[] args) {

    }
}
