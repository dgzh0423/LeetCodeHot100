package main.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author 15304
 */
public class MedianFinder{
    Queue<Integer> A, B;
    public MedianFinder() {
        // 小顶堆，保存较大的一半，
        A = new PriorityQueue<>();
        // 大顶堆，保存较小的一半
        B = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        // 当前 N 为奇数，此时再 add 一个 num，应加到 B 中
        // 实现方法：将新元素 num 先插入至 A ，再将 A 堆顶元素（即 A 中最小的元素）插入至 B
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        }
        // 当前 N 为偶数，此时再 add 一个 num，应加到 A 中
        // 实现方法：将新元素 num 先插入至 B ，再将 B 堆顶元素（即 B 中最大的元素）插入至 A
        else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder   = new MedianFinder();
        medianFinder .addNum(1);
        medianFinder .addNum(2);
        System.out.println("当前中位数： " + medianFinder .findMedian());
        medianFinder .addNum(3);
        System.out.println("当前中位数： " + medianFinder .findMedian());
    }
}
