package 剑指offer;

import java.util.List;
/*
剑指 Offer 06. 从尾到头打印链表
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
 */
public class Offer06 {
    public static void main(String[] args){
        System.out.println("HelloWorld!");
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        Solution06 testMachine = new Solution06();
        int[] ans = testMachine.reversePrint(head);
        System.out.println(ans[0]);
    }
}

/*
就地逆置，然后输出
其实也可以不用逆置，遍历第一遍得到元素个数，第二遍反向填充数组就行了
 */
class Solution06{
    public int[] reversePrint(ListNode head){
        // 辅助节点，放在第一个节点之前，便于逆置
        ListNode ass = new ListNode(-1);
        ass.next = head;

        // 开始逆置
        int size = 0;   // 得到元素个数
        ListNode p = head, next;
        while(p != null){
            next = p.next;
            p.next = ass.next;
            ass.next = p;
            p = next;
            size++;
        }

        // 输出结果
        p = ass.next;
        int[] ans = new int[size];
        for(int i = 0; i <= size-1; i++){
            ans[i] = p.val;
            p = p.next;
        }
        return ans;
    }
}
