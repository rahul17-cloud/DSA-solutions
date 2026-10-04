class Solution {
    public boolean checkValidString(String s) {
        int low = 0; // Minimum possible open parentheses count
        int high = 0; // Maximum possible open parentheses count

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(0, low - 1);
                high--;
            } else { // c == '*'
                low = Math.max(0, low - 1); // treat '*' as ')'
                high++;                     // treat '*' as '('
            }
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}
