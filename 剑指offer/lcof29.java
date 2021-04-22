package 剑指offer;

/*
剑指 Offer 29. 顺时针打印矩阵
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
public class lcof29 {
}

/*
模拟行走，根据边界条件更新方向，根据方向的改变更新边界条件，使用矩阵的元素总个数控制程序的结束
 */
class Solution29 {
    public int[] spiralOrder(int[][] matrix) {
        // 空矩阵：返回空
        if(matrix.length == 0){
            int[] result = new int[0];
            return result;
        }

        // 矩阵的尺寸信息
        int height = matrix.length;
        int width = matrix[0].length;
        int size = width * height;

        // 初始化四个边界信息，分别是上、右、下、左
        int up = 0, right = width-1, down = height-1, left = 0;
        // 初始化起点
        int p = 0, q = 0;
        // 初始化行走方向指示符，1、2、3、4分别代表向右、下、左、上
        int indicator = 1;

        // 共打印size个数字
        int[] result = new int[size];
        for(int i = 0; i < size; i++){
            System.out.println(width);
            System.out.println(String.valueOf(p) + String.valueOf(q));
            result[i] = matrix[p][q];

            // 根据指示符更新访问地址
            switch(indicator){
                case 1:
                    ++q;
                    break;
                case 2:
                    ++p;
                    break;
                case 3:
                    --q;
                    break;
                case 4:
                    --p;
                    break;
            }

            // 根据边界条件更新指示符与边界条件
            if(q > right){  // 右边撞墙
                ++p; --q;   // 更正
                ++up;
                indicator = 2;
                continue;
            }
            if(p > down){   // 下面撞墙
                --p; --q;
                --right;
                indicator = 3;
                continue;
            }
            if(q < left){  // 左边撞墙
                --p; ++q;
                --down;
                indicator = 4;
                continue;
            }
            if(p < up){  // 上面撞墙
                ++p; ++q;
                ++left;
                indicator = 1;
            }
        }

        return result;
    }
}