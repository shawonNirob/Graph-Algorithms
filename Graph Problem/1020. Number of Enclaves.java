//BFS
class Solution{
    public int numEnclaves(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        int[] rowPoint = {0, 0, -1, 1};
        int[] colPoint = {-1, 1, 0, 0};
        
        Queue<Track> queue = new LinkedList<>();
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && (i == 0 || j == 0 || i == m-1 || j == n-1)){
                    queue.add(new Track(i, j));
                    visited[i][j] = true;
                }
            }
        }
        
        while(!queue.isEmpty()){
            Track pointer = queue.poll();
            
            for(int i=0; i<4; i++){
                int row = rowPoint[i] + pointer.row;
                int col = colPoint[i] + pointer.col;
                
                if(row>=0 && row<m && col>=0 && col<n && !visited[row][col] && grid[row][col] == 1){
                    queue.add(new Track(row, col));
                    visited[row][col] = true;
                }
            }
        }
        
        int enclaves = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j] && grid[i][j]==1){
                    enclaves++;
                }
            }
        }
        
        return enclaves;
    }
}

class Track{
    int row;
    int col;
    
    public Track(int row, int col){
        this.row = row;
        this.col = col;
    }
}

//DFS
class Solution{
    public int numEnclaves(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
         
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 1 && (i == 0 || j == 0 || i == m-1 || j == n-1)){
                    dfs(i, j, m, n, visited, grid);
                }
            }
        }
        
        
        int enclaves = 0;
        for(int i=1; i<m-1; i++){
            for(int j=1; j<n-1; j++){
                if(!visited[i][j] && grid[i][j]==1){
                    enclaves++;
                }
            }
        }
        
        return enclaves;
    }
    public void dfs(int r, int c, int m, int n, boolean[][] visited,int[][] grid){
        if(r<0 || r>=m || c<0 || c>=n || visited[r][c] || grid[r][c] == 0) return;
        
        int[] rowPoint = {0, 0, -1, 1};
        int[] colPoint = {-1, 1, 0, 0};
        
        visited[r][c] = true;
        
        for(int i=0; i<4; i++){
            int row = r+rowPoint[i];
            int col = c+colPoint[i];
            
            dfs(row, col, m, n, visited, grid);
        }
    }
}

