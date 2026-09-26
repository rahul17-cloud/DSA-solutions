import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Map to store key-value pairs for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // Find the closing bracket
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                
                // Extract the key inside the brackets
                String key = s.substring(i + 1, j);
                
                // Append the value from the map, or "?" if not found
                result.append(map.getOrDefault(key, "?"));
                
                // Move the pointer past the closing bracket ')'
                i = j + 1;
            } else {
                // Append regular characters outside of brackets
                result.append(c);
                i++;
            }
        }
        
        return result.toString();
    }
}
