package 剑指offer;
/*
剑指 Offer 09. 用两个栈实现队列
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead
分别完成在队列尾部插入整数和在队列头部删除整数的功能
(若队列中没有元素，deleteHead操作返回 -1 )
 */

import java.util.Stack;

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
public class Offer09 {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}

/*
A：负责入队
B：用于倒序，从而删除栈A头部元素
入队：直接入到A中
出队：若B不为空，则B出栈
    若B为空，若A也为空，则说明队列为空，返回-1
    若B为空，但A不为空，则将A中所有元素全倒入B中，然后B出栈
 */
class CQueue {
    Stack<Integer> A, B;
    public CQueue() {
        this.A = new Stack<Integer>();
        this.B = new Stack<Integer>();
    }

    // 直接在A入栈
    public void appendTail(int value) {
        A.add(value);
    }

    // 分三种情况讨论
    public int deleteHead() {
        if(!B.isEmpty()){   // 若B非空
            return B.pop();
        }
        else{   // B为空
            if(A.isEmpty()){    // A也为空，返回-1
                return -1;
            }
            else{   // A不为空，将A中所有元素倒入B中，然后B出栈
                while(!A.isEmpty()){
                    B.add(A.pop());
                }
                return B.pop();
            }
        }
    }
}
