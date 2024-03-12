package main.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 15304
 */
public class CourseSchedule {

    /**
     * 判断课程安排图是否是 有向无环图(DAG)
     * 拓扑排序：1. 选择一个没有前驱(入度为0)的顶点，并输出  2. 删除该顶点和以该顶点为起点的有向边  3. 重复操作1和2，直至图为空，或者图中不存在无前驱的顶点
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // cp: [1,0] 对应 0->1, 先上0，再上1
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // 记录所有入度=0的course
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // 删除该课程和以该课程为前置的有向边，有向边关系保存在adjacency
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre))
                if(--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;

    }

    public static void main(String[] args) {

    }
}
