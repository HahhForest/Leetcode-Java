package 剑指offer;
/*
剑指 Offer 07. 重建二叉树
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */

import java.util.HashMap;

/*
经典重建树
通过【前序遍历列表】确定【根节点 (root)】
将【中序遍历列表】的节点分割成【左分支节点】和【右分支节点】
递归寻找【左分支节点】中的【根节点 (left child)】和 【右分支节点】中的【根节点 (right child)】
使用哈希表存储中序遍历位置与值的关系，提高速度
 */
class Solution07 {
    int[] preorder; // 方便递归函数调用
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();    // 存储中序遍历位置-值关系，提高查找速度

    // 主函数
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        // 存储中序遍历数组
        for(int i = 0; i <= inorder.length-1; i++){
            map.put(inorder[i], i);
        }
        // 使用递归建树
        return build(0, 0, inorder.length-1);
    }

    // 递归函数
    // 参数为前序遍历数组中根节点位置，中序遍历数组中左边界、右边界位置
    TreeNode build(int root, int left, int right){
        if(left > right){   // 递归终止条件
            return null;
        }

        TreeNode node = new TreeNode(preorder[root]);    // 前序数组确定根节点
        int i = map.get(preorder[root]);    // 确定根节点在中序数组的位置，划分为左右子树
        node.left = build(root+1, left, i-1);
        node.right = build(root + (i-left) + 1, i+1, right);    // 右子树根节点：父节点根节点位置+左子树规模+1
        return node;
    }
}