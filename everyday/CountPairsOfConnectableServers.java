package everyday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 15304
 */
public class CountPairsOfConnectableServers {

    /**
     * 3067. 在带权树网络中统计可连接服务器对数目
     * @param edges edges[i] = [a, b, weight] 表示节点 a 和 b 之间有一条双向边，边的权值为 weight
     * @param signalSpeed 从 c 到 a 的距离是可以被 signalSpeed 整除的 且 从 c 到 b 的距离是可以被 signalSpeed 整除的。
     * @return count[i] 表示通过服务器 i 可连接的服务器对的数目
     */
    public int[] countPairsOfConnectableServers(int[][] edges, int signalSpeed) {
        // 将给的 int[][] edges 转化为图
        int n = edges.length + 1;
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            int weight = e[2];
            graph[x].add(new int[]{y, weight});
            graph[y].add(new int[]{x, weight});
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // 当与节点graph[i]相连的节点个数小于等于1，则无法通过节点graph[i]连接其他节点，跳过
            if (graph[i].size() <= 1) {
                continue;
            }
            // int[] e 保存的是与节点graph[i]相连的节点e[0]，和两个节点之间的边的权值e[1]
            for (int[] e : graph[i]) {
                int cnt = dfs(e[0], i, e[1], graph, signalSpeed);
                ans[i] += cnt * sum;
                sum += cnt;
            }
        }
        return ans;
    }


    // 返回从图中节点x开始，满足路径和为signalSpeed的倍数的子树节点数量
    private int dfs(int x, int root, int sum, List<int[]>[] graph, int signalSpeed) {
        int cnt = sum % signalSpeed == 0 ? 1 : 0;
        for (int[] e : graph[x]) {
            int y = e[0];
            if (y != root) {
                cnt += dfs(y, x, sum + e[1], graph, signalSpeed);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        CountPairsOfConnectableServers countPairsOfConnectableServers = new CountPairsOfConnectableServers();
        int[][] edges = {{0, 6, 3}, {6, 5, 3}, {0, 3, 1}, {3, 2, 7}, {3, 1, 6}, {3, 4, 2}};
        int signalSpeed = 3;
        System.out.println(Arrays.toString(countPairsOfConnectableServers.countPairsOfConnectableServers(edges, signalSpeed)));
    }
}
