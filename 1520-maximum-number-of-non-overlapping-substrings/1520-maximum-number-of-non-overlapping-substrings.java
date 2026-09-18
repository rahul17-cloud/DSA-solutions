import java.util.*;

public class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[][] boundaries = new int[26][2];
        
        // Initialize first occurrences to n and last occurrences to -1
        for (int i = 0; i < 26; i++) {
            boundaries[i][0] = n;
            boundaries[i][1] = -1;
        }
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            boundaries[charIdx][0] = Math.min(boundaries[charIdx][0], i);
            boundaries[charIdx][1] = Math.max(boundaries[charIdx][1], i);
        }
        
        List<int[]> validIntervals = new ArrayList<>();
        
        // Step 2: Extend boundaries to form valid intervals
        for (int i = 0; i < 26; i++) {
            int left = boundaries[i][0];
            if (left == n) continue; // Character does not exist in the string
            
            int right = boundaries[i][1];
            int currentLeft = left;
            int currentRight = right;
            boolean isValid = true;
            
            // Dynamically expand the range as new characters are encountered
            for (int j = currentLeft; j <= currentRight; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                // If a character's first occurrence is before our starting point,
                // this interval is invalid for the current starting index.
                if (boundaries[charIdx][0] < left) {
                    isValid = false;
                    break;
                }
                currentRight = Math.max(currentRight, boundaries[charIdx][1]);
            }
            
            if (isValid) {
                validIntervals.add(new int[]{left, currentRight});
            }
        }
        
        // Step 3: Sort intervals by end index ascending. 
        // If ends are equal, sort by start index descending to pick the smaller nested one first.
        Collections.sort(validIntervals, (a, b) -> 
            a[1] == b[1] ? Integer.compare(b[0], a[0]) : Integer.compare(a[1], b[1])
        );
        
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        
        // Greedily pick non-overlapping intervals
        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];
            
            if (start > lastEnd) {
                result.add(s.substring(start, end + 1));
                lastEnd = end;
            }
        }
        
        return result;
    }
}
