package 剑指offer;

import java.util.List;

/*
剑指 Offer 22. 链表中倒数第k个节点
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 */
public class lcof22 {
}

/*
先遍历出长度，再找到节点
 */
class Solution22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int length = 0;
        ListNode p = new ListNode(-1);
        p.next = head;

        // 找到长度
        while(p.next != null){
            p = p.next;
            ++length;
        }

        // 找到要求的节点，默认为输入合法
        p = head;
        for(int i = 0; i <= length-k-1; i++){
            p = p.next;
        }

        return p;
    }
}

// 双指针解法，第一个指针先走k-1步，然后两个指针一起跳直到第一个出界，则第二个指针指向所求节点
class Solution22_2{
    public ListNode getKthFromEnd(ListNode head, int k){
        ListNode first = head, second = head;

        for(int i = 1; i <= k-1; i++){
            first = first.next;
        }

        while(first.next != null){
            first = first.next;
            second = second.next;
        }

        return second;
    }
}