package main.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 15304
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 ){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        // 创建一个大小为k的小根堆（优先队列），用于快速得到 k 个节点中的最小节点
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        // 将k个链表的头结点放入queue中
        for (ListNode head : lists) {
            if (head != null){
                queue.add(head);
            }
        }
        while (!queue.isEmpty()){
            // 得到最小节点
            ListNode node = queue.poll();
            // 将最小节点接到结果中
            p.next = node;
            // 将最小节点的下个节点补到queue中，继续找下一个最小节点
            if (node.next != null){
                queue.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {}
}
