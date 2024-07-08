package main.linkedlist;

/**
 * 19.删除链表的倒数第N个节点
 * @author 15304
 */
public class RemoveNthNode {

    /**
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点，防越界
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        ListNode x = findFromEnd(dummy, n + 1);
        // 删掉倒数第 n 个节点
        x.next = x.next.next;
        return dummy.next;
    }

    /**
     * 返回链表的倒数第 k 个节点，双指针只需要遍历一次
     * @param head
     * @param k
     * @return
     */
    public ListNode findFromEnd(ListNode head, int k){
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }

    public static void main(String[] args) {}
}
