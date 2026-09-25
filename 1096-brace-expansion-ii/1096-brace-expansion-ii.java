import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> resultSet = parse(expression);
        List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        return sortedList;
    }

    private Set<String> parse(String expr) {
        Set<String> resultSet = new HashSet<>();
        int braceLevel = 0;
        int startIndex = 0;
        
        // 1. Process Union (Split by top-level commas)
        for (int i = 0; i < expr.length(); i++) {
            char currentChar = expr.charAt(i);
            if (currentChar == '{') {
                braceLevel++;
            } else if (currentChar == '}') {
                braceLevel--;
            } else if (currentChar == ',' && braceLevel == 0) {
                // Found a top-level union split point
                resultSet.addAll(parseConcat(expr.substring(startIndex, i)));
                startIndex = i + 1;
            }
        }
        
        // Process the final segment after the last comma
        resultSet.addAll(parseConcat(expr.substring(startIndex)));
        return resultSet;
    }

    private Set<String> parseConcat(String expr) {
        Set<String> currentWords = new HashSet<>();
        currentWords.add(""); // Base for concatenation
        
        int i = 0;
        int n = expr.length();
        
        while (i < n) {
            if (expr.charAt(i) == '{') {
                // Find the matching closing brace at the same nesting level
                int braceLevel = 0;
                int j = i;
                while (j < n) {
                    if (expr.charAt(j) == '{') braceLevel++;
                    else if (expr.charAt(j) == '}') braceLevel--;
                    
                    if (braceLevel == 0) break;
                    j++;
                }
                
                // Recursively parse the contents inside the braces
                Set<String> innerWords = parse(expr.substring(i + 1, j));
                currentWords = cartesianProduct(currentWords, innerWords);
                i = j + 1;
            } else {
                // Handle plain lowercase letter strings
                int j = i;
                while (j < n && Character.isLowerCase(expr.charAt(j))) {
                    j++;
                }
                Set<String> literalWord = new HashSet<>();
                literalWord.add(expr.substring(i, j));
                currentWords = cartesianProduct(currentWords, literalWord);
                i = j;
            }
        }
        
        return currentWords;
    }

    private Set<String> cartesianProduct(Set<String> set1, Set<String> set2) {
        Set<String> combined = new HashSet<>();
        for (String word1 : set1) {
            for (String word2 : set2) {
                combined.add(word1 + word2);
            }
        }
        return combined;
    }
}