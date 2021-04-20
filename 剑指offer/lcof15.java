package 剑指offer;

/*
剑指 Offer 15. 二进制中1的个数
请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数
例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2
 */
public class lcof15 {
    public static void main(String[] args){
        System.out.println("Hello World!");
        Solution15_1 test = new Solution15_1();
        test.hammingWeight(15);
    }
}

/*
直观解法，转为二进制字符数组，然后遍历计数
太慢
 */
class Solution15_1{
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        // 转为字符数组
        String biStr = Integer.toBinaryString(n);
        char[] biCharArr = biStr.toCharArray();

        // 计数
        int total = 0;
        for(char c : biCharArr){
            if(c == '1')    total++;
        }

        return total;
    }
}

/*
与运算的特点：n&1==0，则n最右边一位是0，否则为1
因此可以使用移位操作和与运算，依次判断
题目要求看作无符号数，因此使用无符号右移>>>
 */
class Solution15_2 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int total = 0;
        while(n != 0){
            total += (n&1);
            n >>>= 1;
        }
        return total;
    }
}

/*
巧用 n&(n-1)
n-1会把n最右边的1置换为0，然后右边所有0换为1
因此n&(n-1)会把最右边的1消去，即减少一个1的数量
因此只要计数需要多少次运算原数变为0即可
 */
class Solution15_3 {
    int hammingWeight(int n) {
        int total = 0;
        while(n != 0){
            n = n & (n-1);
            total += 1;
        }
        return total;
    }
}
