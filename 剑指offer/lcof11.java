package 剑指offer;

/*
剑指 Offer 11. 旋转数组的最小数字
把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转
输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素
例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1
 */
public class Offer11 {
    public static void main(String[] args){
        System.out.println("Hello World!");
    }
}

/*
使用二分法
由旋转点即原排序数组第一个最小值点可以将数组分为左排序数组和右排序数组
使用二分法，比较中点和右边界点的大小，确定旋转点在哪个区间
 */
class Solution11 {
    public int minArray(int[] numbers) {
        int i = 0, j = numbers.length - 1, m;
        while(i < j){
            m = (i + j) / 2;    // 划分点
            if(numbers[m] < numbers[j]){    // 若小于右边界，划分点在右排序数组，则旋转点在划分点左侧
                j = m;
            }
            else if(numbers[m] > numbers[j]){   // 若大于右边界，划分点在左排序数组，则旋转点在划分点右侧
                i = m + 1;
            }
            else{   // 若等于右边界，无法判断，但一定有[i, m]或[m, j]区间内所有元素相等，直接线性探测
                int min = numbers[i];
                for(int p = i+1; p <= j; p++){
                    if(numbers[p] <  min){
                        min = numbers[p];
                    }
                }
                return min;
            }
        }
        return numbers[i];
    }
}
