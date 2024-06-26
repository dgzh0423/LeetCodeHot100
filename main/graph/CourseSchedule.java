package main.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207.课程表
 * @author 15304
 */
public class CourseSchedule {

    /**
     * 时间复杂度 O(V+E)，空间复杂度O(V+E)，V是节点数，E是边数
     * 拓扑排序：1. 选择一个没有前驱(入度为0)的顶点，并输出  2. 删除该顶点和以该顶点为起点的有向边  3. 重复操作1和2，直至图为空，或者图中不存在无前驱的顶点
     * @param numCourses 选修 numCourses 门课程，记为 0 到 numCourses - 1
     * @param prerequisites 前置课程关系
     * @return 是否能完成所有课程的学习 等价于 判断课程安排图是否是有向无环图(DAG)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // indegrees记录每节课的入度
        int[] indegrees = new int[numCourses];
        // adjacency记录哪些课程需要先上哪个前置课
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // cp: [1,0] 对应 0->1, 先上0，再上1
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // 记录所有入度=0的course
        for(int i = 0; i < numCourses; i++) {
            if(indegrees[i] == 0) {
                queue.add(i);
            }
        }
        // 删除该课程和以该课程为前置的有向边
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre)) {
                if(--indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        return numCourses == 0;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println(new CourseSchedule().canFinish(4, prerequisites));
    }
}
