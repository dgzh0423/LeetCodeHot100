package main.linkedlist;

/**
 * 61.旋转链表
 * @author 15304
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null){
            return head;
        }
        // iter 记录原链表最后一个节点
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        // 当 k 是链表长度的整数倍时(k % n = 0)，相当于没有右移
        if (add == n) {
            return head;
        }
        // 将链表首尾相连，形成环
        iter.next = head;
        // 从旧尾节点走到新尾节点需要走 n - k % n 步
        while (add-- > 0) {
            iter = iter.next;
        }
        // 此时 iter 记录的是右旋后的新尾节点
        // 断开环，返回新头节点
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

}
