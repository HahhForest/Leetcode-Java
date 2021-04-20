package 剑指offer;

/*
剑指 Offer 04. 二维数组中的查找
在一个 n * m 的二维数组中，
每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。
请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Offer04 {
    public static void main(String[] args){
        System.out.println("HelloWorld!");
    }
}

/*
先定位行，再定位列，错误，行可能跳过正确答案所在的行
 */
class WrongSolution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = 0, n = 0;

        boolean doMove = false;
        // 先定位可能在哪一行
        do{
            if(matrix[m][n] < target){
                ++m;
                doMove = true;
                System.out.println(m + '\n');
            }
            else{
                doMove = false;
            }
        }while(m <= matrix.length - 1 && doMove);

        // 再定位可能在哪一列
        doMove = false;
        do{
            if(matrix[m][n] < target){
                ++n;
                doMove = true;
                System.out.println(n + '\n');
            }
            else{
                doMove = false;
            }
        }while(n <= matrix[0].length - 1 && doMove);

        // 可能的位置已经唯一确定，判断是否找到
        return (matrix[m][n] == target);
    }
}

/*
正确解答：每一步同时考虑行和列要不要跳
从左下角开始，可以分为大于、小于、等于目标值三种情况，分别对应向上、向右、找到
从左上角开始则无法实现
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
       int m = matrix.length - 1, n = 0;
       // 线性探测
       while(m >= 0 && n <= matrix[0].length-1){
           if(matrix[m][n] > target){
               --m;
           }
           else if(matrix[m][n] < target){
               ++n;
           }
           else return true;
       }
        // 没找到
        return false;
    }
}