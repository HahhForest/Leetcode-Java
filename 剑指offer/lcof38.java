package 剑指offer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
剑指 Offer 38. 字符串的排列
输入一个字符串，打印出该字符串中字符的所有排列
你可以以任意顺序返回这个字符串数组，但里面不能有重复元素
 */
public class lcof38 {
}

/*
递归、回溯法、剪枝
看作是一个树，使用递归处理每颗子树，使用回溯法节省空间，使用剪枝排除冗余情况
回溯：在同一个字符数组上操作，使用交换进行各种排列，排列完后恢复
剪枝：每一个字符在某一层只能出现一次
数据结构：使用List存储方便添加，最后使用List.toArrayList(T[] a)转换为String[]
 */
class Solution38 {
    List<String> res = new LinkedList<>();
    // 在这个字符数组上操作出所有的排列
    char[] switchOn;

    public String[] permutation(String s) {
        switchOn = s.toCharArray();
        dfs(0);
        // 使用有预设类型和长度的转换，否则转换为Object[]，而Object[]无法直接转换为String[]
        return res.toArray(new String[res.size()]);
    }

    // 递归函数，即在树的第几层进行排列（即操作字符数组的第几位）
    public void dfs(int level){
        // 递归退出条件：最后一位只有一种选择，因此到最后一位时把当前排列出的switchOn加入答案中
        if(level == switchOn.length - 1){
            // 使用valueOf()更安全，自带判空
            // toString()为Object的方法，需要保证对象不为空
            res.add(String.valueOf(switchOn));
            return;
        }

        // 使用哈希表剪枝，防止在同一层出现相同的字符
        HashMap<Character, Integer> map = new HashMap<>();
        // dfs这一层，将所有还未固定的字符都放到leve层进行排列
        for(int i = level; i < switchOn.length; i++){
            // 剪枝
            if(map.containsKey(switchOn[i]))    continue;

            // 递归
            map.put(switchOn[i], 1);
            swap(i, level);
            dfs(level+1);
            // 回溯：恢复更改
            swap(i, level);
        }
    }

    // 交换，将from处的字符放到to位，至于使用交换的方式，反正后续所有位都会进行全排列，所以不用管
    void swap(int from, int to){
        char tmp = switchOn[from];
        switchOn[from] = switchOn[to];
        switchOn[to] = tmp;
    }
}