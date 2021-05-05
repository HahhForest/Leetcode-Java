package 剑指offer;

import java.util.PriorityQueue;
import java.util.Queue;

/*
剑指 Offer 41. 数据流中的中位数
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值
如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值

例如，
[2,3,4] 的中位数是 3
[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：
void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
 */
public class lcof41 {
}

/*
思路：将数据流保存在一个列表中，并在添加元素时保持数组有序。使用堆优化时间复杂度

使用一个大顶堆存储从中位数到最小数，中位数为堆顶。一个小顶堆存储中位数到最大数，中位数为堆顶
规定小顶堆的尺寸大于等于大顶堆的尺寸。因此插入前比较两个堆的尺寸，若相等则插入小顶堆，否则插入大顶堆
插入第奇数个数时，先将其插入到大顶堆，再将大顶堆堆顶插到小顶堆。同理，插入第偶数个数时，先插入到小顶堆，再将小顶堆堆顶插入到大顶堆
 */
class MedianFinder {
    // 中到大为小顶堆，小到中为大顶堆
    Queue<Integer> mid2Max, min2Mid;

    /** initialize your data structure here. */
    public MedianFinder() {
        mid2Max = new PriorityQueue<>();    // 默认小顶堆
        min2Mid = new PriorityQueue<>((x, y) -> (y - x));   // 大顶堆重载比较规则
    }

    public void addNum(int num) {
        if(mid2Max.size() == min2Mid.size()){   // 插入小顶堆，先从大顶堆过一下
            min2Mid.add(num);
            mid2Max.add(min2Mid.poll());
        }else{
            mid2Max.add(num);
            min2Mid.add(mid2Max.poll());
        }
    }

    public double findMedian() {
        if(mid2Max.size() == 0) return 0;

        return mid2Max.size() == min2Mid.size() ? (mid2Max.peek() + min2Mid.peek()) / 2.0 : mid2Max.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */