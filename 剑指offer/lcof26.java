package 剑指offer;

/*
剑指 Offer 26. 树的子结构
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值
 */
public class lcof26 {
}

/*
使用递归
外层函数判断B是否是A的子结构，调用内层函数判断，若不是则递归到左子树和右子树
内层函数具体判断B是否为B对齐A的子结构
关键是理清楚逻辑，外层只判断是否包含，对于A的根节点位置不指定。内层具体对于确定了根节点的两棵树递归比较是否包含
 */
class Solution26 {
    // 外层递归，只判断B是否包含在A中，对于对齐的根节点不指定
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 1、若两棵树有null出现，肯定不是
        if(A == null || B == null){
            return false;
        }
        // 2、调用内层函数判断子结构是否是B对齐A，或者B是A的左子树、右子树的子结构
        return (recur(A, B) || (isSubStructure(A.left, B) || isSubStructure(A.right, B)));
    }

    // 内层递归，判断根节点对齐后，B是否包含在A中
    public boolean recur(TreeNode A, TreeNode B){
        // 因为判断B是否是A的子结构，因此若B空而A有，那在这一层是判断为真的
        if(B == null)   return true;
        // 若B不为空但A为空，则未匹配上
        if(A == null)   return false;
        // 剩下B、A都不为空，则判断值是否相等，以及递归判断左右子树
        return (A.val == B.val && (recur(A.left, B.left) && recur(A.right, B.right)));
    }
}
