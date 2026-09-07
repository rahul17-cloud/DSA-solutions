import java.util.Arrays;

class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        
        int[] dp = new int[n + 1];
        dp[0] = 1; 
        int[] last = new int[26];
        Arrays.fill(last, -1);
        
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int charIndex = ch - 'a';
            
            dp[i] = (dp[i - 1] * 2) % MOD;
            
            if (last[charIndex] != -1) {
                int prevIndex = last[charIndex];
                dp[i] = (dp[i] - dp[prevIndex - 1] + MOD) % MOD;
            }
            
            last[charIndex] = i;
        }
        
        return (dp[n] - 1 + MOD) % MOD;
    }
}
