package 剑指offer;

import java.util.HashMap;
import java.util.Map;

/*
剑指 Offer 07. 重建二叉树
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class lcof48 {
    public static void main(String[] args){
        System.out.println("Hello world");

        String s = "abcabcbb";
        Solution48 test = new Solution48();
        System.out.println(test.lengthOfLongestSubstring(s));
    }
}

/*
动态规划，dp[i]表示以s[j]结尾的最长不重复字串长度
状态转移：设s[i]为等于s[j]的最近的元素，则：
    i<0：表示s[j]左侧无与其相等的元素：dp[j] = dp[j-1]+1
    若dp[j-1]<j-i，意为s[i]在dp[j-1]表示的最长字串的左边（即dp[j-1]的子串中没有s[i]，那么加入dp[j]不会重复），则dp[j] = dp[j-1]+1
    同样的，若dp[j-1] >= j-i，即s[i]在dp[j-1]表示的子串中，那么加入s[j]会与dp[j-1]的子串中重复，那么dp[j] = j - i
    合并i<0，dp[j-1]<=j<j-i，合并入dp[j-1]<j-i的情况
边界条件：因为dp[0] = 1，因此定义dp[-1]=0为边界，这样可以从dp[0]开始递推
使用一个map存储s[i]~i，最后返回max(dp[])
 */
class Solution48{
    public int lengthOfLongestSubstring(String s){
        // 存储s[i]的映射表
        Map<Character, Integer> map = new HashMap<>();
        int res = 0, lastDp = 0;
        for(int j = 0; j < s.length(); j++){
            // 找s[i]，使用HashMap::getOrDefault()使若没找到返回的i小于0，对应第一种情况
            int i = map.getOrDefault(s.charAt(j), -1);
            // 添加当前访问元素到map中
            map.put(s.charAt(j), j);
            // 状态转移
            lastDp = (lastDp < j-i)
                    ? lastDp + 1
                    : j - i;
            // 更新最大值
            if(lastDp > res)    res = lastDp;
        }

        return res;
    }
}
