package main.stack;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 15304
 */
public class LargestRectangle {

    public int largestRectangleArea(int[] heights) {
        // 哨兵：在柱体数组的头和尾加了两个高度为 0 的柱体
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);

        //stack保存柱体的下标
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 若当前柱体 i 的高度小于栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」
            // 因此以栈顶柱体为高的矩形的左右宽度边界就确定了，可以计算面积
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                int height = tmp[stack.pop()];
                int width = i - stack.peek() - 1;
                area = Math.max(area, width * height);
            }
            // addFirst()
            stack.push(i);
        }
        //遍历完成后stack里只剩下两个哨兵的下标
        return area;
    }

    public static void main(String[] args) {
        LargestRectangle lr = new LargestRectangle();
        int [] heights = {2,1,5,6,2,3};
        System.out.println(lr.largestRectangleArea(heights));
    }
}
