package 剑指offer;

/*
剑指 Offer 12. 矩阵中的路径
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 */
public class lcof12 {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}

/*
回溯法，即带标记的递归
假设经过某个元素时，将其值置为已走过，递归完后恢复，可以节约O(mn)的访问数组空间
 */
class Solution12 {
    char[][] board;
    char[] aim;
    public boolean exist(char[][] board, String word) {
        char[] aim = word.toCharArray();    // 转为字符数组
        this.board = board;
        this.aim = aim;
        // 外层遍历地图，找到路径起点
        for(int i = 0; i <= board.length-1; i++){
            for(int j = 0; j <= board[0].length-1; j++){
                // 考虑起点
                if(dfs(i, j, 0))    return true;
            }
        }
        return false;
    }

    // 回溯主函数
    /*
    参数分别为地图所在位置行列索引、预期路径所在位置
     */
    boolean dfs(int m, int n, int p){
        // 地图走完了还没找到
        if(m<0 || m>board.length-1 || n<0 || n>board[0].length-1)   return false;
        // 不符合
        if(aim[p] != board[m][n]) return false;
        // 符合且这是最后一个，即找到路径
        if(p == aim.length - 1)   return true;
        // 符合但还没走完，则递归，先标记这个点走过，后面再改回来
        char tmp = board[m][n];
        board[m][n] = '\0';
        boolean next = dfs(m-1, n, p+1) ||
                dfs(m+1, n, p+1) ||
                dfs(m, n-1, p+1) ||
                dfs(m, n+1, p+1);
        board[m][n] = tmp;
        return next;
    }
}
