class Solution {
    public int countDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        HashSet<String> set = new HashSet<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    StringBuilder shape = new StringBuilder();
                    dfs(i, j, visited, grid, i, j, m, n, shape);
                    set.add(shape.toString());
                }
            }
        }
        return set.size();
    }

    public void dfs(int row, int col, boolean[][] visited, int[][] grid, int row0, int col0, int m, int n,
                    StringBuilder shape) {
        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] || grid[row][col] != 1)
            return;

        visited[row][col] = true;
        shape.append(row - row0);
        shape.append(col - col0);

        int[] rowA = { -1, 0, 1, 0 };
        int[] colA = { 0, -1, 0, 1 };

        for (int i = 0; i < 4; i++) {
            dfs(row + rowA[i], col + colA[i], visited, grid, row0, col0, m, n, shape);
        }
    }
}


TC = (mxn) x log(set(mxn)) + (mxnx4);
