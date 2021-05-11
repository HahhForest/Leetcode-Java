package 剑指offer;

/*
剑指 Offer 45. 把数组排成最小的数
输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 */
public class lcof45 {
}

/*
算法：若拼接字符串ab > ba，则应该把a排在b后面（即定义新的排序方法）。对所有字符串按新规则排序后拼接即可
需要证明算法的正确性和排序规则的传递性。证明见 https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/
使用快速排序，拼接字符串的比较使用String.compareTo()，返回原字符串和参数字符串的字符依次比较的ascll码插值。因此若拼接后ab<ba，则ab.compareTo(ba)<0
 */
class Solution45{
    public String minNumber(int[] nums) {
        // 转为字符串数组，方便进行拼接比较大小
        String[] data = new String[nums.length];
        for(int i = 0;i < nums.length; i++){
            data[i] = String.valueOf(nums[i]);
        }

        return null;
    }
}