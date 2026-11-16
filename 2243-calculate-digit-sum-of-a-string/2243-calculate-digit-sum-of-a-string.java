class Solution {
    public String digitSum(String s, int k) {
        // Repeat rounds as long as the length of s is greater than k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Iterate through s in steps of k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                
                // Calculate the digit sum of the current group of size up to k
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }
                
                // Append the sum to the new string
                nextRound.append(sum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
