package main.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2101. 引爆最多的炸弹
 * @author 15304
 */
public class DetonateMaxBombs {

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        // 构建有向图
        for (int i = 0; i < n; i++){
            long x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];
            for (int j = 0; j < n; j++){
                if (i == j) {
                    continue;
                }
                long dx = x - bombs[j][0];
                long dy = y - bombs[j][1];
                // i 可以引爆 j
                if (dx * dx + dy * dy <= r * r){
                    graph[i].add(j);
                }
            }
        }
        int res = 0;
        boolean[] visited = new boolean[n];
        // BFS/DFS遍历，统计能访问到的炸弹个数，取最大值
        for (int i = 0; i < n; i++){
            Arrays.fill(visited, false);
            res = Math.max(res, dfs(graph, visited, i));
        }
        return res;
    }

    private int dfs(List<Integer>[] graph, boolean[] visited, int i) {
        visited[i] = true;
        int cnt = 1;
        for (int j : graph[i]){
            if (!visited[j]){
                cnt += dfs(graph, visited, j);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] bombs = {{2,1,3},{6,1,4}};
        System.out.println(new DetonateMaxBombs().maximumDetonation(bombs));
    }
}
