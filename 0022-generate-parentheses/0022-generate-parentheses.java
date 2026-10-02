class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String currentString, int openCount, int closeCount, int n) {
        // Base case: If the current string length is 2*n, it is a valid combination.
        if (currentString.length() == 2 * n) {
            result.add(currentString);
            return;
        }

        // Recursive case 1: Add an opening parenthesis if we haven't used all n opening parentheses.
        if (openCount < n) {
            backtrack(result, currentString + "(", openCount + 1, closeCount, n);
        }

        // Recursive case 2: Add a closing parenthesis if the number of closing parentheses is less than the number of opening ones.
        if (closeCount < openCount) {
            backtrack(result, currentString + ")", openCount, closeCount + 1, n);
        }
    }
}
