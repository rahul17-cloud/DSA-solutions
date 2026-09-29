class Solution {
    private Boolean[][][] memo;
    private char[][] grid;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        // Quick check: path length must be even, start must be '(', end must be ')'
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

    // Max possible balance k won't exceed (m + n)
        memo = new Boolean[m][n][m + n];
        return dfs(0, 0, 0);
    }

    private boolean dfs(int i, int j, int k) {
        // Update balance based on current cell
        k += (grid[i][j] == '(') ? 1 : -1;
        
        // If balance becomes negative, invalid path
        if (k < 0) {
            return false;
        }

        // If we reach the bottom-right corner, check if balance is 0
        if (i == m - 1 && j == n - 1) {
            return k == 0;
        }

        // Check memoization table
        if (memo[i][j][k] != null) {
            return memo[i][j][k];
        }

        boolean res = false;
        // Move down
        if (i + 1 < m) {
            res = res || dfs(i + 1, j, k);
        }
        // Move right
        if (j + 1 < n) {
            res = res || dfs(i + 1 == m && j + 1 == n ? i : i, j + 1, k); // clean right move below
        }
        
        // Let's write standard move right:
        res = false;
        if (i + 1 < m && dfs(i + 1, j, k)) {
            return memo[i][j][k] = true;
        }
        if (j + 1 < n && dfs(i, j + 1, k)) {
            return memo[i][j][k] = true;
        }

        return memo[i][j][k] = false;
    }
}
