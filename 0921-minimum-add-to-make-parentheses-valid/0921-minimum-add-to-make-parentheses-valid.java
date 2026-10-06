class Solution {
    public int minAddToMakeValid(String s) {
        int openNeed = 0;   // Unmatched closing ')'
        int closeNeed = 0;  // Unmatched opening '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                closeNeed++;
            } else {
                if (closeNeed > 0) {
                    closeNeed--; // Match with an existing '('
                } else {
                    openNeed++;  // Unmatched ')' needs a preceding '('
                }
            }
        }

        return openNeed + closeNeed;
    }
}
