package 剑指offer;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/*
剑指 Offer 37. 序列化二叉树
请实现两个函数，分别用来序列化和反序列化二叉树
即二叉树与层次遍历序列的互相转换
 */
public class lcof37 {
    public static void main(String args[]){
        String str = "abcd";
        char[] test = {'a', 'b', 'l', 'c'};
        test[2] = 'd';
        System.out.println(test);
    }
}

/*
序列化：层次遍历即可
反序列化：利用层次遍历中根节点与左右节点的下标关系，也可类似层次遍历使用队列来顺序访问序列
 */
class Codec {

    // Encodes a tree to a single string.
    // 使用队列进行层次遍历
    public String serialize(TreeNode root) {
        if(root == null)    return "[]";

        // 使用StringBuilder，对象能够被多次的修改，并且不产生新的未使用对象
        StringBuilder ser = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // 由于序列化空节点也要输出，因此和一般的遍历有些不同
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            if(tmp != null){
                ser.append(tmp.val).append(',');
                queue.add(tmp.left);
                queue.add(tmp.right);
            }else{
                ser.append("null,");
            }
        }

        ser.deleteCharAt(ser.length()-1);
        ser.append(']');
        return ser.toString();
    }

    // Decodes your encoded data to tree.
    // 使用类似层次遍历的方法，使用队列层次遍历，使序列字符串按顺序用于构造
    public TreeNode deserialize(String data) {
        if(data.equals("[]"))   return null;

        // 掐头去尾，用逗号分割为字符串数组
        // substring()参数为左闭右开
        String[] level = data.substring(1, data.length()-1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();

        // 开始遍历
        // 使用valueOf()则装箱冗余，事实上我们只需要int而不是Integer
        TreeNode root = new TreeNode(Integer.parseInt(level[0]));
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()){
            TreeNode tmp = queue.poll();

            if(!level[i].equals("null")){   // 不为空则建立左子
                tmp.left = new TreeNode(Integer.parseInt(level[i]));
                queue.add(tmp.left);
            }
            i++;

            if(!level[i].equals("null")){   // 不为空则建立右子
                tmp.right = new TreeNode(Integer.parseInt(level[i]));
                queue.add(tmp.right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
