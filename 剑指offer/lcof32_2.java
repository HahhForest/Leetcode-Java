package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
剑指 Offer 32 - II. 从上到下打印二叉树 II
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
 */
public class lcof32_2 {
}

/*
类似32_1，只是把一层的打印到同一个数组中，还是使用队列BFS
 */
class Solution32_2 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        // 这样判定空树更优雅
        if(root != null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            // 本层的打印数组
            List<Integer> tmp = new ArrayList<>();
            // 本层的访问数量
            int total = queue.size();
            // 访问一层
            for(int i = 0; i < total; i++){
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null)   queue.add(node.left);
                if(node.right != null)   queue.add(node.right);
            }
            // 将本层加入最后的总数组中
            res.add(tmp);
        }

        return res;
    }
}