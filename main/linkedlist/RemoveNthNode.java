package main.linkedlist;

/**
 * @author 15304
 */
public class RemoveNthNode {

    /**
     * 快慢指针法
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //初始化： 伪头节点 dum ，快慢指针fast，slow指向dum,
        ListNode dum = new ListNode(0);
        dum.next = head;
        ListNode fast = dum, slow = dum;

        //fast先移动n步
        while (n > 0){
            fast = fast.next;
            n--;
        }
        //fast和slow继续同时移动
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //此时slow指向要删除节点的前一个节点
        slow.next = slow.next.next;
        slow.next.next = null;
        //返回真正的头结点
        return dum.next;

    }
    public static void main(String[] args) {

    }
}
