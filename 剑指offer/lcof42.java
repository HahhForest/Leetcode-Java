package 剑指offer;

/*
剑指 Offer 42. 连续子数组的最大和
输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。
 */
public class lcof42 {
}

/*
动态规划达到时间复杂度的要求。
因为要求是连续窗口，所以定义dp[i]为包含nums[i]的窗口的最大子数组的和
状态转移方程：dp[i] = dp[i-1] + nums[i]，若dp[i-1] > 0；否则dp[i] = nums[i]
即若dp[i-1]小于0，那么包含nums[i]的子数组去掉dp[i-1]对应的子数组是更好的
边界条件：dp[0] = nums[0]，即包含第一个元素的最大子数组的组成即为第一个元素
 */
class Solution42 {
    public int maxSubArray(int[] nums) {
        if(nums == null)    return -1;
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
            if(dp[i] > maxSum)  maxSum = dp[i];
        }
        return maxSum;
    }
}