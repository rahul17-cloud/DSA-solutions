import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Base score for the root level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Enter a new nested level, start with score 0
                stack.push(0);
            } else {
                // Finish current level: pop its score
                int currentScore = stack.pop();
                // If it was "()", score is 1; if nested "(A)", score is 2 * currentScore
                int val = (currentScore == 0) ? 1 : 2 * currentScore;
                // Add the computed score to the parent level
                stack.push(stack.pop() + val);
            }
        }

        return stack.pop();
    }
}
