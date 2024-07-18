package main.graph;

import java.util.*;

/**
 * 743. 网络延迟时间
 * @author 15304
 */
public class NetworkDelayTime {

    final int INF = Integer.MAX_VALUE / 2;
    /**
     * 时间复杂度 O(n^2) 空间复杂度 O(n^2)
     * @param times times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是信号从 ui 传递到 vi 的时间
     * @param n n个节点 节点编号从1到n
     * @param k 信号出发点
     * @return 需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 邻接矩阵表示法 适用于稠密图
        int [][] graph = new int[n][n];
        for (int[] row : graph){
            Arrays.fill(row, INF);
        }
        // 数组下标从0开始，而节点编号从1开始，所以需要-1
        for (int[] time : times){
            graph[time[0] - 1][time[1] - 1] = time[2];
        }

        // Dijkstra算法
        int res = 0;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        boolean[] done = new boolean[n];
        while (true){
            // 寻找dist中的最小值(除了出发点和已经确定最短路径的点)
            int x = -1;
            for (int i = 0; i < n; i++){
                if (!done[i] && (x < 0 || dist[i] < dist[x])){
                    x = i;
                }
            }
            // 最后一次算出的最短路就是最大的
            if (x < 0){
                return res;
            }
            // 有节点无法到达
            if (dist[x] == INF){
                return -1;
            }
            // 更新最短路径，最后返回的是dist数组里的最大值
            res = dist[x];
            // 到该节点的最短路径已经确定
            done[x] = true;
            for (int y = 0; y < n; y++){
                // 更新 x 的邻居的最短路
                dist[y] = Math.min(dist[y], dist[x] + graph[x][y]);
            }
        }
    }

    // 时间复杂度：O(mlogm)，其中 m 为 times 的长度 空间复杂度 O(m)
    public int networkDelayTime2(int[][] times, int n, int k) {
        // 邻接表表示法 适用于稀疏图
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] time : times){
            graph[time[0] - 1].add(new int[]{time[1] - 1, time[2]});
        }

        // Dijkstra算法
        int res= 0;
        // 未确定最短路径的节点个数
        int left = n;
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        // 最小堆 用于寻找dist中的最小值(除了出发点和已经确定最短路径的点)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k - 1});
        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            int dx = cur[0];
            int x = cur[1];
            // 节点x之前出堆过
            if (dx > dist[x]){
                continue;
            }
            // 更新最短路径，最后返回的是dist数组里的最大值
            res = dx;
            left--;
            for (int[] neighbor : graph[x]){
                int y = neighbor[0];
                int dy = dx + neighbor[1];
                if (dy < dist[y]){
                    dist[y] = dy;
                    pq.offer(new int[]{dy, y});
                }
            }
        }
        // left != 0 说明有节点无法到达
        return left == 0 ? res : -1;
    }

    public static void main(String[] args) {
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        System.out.println(new NetworkDelayTime().networkDelayTime(times, n, k));
        System.out.println(new NetworkDelayTime().networkDelayTime2(times, n, k));
    }
}
