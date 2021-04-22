package 剑指offer;

/*
剑指 Offer 28. 对称的二叉树
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class lcof28 {
}

/*
先利用lcof27镜像，再递归判断是否相同
此方法内存和时间都爆炸，应该在原树上递归判断
 */
class Solution28 {
    // 判断是否对称的外层函数
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        TreeNode afterSym = mirrorTree(root);
        return recurSym(afterSym, root);
    }

    // 判断是否堆成的内层递归
    public boolean recurSym(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null)  return true;
        if(root1 == null || root2 == null || root1.val != root2.val)    return false;
        return recurSym(root1.left, root2.left) && recurSym(root1.right, root2.right);
    }

    // 镜像翻转的外层函数
    public TreeNode mirrorTree(TreeNode root){
        // 镜像并复制
        TreeNode newRoot = new TreeNode(root.val);

        recurMirror(root, newRoot);
        return newRoot;
    }

    // 镜像翻转的内层递归
    public void recurMirror(TreeNode root, TreeNode newRoot){
        if(root == null)    return;

        TreeNode left = new TreeNode(-1);
        TreeNode right = new TreeNode(-1);

        if(root.right != null){
            left.val  = root.right.val;
        }else{
            left = null;
        }

        if(root.left != null){
            right.val  = root.left.val;
        }else{
            right = null;
        }

        newRoot.left = left;
        newRoot.right = right;

        recurMirror(root.left, newRoot.right);
        recurMirror(root.right, newRoot.left);
    }
}

/*
不新建树，在原树上递归判断是否对称
 */
class Solution28_2{
    // 外层函数
    public boolean isSymmetric(TreeNode root){
        // 空树直接对称
        if(root == null)    return true;

        return recur(root.left, root.right);
    }

    // 内层递归，判断树是否对称
    public boolean recur(TreeNode left, TreeNode right){
        // 两个空：退出递归，对称
        if(left == null && right == null)   return true;
        // 单个空：退出递归，不对称
        if(left == null || right == null)   return false;
        // 两个都不为空，但值不相等：不对称
        if(left.val != right.val)   return false;

        // 递归判断子树，注意对称判断，即左子树与右子树对比
        return recur(left.left, right.right) && recur(left.right, right.left);
    }
}
