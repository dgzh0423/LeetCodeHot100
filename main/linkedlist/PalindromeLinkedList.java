package main.linkedlist;

/**
 * 234.回文链表
 * @author 15304
 */
public class PalindromeLinkedList {

    /**
     * 快慢指针法：快指针走到末尾，慢指针刚好到中间。其中慢指针将前半部分反转。然后比较
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        // slow和fast分别用于走一半和走全程
        ListNode slow = head, fast = head;
        // pre和prepre用于反转链表前一半
        ListNode pre = head, prepre = null;
        // 一边遍历，一边反转前半个链表
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        // if判断是为了处理链表个数为奇数的情况
        if(fast != null) {
            slow = slow.next;
        }
        // 将反转后的前半链表与后半链表比较
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {}
}
