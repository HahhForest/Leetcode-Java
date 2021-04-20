package 剑指offer;

/*
剑指 Offer 18. 删除链表的节点
给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。
 */
public class lcof18 {
}

/*
注意头节点直接指向第一个带数据的节点
 */
class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        // 头节点为特例
        if(head.val == val) return head.next;

        // 其他节点：双指针，一个指向要删的节点，另一个指向要删的节点的下一个节点
        ListNode prior = head, toDel = head.next;
        while(toDel != null && toDel.val != val){
            prior = toDel;
            toDel = toDel.next;
        }
        // 删除，此时toDel指向被删节点
        if(toDel.val == val){
            prior.next = toDel.next;
        }

        return head;
    }
}