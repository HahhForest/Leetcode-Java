package 剑指offer;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
剑指 Offer 36. 二叉搜索树与双向链表
输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向
 */
public class lcof36 {
    public static void main(String args[]){
        System.out.println("Hello world!");

        // 测试样例
        Node36 t4 = new Node36(4);
        Node36 t2 = new Node36(2);
        Node36 t5 = new Node36(5);
        Node36 t1 = new Node36(1);
        Node36 t3 = new Node36(3);

        t4.left = t2;
        t4.right = t5;
        t2.left = t1;
        t2.right = t3;

        Solution36 test = new Solution36();
        test.treeToDoublyList(t4);

    }
}


// Definition for a Node.
class Node36 {
    public int val;
    public Node36 left;
    public Node36 right;

    public Node36() {}

    public Node36(int _val) {
        val = _val;
    }

    public Node36(int _val,Node36 _left,Node36 _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

/*
使用性质：二叉搜索树的中序遍历即为顺序序列
用一个哈希表保存节点的出栈次数，第一次访问建立键值对，第二次检测到键值对才访问值
一个节点只有当其右子进栈以后，其左右子树关系才可以被修改。因此应该修改
在此题中，一个指针指向当前倒数第二个访问值的节点，一个指针指向倒数第一个访问值的节点，将这两个节点的关系转化为双向列表
此方法由于不能修改节点数据结构，因此需要建立一个哈希表存储每个节点访问的次数，在树规模比较大时内存超限
 */
class Solution36 {
    public Node36 treeToDoublyList(Node36 root) {
        if(root == null)    return null;

        // 先找最小值，即双向序列的起点，树的最左边的节点
        Node36 p = root;
        Node36 head = new Node36();
        while(p != null){
            if(p.left != null)  break;
            p = p.right;
        }
        if(p == null){
            head.right = root;
        }else{
            p = p.left;
            while(p.left != null){
                p = p.left;
            }
            head.right = p;
        }

        // 根节点入栈
        Stack<Node36> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        stack.push(root);
        map.put(root.val, 0);
        // 循环
        Node36 pre=head.right, after=head.right;
        while(!stack.isEmpty()){
            Node36 tmp = stack.pop();

            if(map.get(tmp.val) == 0){  // 说明这是第一次出栈，不访问值，左树压栈
                // 根节点访问次数+1
                map.put(tmp.val, 1);
                stack.push(tmp);
                // 有左子则压栈
                if(tmp.left != null){
                    stack.push(tmp.left);
                    map.put(tmp.left.val, 0);
                }

            }else{  // 第二次出栈，即可以访问了
                after = tmp;
                // 若有右子，压栈
                if(tmp.right != null){
                    stack.push(tmp.right);
                    map.put(tmp.right.val, 0);
                }
            }

            // 判断是否连接
            if(pre != after){   // after有更新，要新建关系
                pre.right = after;
                after.left = pre;
                pre = after;
            }
        }

        // 头尾连接
        after.right = head.right;
        head.right.left = after;

        return head.right;
    }
}

/*
使用递归进行中序遍历，不需要额外空间
 */
class Solution36_2 {
    Node36 head=null, p=null;   // head指向第一个，p指向上一个访问的节点
    public Node36 treeToDoublyList(Node36 root) {
        if(root == null)    return null;
        recur(root);
        // 手动连接头尾
        head.left = p;  p.right = head;
        return head;
    }

    // 内层递归
    public void recur(Node36 root){
        // 递归终止条件
        if(root == null)    return;

        recur(root.left);
        // 还没有访问到最小的节点时，head和p都为null
        if(head != null){   // 即已经访问了最小的节点了，此时p指向上一个访问的节点
            p.right = root;
        }else{  // 即此时访问第一个节点
            head = root;
        }
        root.left = p;
        p = root;
        recur(root.right);
    }
}