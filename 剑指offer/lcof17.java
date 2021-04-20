package 剑指offer;

/*
剑指 Offer 17. 打印从1到最大的n位数
输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数
比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
用返回一个整数列表来代替打印,n 为正整数
 */
public class lcof17 {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}

/*
普通解法直接使用Math.pow(10, n)计算边界然后循环填入数组即可
考虑大数越界：使用String存储数
排列特点：只要高位的数更大，整个数就更大。因此使用递归可以保证保持顺序
 */
class Solution17 {
    int res[];  // 结果数组
    int num = 0;    // 计数，用来确定当前生成整数填入数组的位置

    public int[] printNumbers(int n) {
        // 注意答案数组的规模要刚刚好，少了会数组超限，多了会有默认的0
        res = new int[(int)Math.pow(10, n) - 1];

        // 循环位数，1到n
        for(int digit = 1; digit <= n; digit++){
            // 首位数字不能为0，因此用循环实现与其他位分开
            for(char first = '1'; first <= '9'; first++){
                char[] thisNum = new char[digit];   // 生成数的字符串
                thisNum[0] = first;
                dfs(1, thisNum, digit);
            }
        }

        return res;
    }

    void dfs(int index, char[] thisNum, int digit){
        // 递归终止：当前已将该数填完，即index=digit
        if(index == digit){
            // 该数已生成好，放入结果数组中
            res[num++] = Integer.parseInt(String.valueOf(thisNum));
            return;
        }

        // 数还未生成所有的位，递归继续生成
        for(char p = '0'; p <= '9'; p++){
            thisNum[index] = p;
            dfs(index+1, thisNum, digit);
        }
    }
}