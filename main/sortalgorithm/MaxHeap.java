package main.sortalgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 堆是一种完全二叉树
 * @author 15304
 */
public class MaxHeap {
    // 使用列表而非数组，这样无须考虑扩容问题
    private List<Integer> maxHeap;

    /**
     * 构造方法，根据输入列表建堆
     */
    public MaxHeap(List<Integer> nums) {
        // 将列表元素原封不动添加进堆
        maxHeap = new ArrayList<>(nums);
        // 堆化除叶节点以外的其他所有节点，因为叶子节点没有子节点，本身就是子堆
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 堆排序：先建堆，然后每次输出堆顶元素
     */
    List<Integer> heapSort(MaxHeap maxHeap){
        List<Integer> sortedNums = new ArrayList<>();
        int size = maxHeap.size();
        for (int i = 0; i < size; i++) {
            sortedNums.add(pop());
        }
        return sortedNums;
    }

    /* 获取左子节点的索引 */
    private int left(int i) {
        return 2 * i + 1;
    }

    /* 获取右子节点的索引 */
    private int right(int i) {
        return 2 * i + 2;
    }

    /* 获取父节点的索引 */
    private int parent(int i) {
        // 向下整除
        return (i - 1) / 2;
    }

    /* 交换元素 */
    private void swap(int i, int j) {
        int tmp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, tmp);
    }

    /* 获取堆大小 */
    public int size() {
        return maxHeap.size();
    }

    /* 判断堆是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* 访问堆顶元素 */
    public int peek() {
        return maxHeap.get(0);
    }

    /* 元素入堆 */
    public void push(int val) {
        // 添加节点
        maxHeap.add(val);
        // 从底至顶堆化
        siftUp(size() - 1);
    }

    /* 元素出堆 */
    public int pop() {
        // 判空处理
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 1. 交换堆顶节点与最右叶节点（交换首元素与尾元素）
        swap(0, size() - 1);
        // 2. 删除"堆顶节点"
        int val = maxHeap.remove(size() - 1);
        // 3. 从顶至底堆化
        siftDown(0);
        // 返回堆顶元素
        return val;
    }

    /* 从节点 i 开始，从底至顶堆化 */
    private void siftUp(int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = parent(i);
            // 当“越过根节点”或“节点无须上移”时，结束堆化
            if (p < 0 || maxHeap.get(i) <= maxHeap.get(p)) {
                break;
            }
            // 交换两节点
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    /* 从节点 i 开始，从顶至底堆化 */
    private void siftDown(int i) {
        while (true) {
            // 判断节点 i, l, r 中值最大的节点，记为 max
            int l = left(i), r = right(i), max = i;
            if (l < size() && maxHeap.get(l) > maxHeap.get(max)) {
                max = l;
            }
            if (r < size() && maxHeap.get(r) > maxHeap.get(max)) {
                max = r;
            }
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
            if (max == i) {
                break;
            }
            // 交换两节点的元素值
            swap(i, max);
            // 循环向下堆化
            i = max;
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(Arrays.asList(9, 8, 6, 6, 7, 5, 2, 1, 4, 3, 6, 2));
        System.out.println(maxHeap.heapSort(maxHeap));
        maxHeap.push(10);
        System.out.println(maxHeap.peek());
    }
}

