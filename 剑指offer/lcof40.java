package 剑指offer;

import java.util.Arrays;

/*
剑指 Offer 40. 最小的k个数
输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4
 */
public class lcof40 {
}

/*
本题最朴素的方法为使用各种排序方法排序，然后取前7个
法一：快速排序思想。在每次划分后，判断当前划分点与k的关系，对应返回、分治左边、分治右边，不必得到完全排序的序列
 */
class Solution40_1 {
    int kk; // 要找的数的下标，如找前5小，则下标为5-1=4

    // 外层包装函数
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k == 0 || arr == null)   return new int[0];

        kk = k-1;
        return quickSearch(arr, 0, arr.length-1);
    }

    /**
     * 内层递归，由partition()得到划分后，进行判断并分治左右
     * @param arr：排序数组
     * @param left：左端点
     * @param right：右端点
     * @return：得到的前k小的数组
     */
    public int[] quickSearch(int[] arr, int left, int right){
        // 一次划分，得到划分点下标
        int par = partition(arr, left, right);

        // 判断，若par=k则得到答案，否则分情况划分左边或划分右边（不用完全排列）
        if(par == kk){
            return Arrays.copyOf(arr, par+1);
        }
        else if(par < kk){  // 在右边找合适的划分点
            return quickSearch(arr, par+1, right);
        }
        else{   // 在左边找合适的划分点
            return quickSearch(arr, left, par-1);
        }
    }

    /**
     * 划分函数，由划分值将数组排列成划分点左右区块有序
     * 这里的划分函数参照了题解里的写法，不是挖坑-填坑，而是左右成对交换
     * @param arr：排序数组
     * @param left：左端点
     * @param right：右端点
     * @return：返回划分点的索引
     */
    public int partition(int[] arr, int left, int right){
        // 基准数为第一个数
        int refer = arr[left];
        int p = left, q = right+1;
        while(true){
            // 交换排序，两个循环后p指向大于refer的，q指向小于refer的
            while(++p <= right && arr[p] <= refer);
            // 注意这里是arr[q]>refer，因为最后一次要把q和refer交换，因此q不能超出数组索引限制。因为refer是arr[0]，所以用>保证q不会<0
            while(--q >= left && arr[q] > refer);
            // 退出条件
            if(p > q)   break;
            // 否则，交换
            int tmp = arr[p];
            arr[p] = arr[q];
            arr[q] = tmp;
        }
        // 此时p > q, p指向大于refer的，q指向小于refer的，因为是左小右大，因此将refer和q的数交换
        arr[left] = arr[q];
        arr[q] = refer;

        return q;
    }
}