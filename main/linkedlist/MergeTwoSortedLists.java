package main.linkedlist;

/**
 * @author 15304
 */
public class MergeTwoSortedLists {

    /**
     * 递归法 时间空间复杂度都是O(m+n)
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        else if (list2 == null) {
            return list1;
        }
        else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 迭代法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwo(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        p.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

    public static void main(String[] args) {}
}
