package main.linkedlist;

/**
 * @author 15304
 */
public class IntersectionNodeOfTwoLinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;


        // pA和pB走过的长度都相同，都是A链和B链的长度之和
        // 如果相交，则会提前在相交点相遇，如果没有相交点，则会在最后相遇。
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
