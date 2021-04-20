package 剑指offer;

/*
剑指 Offer 14- I. 剪绳子
给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1]
请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
 */
public class lcof14 {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}

/*
动态规划
状态转移模板dp[i]表示长度为i的绳子剪断后的最大乘积
dp[i] = max(dp[i], max(j*dp[i-j], j*(i-j)))，外层max表示j从2到i-1遍历，取最大值
内层max表示从长度为i的绳子上截下长度为j的一段，剩下部分在截和不截中取最大值
 */
class Solution14 {
    public int cuttingRope(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){
            for(int j = 2; j <= i-1; j++){
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
