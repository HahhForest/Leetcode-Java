package 剑指offer;

/*
剑指 Offer 44. 数字序列中某一位的数字
数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等

请写一个函数，求任意第n位对应的数字
 */
public class lcof44 {
    public static void main(String[] args){
        System.out.println("Hello world!");
    }
}

/* 找规律
    一位数1~9共9个，起始为1，每个数字位数为1，共1*9*1 = 9位
    二位数10~99共90个，起始为10，每个数字位数为2，共2*9*10 = 180位
    三位数100~999共900个，起始为100，每个数字位数为3，共3 * 9*100 = 2700位
    因此思路分三步走：先找到是几位数，再找到是哪一个数字内部，最后看是这个数字内部第几位
    */
class Solution44{
    public int findNthDigit(int n){
        if(n == 0)  return 0;

        // 迭代
        int num = n;
        int digit = 1;  // 位数
        long start = 1;  // 起始数字
        long countThisDigit = digit * 9 * start;  // 总数位

        // 找是几位数。结束后digit指示位数，num指示是在这位数的第几个，从1开始
        while(num > countThisDigit){
            num -= countThisDigit;
            digit++;
            start *= 10;
            countThisDigit = digit * 9 * start;
        }

        // 找是在哪个数字里
        int thisNum = (int)(start + (num - 1) / digit);

        // 找是这个数字中的第几位数，从0开始
        int subTimes = (int)(digit - 1 - ((num-1) % digit));
        for(int i = 0; i < subTimes; i++)   thisNum /= 10;
        return thisNum % 10;
    }
}
