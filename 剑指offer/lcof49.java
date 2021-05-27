package 剑指offer;
/*
剑指 Offer 49. 丑数
我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数
 */
public class lcof49 {
    public static void main(String args[]){
        System.out.println("Hello world");
        Solution49_1 test = new Solution49_1();
        System.out.println(test.nthUglyNumber(10));
    }
}

/*
三指针法动规。主要思想：当前的丑数一定是由之前的某个丑数*2或*3或*5得来的，但是这三种情况顺序会交错
因此使用三个指针分别对应下一次计算时应该*2、*3、*5的数。下一个丑数一定从这三个数*2或*3或*5得来。由哪一个数得到，这个指针移向下一个丑数
定义ugly[n]为第n+1个丑数。初始化ugly[0] = 1，即第一个丑数为1。最后返回ugly[n-1]
 */
class Solution49_1{
    // 三个数最小值
    public int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }

    public int nthUglyNumber(int n){
        // 初始化与边界条件
        int[] ugly = new int[n];
        ugly[0] = 1;
        // 三个指针初始都指向ugly[0]，即表示当前ugly[0]为*2或*3或*5来构造下一个丑数的数
        int a = 0, b = 0, c = 0;
        int tmp2, tmp3, tmp5;
        for(int i = 1; i < n; i++){
            tmp2 = ugly[a] * 2;
            tmp3 = ugly[b] * 3;
            tmp5 = ugly[c] * 5;
            // 构造
            ugly[i] = min(tmp2, tmp3, tmp5);
            // 判断是哪个数构造出来的。若可以有多个构造路径，对应指针都要迭代，表示这种构造路径已经用过了
            if(ugly[i] == tmp2) a++;
            if(ugly[i] == tmp3) b++;
            if(ugly[i] == tmp5) c++;
        }

        return ugly[n-1];
    }
}

/*
解法二，使用小根堆实现找到最小的构造用的已经存在的丑数
 */
class Solution49_2{

}
