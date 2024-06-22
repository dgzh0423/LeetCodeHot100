package main.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 797.所有可能的路径
 * @author 15304
 */
public class AllPathsFromSourceToTarget {

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 时间复杂度：O(n * 2^n)，空间复杂度：O(n)，其中 n 是节点个数
     * @param graph 有 n 个节点的有向无环图（DAG）--邻接表表示
     * @return 所有从节点 0 到节点 n-1 的路径
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int node, LinkedList<Integer> path) {
        // 添加当前节点到路径
        path.addLast(node);
        int n = graph.length;
        if (node == n - 1) {
            // 到达终点，将路径加入结果
            // 不能直接 res.add(path)，因为 path 是引用类型，会修改方法外的 path，所以要复制一份
            res.add(new LinkedList<>(path));
            // 可以在这直接 return，但要 removeLast 正确维护 path
            // path.removeLast();
            // return;
            // 不 return 也可以，因为图中不包含环，不会出现无限递归
        }
        // 递归每个相邻节点
        for (int v : graph[node]) {
            traverse(graph, v, path);
        }
        // 从路径移出节点 node
        path.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println(new AllPathsFromSourceToTarget().allPathsSourceTarget(graph));
    }
}
