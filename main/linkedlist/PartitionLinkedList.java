package main.linkedlist;

/**
 * 86.分割链表
 * @author 15304
 */
public class PartitionLinkedList {

    ListNode partition(ListNode head, int x) {
        // 定义两个虚拟节点, dummy1 和 dummy2 分别代表小于 x 和大于等于 x 的链表
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);
        // p, p1, p2用于分解链表
        ListNode p1 = dummy1, p2 = dummy2;
        ListNode p = head;
        while (p != null){
            if (p.val > x){
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            // 不能直接让 p 指针前进，因为节点 p 并没有完全断开
            // p = p.next
            ListNode temp = p.next;
            // 断开原链表中的每个节点的 next 指针
            p.next = null;
            p = temp;
        }
        p1.next = dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {

    }
}
