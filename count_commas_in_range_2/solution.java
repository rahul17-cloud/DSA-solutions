class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000;
        
        while (threshold <= n) {
            totalCommas += (n - threshold + 1);
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}
