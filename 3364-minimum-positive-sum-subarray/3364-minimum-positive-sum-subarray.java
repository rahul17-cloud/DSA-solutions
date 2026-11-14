import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        boolean found = false;

        // Fix the starting index of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;

            // Expand the subarray up to length r
            for (int j = i; j < n && (j - i + 1) <= r; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;

                // Check if the subarray length is within [l, r] and sum is positive
                if (length >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                    found = true;
                }
            }
        }

        return found ? minSum : -1;
    }
}
