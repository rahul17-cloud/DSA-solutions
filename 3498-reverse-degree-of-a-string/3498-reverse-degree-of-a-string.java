class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // 'a' becomes 26, 'b' becomes 25, ..., 'z' becomes 1
            int reversedAlphabetPos = 26 - (c - 'a');
            
            // 1-indexed position in the string
            int stringPos = i + 1;
            
            totalDegree += reversedAlphabetPos * stringPos;
        }
        
        return totalDegree;
    }
}
