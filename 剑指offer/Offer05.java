package 剑指offer;

/*
剑指 Offer 05. 替换空格
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class Offer05 {
    public static void main(String[] args){
        System.out.println("HelloWorld!");
    }
}

/*
重点是熟悉StringBuilder的使用
 */
class Solution05 {
    public String replaceSpace(String s) {
        StringBuilder ans = new StringBuilder();
        for(Character c : s.toCharArray()){
            if(c == ' '){
                ans.append("%20");
            }
            else{
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
