import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        
        // Base case: starting from step 0 with 0 cost
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (dp[i] == Long.MAX_VALUE) continue;
            
            // From step i, we can jump to i + 1, i + 2, or i + 3
            for (int jump = 1; jump <= 3; jump++) {
                int nextStep = i + jump;
                if (nextStep <= n) {
                    // Cost formula: current_dp + costs[nextStep] + (jump)^2
                    // Note: costs array is 1-indexed, matching costs[nextStep - 1] in Java's 0-indexed array
                    long jumpCost = dp[i] + costs[nextStep - 1] + (long) jump * jump;
                    dp[nextStep] = Math.min(dp[nextStep], jumpCost);
                }
            }
        }
        
        return (int) dp[n];
    }
}
