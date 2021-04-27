package 剑指offer;

/*
剑指 Offer 10- II. 青蛙跳台阶问题
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1
 */
public class lcof10_2 {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}

/*
设第n级台阶有f(n)种跳法
在最后一步，可以跳一步或者跳两步
因此f(n) = f(n-1)+f(n-2)，类似斐波那契数列，只是起始数字不同，f(0)=1，f(1)=1
使用动态规划，达到最优时间和空间
设dp[i]为f(i)
 */
class Solution10_02 {
    public int numWays(int n) {
        int a = 1, b = 1, f = 0;
        if(n == 0)  return 1;
        if(n == 1)  return 1;
        for(int i = 2; i <= n; i++){
            f = (a + b) % 1000000007;
            a = b;
            b = f;
        }
        return f;
    }
}
