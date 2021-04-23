package 剑指offer;

/*
剑指 Offer 13. 机器人的运动范围
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1]
一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子
例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class Offer13 {
    public static void main(String[] args){
        System.out.println("HelloWorld!");
    }
}

/*
尝试暴力解法
超时
 */
class Solution13 {
    public int movingCount(int m, int n, int k) {
        int ans = 0;
        for(int i = 0; i <= m-1; m++){
            for(int j = 0; j <= n-1; j++){
                char[] arr = String.valueOf(i).toCharArray();
                char[] col = String.valueOf(j).toCharArray();
                // 计算和
                int sumTest = 0;
                for(char p : arr)   sumTest += (int)p;
                for(char q : col)   sumTest += (int)q;
                // 判断
                if(sumTest <= k)    ++ans;
            }
        }
        return ans;
    }
}

/*
题目理解应该是找到路径，有些点符合限制但不可达
主体使用dfs，从(0,0)开始只考虑向右或向下走（由于数位和的性质，数位和相等的点在斜率为1的直线上，总体构成一个等腰三角形）
计数位和时，可以用递推关系: s_x = (x + 1) % 10 != 0 ? s_x + 1 : s_x - 8
 */
class Solution13_2 {
    int m;
    int n;
    int k;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.visited = new boolean[m][n];
        // 深度优先遍历，递归
        return dfs(0, 0, 0, 0);
    }

    // 递归主体
    public int dfs(int row, int col, int rowSum, int colSum){
        if(row>=m || col>=n)    return 0;
        if(rowSum + colSum > this.k)    return 0;
        if(visited[row][col])  return 0;
        // 递归
        this.visited[row][col] = true;
        return (1
                + dfs(row+1, col, (row + 1) % 10 == 0 ? rowSum-8 : rowSum+1, colSum)
                + dfs(row, col+1, rowSum, (col + 1) % 10 == 0 ? colSum-8 : colSum+1));
    }
}
