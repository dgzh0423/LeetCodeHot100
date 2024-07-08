package main.linkedlist;

/**
 * 24.两两交换链表中的节点
 * @author 15304
 */
public class SwapNodesInPairs {

    /**
     * 递归法
     * @param head
     * @return
     */
    public ListNode swapPairsRecursion(ListNode head) {

        // 终止条件：当前无节点或者只有一个节点，无法进行交换
        if (head == null || head.next == null){
            return head;
        }

        ListNode next = head.next;
        // head指向后面交换完成的子链表，next指向head，完成交换
        head.next = swapPairsRecursion(next.next);
        next.next = head;

        // 交换完成，next变为head
        return next;

    }

    /**
     * 非递归法
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 初始化： 伪头节点 dum ，temp 指向 dum
        ListNode dum = new ListNode(0);
        dum.next = head;
        ListNode temp = dum;
        while(temp.next != null && temp.next.next != null) {
            // start和end指向要交换的两个节点
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            // 交换过程
            temp.next = end;
            start.next = end.next;
            end.next = start;
            // 交换完成，更新temp位置
            temp = start;
        }
        return dum.next;
    }

    public static void main(String[] args) {}
}
