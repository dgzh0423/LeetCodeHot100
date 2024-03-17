package main.stack;

import java.util.Stack;

/**
 * @author 15304
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    // 注意 pop()， top()也就是peek()， getMin()要先判空
    public void pop() {
        if(stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }
    public int top() {
        return stack.peek();
    }
    /* 借助 minStack，栈顶来保存当前栈的最小值 */
    public int getMin() {
        return minStack.peek();
    }
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
