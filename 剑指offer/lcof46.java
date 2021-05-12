package 剑指offer;

/*
剑指 Offer 46. 把数字翻译成字符串
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”
一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class lcof46 {
    public static void main(String[] args){
        System.out.println("Hello world!");
    }
}

/*
动态规划
定义dp[n]为a1a2..an-1an的翻译总个数，则状态转移如下：
    若a_n-1a_n可以被翻译（如23），则dp[n] = dp[n-2] + dp[n-1]
    若a_n-1a_n不可被翻译（如53），则dp[n] = dp[n-1]
边界条件：dp[0] = dp[1] = 1: 定义dp[0]是为了可被翻译的dp[2] = dp[0]+dp[1]能够被正确推出
1253
 */
class Solution46{
    public int translateNum(int num){
        String numStr = String.valueOf(num);
        int prepre = 1, pre = 1;
        for(int i = 1; i < numStr.length(); i++){
            String tail = numStr.substring(i-1, i+1);
            int cur = tail.compareTo("10") >= 0 && tail.compareTo("25") <= 0 ? prepre + pre : pre;
            prepre = pre; pre = cur;
        }

        return pre;
    }
}