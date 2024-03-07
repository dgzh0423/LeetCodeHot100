package main.linkedlist;

/**
 * @author 15304
 */
public class LinkedListCycleMedium {

    /**
     * 从头结点head开始遍历，让每个结点的next指针指向自己，如果有环，则有head.next = head，此时head指向入环的第一个节点
     * 该方法超出时间限制
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //如果head为空，或者他的next指向为空，直接返回null
        if (head == null || head.next == null)
            return null;
        //如果出现head.next = head表示有环
        if (head.next == head)
            return head;
        ListNode nextNode = head.next;
        //当前节点的next指向他自己，相当于把它删除了
        head.next = head;
        //然后递归，查看下一个节点
        return detectCycle(nextNode);
    }

    /**
     * 快慢指针法
     * @param head
     * @return
     */
    public ListNode detectCycleTwoPoints(ListNode head) {
        ListNode fast = head, slow = head;
        do {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            //第一次相遇时，fast 和 slow 指针分别走了2n，n个环的长度
        } while (fast != slow);
        //走到链表环入口节点时的步数方程为 a + n个环的长度
        //此时令fast指向头结点，和slow同时同速度再走a步 能再次相遇，相遇点为环入口节点
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;

    }

    public static void main(String[] args) {

    }
}
