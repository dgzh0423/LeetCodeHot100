package main.linkedlist;

/**
 * @author 15304
 */
public class AddTwoNumbers {
    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //初始化： 伪头节点 dum ，节点 cur 指向 dum, carry保存进位
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        int carry = 0;

        while(l1 != null || l2 != null) {
            //短的链表用0补
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        //如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return dum.next;
    }

    public static void main(String[] args) {

    }
}
