package 剑指offer;

import java.math.BigInteger;
import java.util.Arrays;

/*
剑指 Offer 14- II. 剪绳子 II
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1）
每段绳子的长度记为 k[0],k[1]...k[m - 1] ,请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
public class lcof14_2 {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}

/*
剪绳子II，代码逻辑和I一样，只是把求最大值和求模写成大数的运算
使用java.math.BigInteger，其底层使用int[]来表示整数，使用其提供的方法来实现运算符
使用Arrays.fill()对数组进行填充
 */
class Solution14_2 {
    public int cuttingRope(int n) {
        if(n == 2)  return 1;
        BigInteger dp[] = new BigInteger[n+1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        for(int i = 3; i <= n; i++){
            for(int j = 1; j <= i-1; j++){
                // BigInteger.max()用于比较大小，若参数大于实例，则更新实例为参数的值
                dp[i] = dp[i].max(BigInteger.valueOf(j * (i - j))).max(dp[i - j].multiply(BigInteger.valueOf(j)));
            }
        }
        return  dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }
}
