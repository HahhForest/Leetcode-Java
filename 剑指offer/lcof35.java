package 剑指offer;

import java.util.HashMap;

/*
剑指 Offer 35. 复杂链表的复制
请实现 copyRandomList 函数，复制一个复杂链表
在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null
 */
public class lcof35 {
}

/*
难点在于构建引用指向
使用哈希表，存储旧-新键值对
 */
class Solution35 {
    public Node copyRandomList(Node head) {
        if(head == null)    return null;

        // 第一次遍历，建立新节点，建立键值对表
        HashMap<Node, Node> map = new HashMap<>();
        Node current = head;
        while(current != null){
            map.put(current, new Node(current.val));
            current = current.next;
        }

        // 第二次遍历，加入指向关系
        current = head;
        while (current != null){
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);
            current = current.next;
        }

        return map.get(head);
    }
}

/*
哈希表费空间，可以使用拼接拆分的方法，构建旧-新-旧-新的拼接表，建立关系后再拆分出新表
共三次遍历：构建拼接表、构建关系、拆分出新表
 */
class Solution35_2 {
    public Node copyRandomList(Node head) {
        if(head == null)    return null;

        // 第一次遍历，构建拼接表
        Node current = head;
        while(current != null){
            Node tmp = new Node(current.val);
            tmp.next = current.next;
            current.next = tmp;
            current = tmp.next;
        }

        // 第二次遍历，构建random关系
        current = head;
        while(current != null){
            if(current.random != null){
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // 第三次遍历，拆分出新表，同时不改变旧表
        // 注意循环终止条件，旧表节点的next永远不可能为null，但新表节点的next是可以为null的，因此要用新表节点的next来做判断，避免出现null.next
        // 因此最后一对要循环外单独拆开
        current = head;
        Node newRoot = head.next;
        Node copy = newRoot;
        while(copy.next != null){
            current.next = current.next.next;
            copy.next = copy.next.next;
            current = current.next;
            copy = copy.next;
        }
        // 单独拆开最后一对
        current.next = null;

        return newRoot;
    }
}