package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
请实现一个函数按照之字形顺序打印二叉树
即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 */
public class lcof32_3 {
}

/*
同lcof32，使用队列做层次遍历，使用层次遍历数组的元素个数就行判断方向
奇数层访问的数据从头到尾添加到答案中，偶数层从尾到头，但是下一层元素入队时必须都从左到右
LinkList::addLast()是在尾部加，即正序添加；LinkList::addFirst()相反，倒序添加
 */
class Solution32_3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        if(root != null){
            queue.add(root);
        }

        // 使用res的size来判断在奇偶
        // 使用双端队列
        while(!queue.isEmpty()){
            LinkedList<Integer> tmp = new LinkedList<>();
            int total = queue.size();
            for(int i = 0; i < total; i++) {
                TreeNode node = queue.poll();
                if((res.size() & 1) == 0){  // 表示已有偶数个元素，正在添加奇数层
                    tmp.addLast(node.val);
                }else{
                    tmp.addFirst(node.val);
                }

                // 注意下一层入队都要按照从左到右的顺序来
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)  queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}