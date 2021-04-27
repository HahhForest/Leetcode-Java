package 剑指offer;

/*
剑指 Offer 10- I. 斐波那契数列
写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）
答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1
 */
public class lcof10 {
    public static void main(String[] args){
        System.out.println("HelloWorld!");
    }
}

/*
尝试直接迭代求解
 */
class Solution10 {
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int preA = 0;
        int preB = 1;
        int ans = 0;
        for (int p = 2; p <= n; p++) {
            ans = (preA + preB)%1000000007;
            preA = preB;
            preB = ans;
        }
        return ans % 1000000007;
    }
}




