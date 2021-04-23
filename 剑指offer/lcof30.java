package 剑指offer;

/*
剑指 Offer 30. 包含min函数的栈
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)
 */

import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
public class lcof30 {
}

/*
使用一个辅助栈，使找最小元素的复杂度降到O(1)
在辅助栈B中，存储非严格降序的数字，即若新入栈的元素小于等于B的栈顶，才在B中入栈。出栈时维持B和A同步
 */
class MinStack30 {
    Stack<Integer> A, B;

    /** initialize your data structure here. */
    public MinStack30() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.add(x);
        if(B.isEmpty() || B.peek() >= x){
            B.add(x);
        }
    }

    public void pop() {
        if(A.peek() == B.peek()){
            B.pop();
        }
        A.pop();
    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }
}