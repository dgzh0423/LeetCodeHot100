package main.linkedlist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 15304
 */
public class CopyListWithRandomPointer {

    /**
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }

        Node p = head;
        //第一步，在每个原节点后面创建一个新节点   1->1'->2->2'->3->3'
        while (p != null){
            Node copyNode = new Node(p.val);
            copyNode.next = p.next;
            p.next = copyNode;
            p = copyNode.next;
        }

        p = head;
        //第二步，设置新节点的随机节点
        while (p != null){
            if (p.random != null){
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        p = head;
        Node dum = new Node(0);
        Node cur = dum;
        //第三步，将两个链表分离
        while (p != null){
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }

        //返回复制链表的头指针
        return dum.next;
    }

    /**
     * 借助HashMap
     * @param head
     * @return
     */
    public Node copyByHashMap(Node head){
        if(head == null) {
            return null;
        }

        // key是原节点， value是复制节点
        // 注意！map的key为自定义对象时，要重写hashCode和equals
        Map<Node,Node> map = new HashMap<>();
        Node p = head;
        //将原节点和新节点放入哈希表中
        while(p != null) {
            Node newNode = new Node(p.val);
            map.put(p,newNode);
            p = p.next;
        }

        p = head;
        //遍历原链表，设置新节点的next和random
        while(p != null) {
            Node newNode = map.get(p);
            //p是原节点，map.get(p)是对应的新节点，p.next是原节点的下一个节点
            //map.get(p.next)是原节点下一个节点对应的新节点
            if(p.next != null) {
                newNode.next = map.get(p.next);
            }
            //p.random是原节点随机指向
            //map.get(p.random)是原节点随机指向  对应的新节点
            if(p.random != null) {
                newNode.random = map.get(p.random);
            }
            p = p.next;
        }
        //返回头结点，即原节点对应的value(新节点)
        return map.get(head);
    }


    public static void main(String[] args) {

    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(val);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        result = 31 * result + (random != null ? random.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        //Object 的 equals 方法是比较的对象的内存地址
        return val == node.val && (Objects.equals(next, node.next)) && (Objects.equals(random, node.random));
    }
}