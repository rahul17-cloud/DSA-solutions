class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // dp[i] stores the maximum number of valid palindromes in the prefix s[0...i-1]
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            // By default, carry forward the best result from the previous character
            dp[i] = dp[i - 1];
            
            // Check for a valid palindrome of length k ending at index i - 1
            if (i >= k && isPalindrome(s, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            
            // Check for a valid palindrome of length k + 1 ending at index i - 1
            if (i >= k + 1 && isPalindrome(s, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        
        return dp[n];
    }
    
    // Helper method to verify if a substring is a palindrome
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
