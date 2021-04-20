package 剑指offer;
/*
剑指 Offer 20. 表示数值的字符串
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）
例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是
 */

import java.util.HashMap;
import java.util.Map;

public class lcof20 {
    public static void main(String args[]){
        System.out.println("Hello World!");
    }
}

/*
使用有限状态自动机
定义出所有的状态，其中有一些是初始状态、接受状态
从初始状态开始，在不同的状态间转移。若最后停留在接受状态，则认为可以接受，否则认为不可接受
对于不存在的状态转移，定义一个特殊的拒绝状态，若转移进来，立即判定不接受
状态机图见：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/biao-shi-shu-zhi-de-zi-fu-chuan-by-leetcode-soluti/
 */
class Solution20 {
    // 共定义十个状态，需要注意的是，左侧无整数的小数点和有整数的小数点需要分开讨论
    enum State {
        STATE_INITIAL,  // 初始状态，起始的空格
        STATE_INT_SIGN, // 符号位
        STATE_INTEGER,  // 整数部分
        STATE_POINT,    // 左边有整数的小数点
        STATE_POINT_WITHOUT_INT,    // 左边没有整数的小数点
        STATE_FRACTION, // 小数部分
        STATE_EXP,  // 字符e
        STATE_EXP_SIGN, // 指数的符号
        STATE_EXP_NUMBER,   // 指数部分的数字
        STATE_END,  // 末尾的空格
    }

    // 定义六种接收到的字符类型（即状态间的转移路径），其中一种是非发输入
    enum CharType{
        CHAR_NUMBER, // 数字
        CHAR_EXP, // 字符e
        CHAR_POINT, // 小数点
        CHAR_SIGN, // +、-号
        CHAR_SPACE, // 空格
        CHAR_ILLEGAL, // 非法输入
    }

    // 将输入转换为字符类型（即转移路径）
    // 用switch会不会快一点？应该不会
    public CharType toCharType(char ch){
        if(ch >= '0' && ch <= '9'){
            return CharType.CHAR_NUMBER;
        }else if(ch == 'e' || ch == 'E'){
            return CharType.CHAR_EXP;
        }else if(ch == '.'){
            return CharType.CHAR_POINT;
        }else if(ch == '+' || ch == '-'){
            return CharType.CHAR_SIGN;
        }else if(ch == ' '){
            return CharType.CHAR_SPACE;
        }else{
            return CharType.CHAR_ILLEGAL;
        }
    }

    // 使用一个双层哈希表映射状态之间的转移关系
    // 第一层键值对为离开状态-（转移路径-到达状态），第二层键值对为转移路径-到达状态
    public boolean isNumber(String s) {
        // 第一层Map
        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();

        // 从起始空格开始的状态转移
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);

        // 离开状态为符号位
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);

        // 离开状态为整数部分
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);

        // 离开状态为左边有整数的小数点
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);

        // 离开状态为左边无整数的小数点
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        // 离开状态为小数部分
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);

        // 离开状态为字符e
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);

        // 离开状态为指数部分的符号
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);

        // 离开状态为指数部分整数
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        // 离开状态为结尾空格
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        // 开始状态转移
        // 若遇到
        for(int i = 0; i <= length-1; i++){
            CharType transType = toCharType(s.charAt(i));
            // 排除非法输入
            if(transType == CharType.CHAR_ILLEGAL){
                return false;
            }
            if (!transfer.get(state).containsKey(transType)) {
                return false;
            } else {
                state = transfer.get(state).get(transType);
            }
      }

        // 终止状态
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }
}