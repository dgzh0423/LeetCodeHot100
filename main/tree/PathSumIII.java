package main.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 15304
 * 对照  560.和为 K 的子数组
 */
public class PathSumIII {
    Map<Long, Integer> prefixMap;
    int target;

    /**
     * 定义：一个节点的前缀和就是该节点到根之间的node.val之和。两节点间的路径和 = 两节点的前缀和之差
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和， value是该前缀和对应的节点个数
        prefixMap = new HashMap<>();
        target = sum;
        // 这里认为根结点之前的前缀和是 0，这样可以计算以根结点为起点的路径的前缀和
        prefixMap.put(0L, 1);
        return recur(root, 0L);
    }

    private int recur(TreeNode node, long curSum) {
        if(node == null) {
            return 0;
        }
        // 遍历到一个node，前缀和 += node.val
        curSum += node.val;
        int res;
        // 如果前缀和在节点A和节点B处相差target，则位于节点A和节点B之间的元素之和是target
        res = prefixMap.getOrDefault(curSum - target, 0);
        // 路径方向必须是向下的（只能从父节点到子节点），因此当我们把一个节点的前缀和信息更新到map里时，它应当只对其子节点们有效
        prefixMap.put(curSum, prefixMap.getOrDefault(curSum, 0) + 1);
        int left = recur(node.left, curSum);
        int right = recur(node.right, curSum);

        // 在遍历完一个节点的所有子节点后，将其从map中除去
        prefixMap.put(curSum, prefixMap.get(curSum) - 1);

        return res + left + right;
    }

    public static void main(String[] args) {

    }
}
