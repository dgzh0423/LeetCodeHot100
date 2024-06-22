package main.graph;

import java.util.*;

/**
 * 210.课程表 II
 * @author 15304
 */
public class CourseScheduleII {

    // 与207.课程表 基本一致，就是在拓扑排序的基础上，每次将一门入度为0的课程"删除"的同时，加到课程顺序order中
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
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
        int i = 0;
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            order[i] = pre;
            i++;
            numCourses--;
            for(int cur : adjacency.get(pre)) {
                if(--indegrees[cur] == 0) {
                    queue.add(cur);
                }
            }
        }
        // 如果课程数不为0，则说明有环, 返回空数组，否则返回课程的拓扑排序（任意一种即可）
        if(numCourses != 0){
            return new int[]{};
        }else{
            return order;
        }
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(Arrays.toString(new CourseScheduleII().findOrder(4, prerequisites)));
    }
}
