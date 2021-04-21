package 剑指offer;

/*
剑指 Offer 25. 合并两个排序的链表
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
 */
public class lcof25 {
}

/*
双指针
 */
class Solution25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode h = new ListNode(-1);
        ListNode now = h;

        // 双边前进
        while(p != null && q != null){
            if(p.val <= q.val){
                now.next = p;
                p = p.next;
            }else{
                now.next = q;
                q = q.next;
            }
            now = now.next;
        }

        // 单边收尾
        if(p == null){
            while(q != null){
                now.next = q;
                now = now.next;
                q = q.next;
            }
        }else{
            while(p != null){
                now.next = p;
                now = now.next;
                p = p.next;
            }
        }

        return h.next;
    }
}
