package 剑指offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
剑指 Offer 34. 二叉树中和为某一值的路径
输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
注意题意中的路径定义是从根节点到叶节点。因此必须和满足且终点为叶节点才表示找到
 */
public class lcof34 {
}

/*
动态规划？×
回溯法，一个path存储临时路径，探测某一路径时将沿路节点加入，回溯时要删除当前探测的节点
一个res存储答案，仅当达到目标时，复制一份path加入res
使用递归探测左右子树
 */
class Solution34 {
    public LinkedList<List<Integer>> res = new LinkedList<>();
    public LinkedList<Integer> path = new LinkedList<>();

    // 外层函数
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    // 内层递归，表示以root结束，目标为goal
    public void recur(TreeNode root, int goal){
        // 递归终点：root为空，表示此条路径找完了
        if(root == null)    return;

        // 探测当前节点
        goal -= root.val;
        path.add(root.val);
        // 检测当前是否是终点且满足条件
        if(goal == 0 && root.left == null && root.right == null){
            res.add(new LinkedList(path));
        }

        // 递归检测左右子树
        recur(root.left, goal);
        recur(root.right, goal);
        // 回溯：最后要删除当前探测的节点
        path.removeLast();
    }
}