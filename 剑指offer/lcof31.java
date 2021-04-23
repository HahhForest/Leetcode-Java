package 剑指offer;

import java.util.Stack;

/*
剑指 Offer 31. 栈的压入、弹出序列
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等
例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 */
public class lcof31 {
}

/*
使用一个栈模拟过程
沿弹出序列从头到尾模拟。若栈顶不等于当前弹出元素，入栈，若等于，出栈，弹出序列指针指向下一个元素。
最后用栈是否为空来判断是否能够得到弹出序列。
 */
class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 弹出序列指针
        int i = 0;
        // 模拟栈
        Stack<Integer> stack = new Stack<>();
        // 模拟压栈
        for(int num: pushed){
            stack.add(num);
            // 判断是否在这里出栈
            while(!stack.isEmpty() && popped[i] == stack.peek()){
                stack.pop();
                ++i;
            }
        }

        // 若弹出序列可行，最后一定是把栈弹空。不可行的话，退出for循环时栈还未弹空
        return stack.isEmpty();
    }
}
