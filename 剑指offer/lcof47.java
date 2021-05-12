package 剑指offer;

/*
剑指 Offer 47. 礼物的最大价值
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）
你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角
给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 */
public class lcof47 {
    public static void main(String[] args){
        System.out.println("Hello world!");
    }
}

/**
 * 经典动态规划
 * 状态转移：dp[i, j] = max(dp[i-1, j], dp[i, j-1]) + grid[i, j]
 * 边界条件：第一行和第一列直接累加
 */
class Solution47{
    public int max(int a, int b){
        return a > b ? a : b;
    }

    public int maxValue(int[][]grid){
        int m = grid.length; if(m == 0) return 0;
        int n = grid[0].length; if(n == 0) return 0;

        // 初始化
        int[][]dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++)  dp[i][0] = dp[i-1][0] + grid[i][0];
        for(int j = 1; j < n; j++)  dp[0][j] = dp[0][j-1] + grid[0][j];

        // 迭代
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }
}
