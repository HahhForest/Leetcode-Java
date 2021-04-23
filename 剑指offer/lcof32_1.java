package 剑指offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/*
剑指 Offer 32 - I. 从上到下打印二叉树
从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
 */
public class lcof32_1 {
}

/*
用一个队列模拟，元素出队时访问值，并将左右子放入队列中
先用一个Vector存放，并计数，再放入数组中
 */
class Solution32_1 {
    public int[] levelOrder(TreeNode root) {
        // 空树
        if(root == null){
            int[] res = new int[0];
            return res;
        }

        // 临时存放的可变数组，统计元素个数为后面建立定长数组做准备
        Vector<Integer> result = new Vector<>();
        int total = 0;

        // 开始访问
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode tmp;
        while(!queue.isEmpty()){
            // 访问
            tmp = queue.poll();
            result.add(tmp.val);
            total++;
            // 左右子树入队
            if(tmp.left != null)    queue.add(tmp.left);
            if(tmp.right != null)    queue.add(tmp.right);
        }

        // 将答案倒入定长数组
        int[] res = new int[total];
        for(int i = 0; i < total; i++){
            res[i] = result.elementAt(i);
        }

        return res;
    }
}