class Solution {
    public int minChanges(String binaryString) {
        int totalChangesNeeded = 0;

        for (int currentIndex = 0; currentIndex < binaryString.length(); currentIndex += 2) {
            char firstCharacter = binaryString.charAt(currentIndex);
            char secondCharacter = binaryString.charAt(currentIndex + 1);

            if (firstCharacter != secondCharacter) {
                totalChangesNeeded++;
            }
        }

        return totalChangesNeeded;
    }
}
