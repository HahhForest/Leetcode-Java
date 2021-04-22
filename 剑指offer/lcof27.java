package 剑指offer;

/*
剑指 Offer 27. 二叉树的镜像
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
 */
public class lcof27 {
    public static void main(String  args[]){
        System.out.println("Hello world!");
    }
}

/*
递归？
 */
class Solution27 {
    public TreeNode mirrorTree(TreeNode root) {
        recur(root);
        return root;
    }

    public void recur(TreeNode root){
        if(root == null)    return;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
    }
}
