package 剑指offer;
/*
剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分
 */

public class lcof21 {
}

/*
前后双指针交换
 */
class Solution21 {

    // 返回数字是否是奇数，使用位运算
    public boolean toNumType(int num){
        return ((num & 1) == 1);
    }

    // 前后双指针交换
    public int[] exchange(int[] nums) {
        int length = nums.length;
        int p = 0; int q = length-1;

        while(p < q){
            // p、q移位至p为偶数，q为奇数
            while(toNumType(nums[p]) && p <= length-2){
                p++;
            }
            while(!toNumType(nums[q]) && q >= 1){
                q--;
            }

            // 提前结束
            if(p >= q){
                break;
            }

            // 交换
            int tmp = nums[p];
            nums[p++] = nums[q];
            nums[q--] = tmp;
        }

        return nums;
    }
}
