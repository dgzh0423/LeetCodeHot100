package main.linkedlist;

/**
 * 876.链表的中间节点
 * @author 15304
 */
public class MiddleOfTheLinkedList {

    // 返回链表的中间结点，如果有两个中间结点，则返回第二个中间结点。
    public ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }

    public static void main(String[] args) {}
}
