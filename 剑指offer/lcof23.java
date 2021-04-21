package 剑指offer;
/*
剑指 Offer 24. 反转链表
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
 */
public class lcof23 {
}

/*
就地逆置，依次将节点插入到头节点前
 */
class Solution23 {
    public ListNode reverseList(ListNode head) {
        // 为了方便，加入一个不存储信息的头节点
        ListNode h = new ListNode(-1);
        h.next = null;
        
        // 开始逆置，依次将节点插入虚拟头节点和其后的节点间
        // 两个指针分别指向当前正在逆置的节点以及下一步逆置的节点（逆置时节点的子节点信息会丢失）
        ListNode p = head, q;
        while(p != null){
            /*
            错误写法：不要出现q.next这个表达，会出现null.null的错误。
            因为p始终不为null，应该基于p来生成q
            p.next = h.next;
            h.next = p;
            p = q;
            q = q.next;
             */
            q = p.next;
            // 逆置
            p.next = h.next;
            h.next = p;
            // 更新
            p = q;
        }

        return h.next;
    }
}
