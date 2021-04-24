package 剑指offer;

import java.util.Stack;

/*
剑指 Offer 33. 二叉搜索树的后序遍历序列
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 */
public class lcof33 {
}

/*
二叉搜索树，左小右大。判断是否满足规律即可。
递归法，根节点在最后一个。找到左子树和右子树的边界，递归判断即可。
 */
class Solution33 {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    // 内层递归函数，参数为树的边界
    public boolean recur(int[] postOrder, int left, int right){
        // 递归终止条件，此树节点数量《=1
        if(left >= right)   return true;

        // 找左子树右边界，即最后一个连续的小于根节点的元素
        int lr = left;
        while(postOrder[lr] < postOrder[right])
            lr++;
        lr--;   // 减去最后一次不满足while加上的1

        // 判断右子树是否合规：检查右子树中有没有小于根节点的数出现
        int rr = lr+1;
        // 若右子树没问题，退出while时应停在根节点上
        while(postOrder[rr] > postOrder[right])
            rr++;

        // 判断，第一是右子树没问题，然后再递归判断左右子树有没有问题
        return rr == right && recur(postOrder, left, lr) && recur(postOrder, lr+1, right-1);
    }
}

/*
非递归解法，使用栈，时间复杂度更低
将后序遍历序列倒过来，即为根右左的遍历序列，设此序列为a1,...,an，则：
    若ai+1>ai，则ai+1为ai的右子
    若ai+1<ai，则ai+1为其前面某个节点root的左子，因此其后的所有节点rx只可能为以下两种情况：
        1.rx为ri的左、右子树的各节点
        2.rx为root的父节点或更高层父节点的左子树的各节点
        在二叉搜索树中，以上节点都应小于root
此方法会在每个左子处都产生一个下降，因此找到他们的父节点，判断这个父节点作为根，其左右子是否满足左小右大
实现：沿后序遍历倒序序列依次压栈，遇到递减节点时，弹栈直到栈空（最后一个就是根）或出现栈顶小于递减节点（根是根的父节点的右子，因此根是最后一个大于递减节点的）
此时通过递减节点与此时的根的关系，来判断这个根是否合法
 */
class Solution33_2{
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        // 初始化为最大值
        int root = Integer.MAX_VALUE;
        // 查看后序遍历的倒叙序列
        for(int i = postorder.length - 1; i >= 0; i--) {
            // 若没出现递减节点，while不会执行，后面会一直压栈
            // 一旦出现递减节点，while会弹栈直到找到其父节点
            while(!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            // 判断此父节点是否合法
            if(root < postorder[i]) return false;
            // 默认压栈，即每个元素都会进栈
            stack.add(postorder[i]);
        }

        // 检查完没有问题，返回true
        return true;
    }
}
