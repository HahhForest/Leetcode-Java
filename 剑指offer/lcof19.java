package 剑指offer;

/*
剑指 Offer 19. 正则表达式匹配
请实现一个函数用来匹配包含'. '和'*'的正则表达式
模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）
在本题中，匹配是指字符串的所有字符匹配整个模式
例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配
 */
public class lcof19 {
}

/*
使用动态规划，dp[i][j]表示主串s前i个与模式串p前j个是否能够匹配
1、模式串第j个字符为小写字母：若s[i]==p[j]，则dp[i][j]=dp[i-1][j-1]，否则false
2、模式串第j个字符是’.‘：s[i]与p[j]一定成功匹配，则dp[i][j]=dp[i-1][j-1]
3、模式串第j个字符是’*‘：
    a、若s[i]==p[j-1]，则主串向前移，模式串此组合还可以继续匹配（也可以抛掉，见情况c）
    b、若s[i]!=p[i-1]，则该组合抛掉，主串位置不变
    c、无论如何，模式串此组合都可以抛掉（对应重复0次的情况），因此a、b中还要或一个抛掉组合主串位置不变的情况
    总：
        若s[i]==p[j-1]，dp[i][j]=dp[i-1][j] || dp[i][j-2]
        若s[i]!=p[j-1]，dp[i][j]=dp[i][j-2]
4、边界：两个空字符串匹配，即dp[0][0]=true
5、特殊情况：空主串和空模式串
    a、空模式串，若主串也为空，则为true；主串不为空，则为false
    b、空主串非空模式串，比如A='',B=a*b*c*，得具体计算。可以把矩阵的长宽都延长1，相当于s、p都加了一个相同的头字符串

还可以优化合并一些情况，此解答完全按照转移方程
 */
class Solution19 {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];

        // 初始化边界条件
        dp[0][0] = true;    // 空主串空模式串
        for(int i = 1; i <= sLen; i++){ // 非空主串空模式串
            dp[i][0] = false;
        }

        // 开始生成
        for(int i = 0; i <= sLen; i++){
            for(int j = 1; j <= pLen; j++){
                if(p.charAt(j-1) == '*'){   // 3、若模式串最后一位为‘*’
                    if(s.charAt(i-1) == p.charAt(j-2) && i!=0){ // 3a、匹配上
                        dp[i][j] = dp[i-1][j] || dp[i][j-2];
                    }
                    else{   // 3b、未匹配上
                        dp[i][j] = dp[i][j-2];
                    }
                }
                else if(p.charAt(j-1) == '.'){   // 2、若模式串最后一位为’.‘
                    dp[i][j]=dp[i-1][j-1];
                }
                else{   // 1、若模式串最后一位为普通字母
                    if(i == 0)  dp[i][j] = false;

                    if(s.charAt(i-1) == p.charAt(j-1)){ // 若匹配：看前一位
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{   // 未匹配，false
                        dp[i][j] = false;
                    }
                }
            }
        }

        // 返回结果
        return dp[sLen][pLen];
    }
}
