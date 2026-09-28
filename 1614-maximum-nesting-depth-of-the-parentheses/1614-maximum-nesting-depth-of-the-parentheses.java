class Solution {
    public int maxDepth(String s) {
        int maxNestingDepth = 0;
        int currentDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxNestingDepth = Math.max(maxNestingDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }

        return maxNestingDepth;
    }
}
