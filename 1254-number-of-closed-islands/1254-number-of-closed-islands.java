class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Flood fill / eliminate all boundary-connected land (0s)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If it is on the border and it's land, trigger DFS to turn it into water
                if (r == 0 || r == rows - 1 || c == 0 || c == cols - 1) {
                    if (grid[r][c] == 0) {
                        dfs(grid, r, c);
                    }
                }
            }
        }
        
        // Step 2: Count the remaining isolated closed islands
        int closedIslandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    closedIslandsCount++;
                    dfs(grid, r, c); // Sink this island so it isn't counted again
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: Out of bounds or already water (1)
        if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        // Change land (0) to water (1) to mark it as visited
        grid[r][c] = 1;
        
        // Explore 4-directional neighbors
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
