package main.linkedlistmedium;

/**
 * @author 15304
 */
public class SortLinkedList {
    /*
    时间空间复杂度分别为 O(nlogn)和 O(1)
     */
    public ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层合并有序链表
        return mergeTwoLists(left, right);
    }

    //  找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            //快慢双指针 fast, slow
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //初始化： 伪头节点 dum ，节点 cur 指向 dum
        ListNode dum = new ListNode(0), cur = dum;

        while (list1 != null && list2 != null) {
            //当 list1.val < list2.val时, cur的后继节点指定为list1, 且list1向前走一步
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            }
            //当 list1.val ≥ list2.val时，cur的后继节点指定为list2, 且list2向前走一步
            else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        //合并剩余尾部，list1或者list2为空
        cur.next = list1 != null ? list1 : list2;
        //合并链表在伪头节点 dum 之后，因此返回 dum.next 即可
        return dum.next;
    }

    public static void main(String[] args) {

    }
}
