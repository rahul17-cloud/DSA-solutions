import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public String reverseParentheses(String s) {
        Deque<StringBuilder> stack = new ArrayDeque<>();
        stack.push(new StringBuilder()); // Initialize with an empty StringBuilder

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(new StringBuilder());
            } else if (c == ')') {
                StringBuilder top = stack.pop();
                top.reverse();
                stack.peek().append(top);
            } else {
                stack.peek().append(c);
            }
        }

        return stack.pop().toString();
    }
}
