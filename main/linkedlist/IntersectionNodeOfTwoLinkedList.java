package main.linkedlist;

public class IntersectionNodeOfTwoLinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 若相交，链表A： a+c, 链表B : b+c. 有a+c+b+c = b+c+a+c，会在公共处c起点相遇。
        // 若不相交，a +b = b+a 。因此相遇处是NULL
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
    public static void main(String[] args) {

    }
}
