class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Array to store the minimum value from index i to n-1
        int[] minSuff = new int[n];
        minSuff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuff[i] = Math.min(nums[i], minSuff[i + 1]);
        }
        
        // Track the running maximum from index 0 to i
        int maxPref = nums[0];
        for (int i = 0; i < n; i++) {
            maxPref = Math.max(maxPref, nums[i]);
            
            // Calculate instability score and check if it's stable
            if (maxPref - minSuff[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
