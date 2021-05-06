package 剑指offer;

/*
剑指 Offer 43. 1～n 整数中 1 出现的次数
输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 */
public class lcof43 {
    public static void main(String[] args){
        Solution43 counter = new Solution43();
        counter.countDigitOne(12);
    }
}

/*
找规律
定义当前位、高位、低位、指数位。如2301，若当前位为0，则高位为23，低位为1，指数位为10^2=100
对当前位的数字进行讨论：
    若大于1，则高位所有组合都能在当前位配1，则当前位的1次数为（高位+1）*指数位
    若等于1，则高位除最大的一个组合都能配1，最大点一个组合能配几个1要看低位。次数为高位*指数位+低位+1
    若小于1，则高位除最大的一个组合都能配1。次数为高位*指数位
将n的所有位的1出现的次数加在一起就是1~n中1出现的次数
 */
class Solution43 {
    /**
     * 当前位从个位开始向最高位迭代。当当前位为0且最高位也为0时说明当前位已经越过最高位
     * @param n 最大整数
     * @return 1的个数
     */
    public int countDigitOne(int n) {
        int data = n;
        int cur = data % 10;
        int high = data / 10;
        int low = 0;
        int digit = 1;

        int total = 0;
        while(cur != 0 || high != 0){
            switch (cur){
                case 0:
                    total += high * digit;
                    break;
                case 1:
                    total += high * digit + low + 1;
                    break;
                default:
                    total += (high + 1) * digit;
                    break;
            }

            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }

        return total;
    }
}
