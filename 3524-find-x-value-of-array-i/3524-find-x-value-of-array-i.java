class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // currentCounts[x] stores the count of subarrays ending at the current index 
        // with a product modulo k equal to x.
        long[] currentCounts = new long[k];
        
        for (int num : nums) {
            int val = num % k;
            long[] nextCounts = new long[k];
            
            // 1. A new subarray that starts at the current element
            nextCounts[val]++;
            
            // 2. Extend existing subarrays from the previous element
            for (int x = 0; x < k; x++) {
                if (currentCounts[x] > 0) {
                    int nextX = (x * val) % k;
                    nextCounts[nextX] += currentCounts[x];
                }
            }
            
            // Update the DP state for the next iteration
            currentCounts = nextCounts;
            
            // Collect the results of all subarrays ending at this position
            for (int x = 0; x < k; x++) {
                result[x] += currentCounts[x];
            }
        }
        
        return result;
    }
}
