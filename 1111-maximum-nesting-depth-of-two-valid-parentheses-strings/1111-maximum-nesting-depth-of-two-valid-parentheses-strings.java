class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            // Using character index parity:
            // If seq[i] == '(', assign based on i & 1
            // If seq[i] == ')', assign based on 1 - (i & 1)
            res[i] = seq.charAt(i) == '(' ? i & 1 : (1 - (i & 1));
        }
        return res;
    }
}
