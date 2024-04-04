package main.linkedlist;

/**
 * @author 15304
 */
public class LinkedListCycle {

    /**
     * 快慢指针法
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        //快慢两个指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            //慢指针每次走一步
            slow = slow.next;
            //快指针每次走两步
            fast = fast.next.next;
            //如果相遇，说明有环，直接返回true
            if (slow == fast) {
                return true;
            }
        }
        //否则就是没环
        return false;
    }

    /**
     * 先反转，再比较
     * @param head
     * @return
     */
    public boolean hasCycleReversion(ListNode head) {
        ListNode rev = reverseList(head);
        //如果有环，那么链表反转之后，原来的头结点和反转之后的头结点一定是同一个
        return head != null && head.next != null && rev == head;
    }

    public ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            ListNode temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，
            //其实就是把新链表挂到访问的原链表节点的后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }


    public static void main(String[] args) {

    }
}
