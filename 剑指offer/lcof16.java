package 剑指offer;

/*
剑指 Offer 16. 数值的整数次方
实现函数double Power(double base, int exponent)，求base的exponent次方
不得使用库函数，同时不需要考虑大数问题
 */
public class lcof16 {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}

/*
快速幂算法
将n转换为2的次幂的和
那么x^n就转换为x的2的次幂的次方的乘积
算法本质是二分法，因此时间复杂度为O(logn)
因此循环主体中，若n&1=1（即n为奇数），将结果乘x，然后x自方，n右移
例如n=11=1011b，则x^n=x^(1*2^0) * x^(1*2^1) * x^(0*2^2) * x^(1*2^3)
那么从低到高，每次将下自方，若n这一位为1，则x乘入结果，最后即为答案
需要注意Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，因此当 n=−2147483648 时执行 n=−n 会因越界而赋值出错
解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可。

详见https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
 */
class Solution16{
    public double myPow(double x, int n){
        if(x == 0)  return 0;
        long b = n;
        double result = 1.0;

        // 对于负指数转化为正指数运算
        if(b < 0){
            x = 1/x;
            b = -b;
        }

        // 开始迭代
        while(b > 0){
            if((b&1) == 1) result *= x; // 若二进制此位部位0才乘入
            // 滚雪球
            x *= x;
            b >>= 1;
        }

        return result;
    }
}
