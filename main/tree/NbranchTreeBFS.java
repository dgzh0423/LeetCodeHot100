package main.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 429.N叉树的层序遍历
 * @author 15304
 */
public class NbranchTreeBFS {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }
        // List.of() 方法是在 Java 9 中引入的，它提供了一种简洁的方式来创建不可变列表（immutable list）。
        // 这个方法可以接受零个或多个参数，并返回一个固定大小的列表，其中包含传入的元素。
        // List<Node> cur = List.of(root);
        List<Node> cur = new ArrayList<>();
        cur.add(root);
        while (!cur.isEmpty()) {
            // 用于保存下一层的节点
            List<Node> next = new ArrayList<>();
            // 保存当前层的节点val
            List<Integer> vals = new ArrayList<>(cur.size());
            for (Node node : cur) {
                vals.add(node.val);
                next.addAll(node.children);
            }
            ans.add(vals);
            cur = next;
        }
        return ans;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.children = new ArrayList<>();
        Node node3 = new Node(3);
        root.children.add(node3);
        root.children.add(new Node(2));
        root.children.add(new Node(4));
        node3.children = new ArrayList<>();
        node3.children.add(new Node(5));
        node3.children.add(new Node(6));
        System.out.println(new NbranchTreeBFS().levelOrder(root));
    }
}
