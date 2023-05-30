//Brute force [Time Limit Exceeded]
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return matrix;
        int cols = matrix[0].length;
        int[][] dist = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    dist[i][j] = 0;
                } else {
                    for (int k = 0; k < rows; k++) {
                        for (int l = 0; l < cols; l++) {
                            if (matrix[k][l] == 0) {
                                int dist_01 = Math.abs(k - i) + Math.abs(l - j);
                                dist[i][j] = Math.min(dist[i][j], dist_01);
                            }
                        }
                    }
                }
            }
        }
        return dist;
    }
}



//BFS
//TC - O(mxn)
//SC - O(mxn)
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return matrix;
        int n = matrix[0].length;
        
        boolean[][] visited = new boolean[m][n];
        int[][] distance = new int[m][n];
        
        Queue<Triple> queue = new LinkedList<>();
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 0){
                    queue.add(new Triple(i, j, 0));
                    visited[i][j] = true;
                    distance[i][j] = 0;
                }
            }
        }
        
        int[] rowPoint = {0, 0, -1, 1};
        int[] colPoint = {-1, 1, 0, 0};
        
        while(!queue.isEmpty()){
            Triple pointer = queue.poll();
            int row = pointer.row;
            int col = pointer.col;
            int dist = pointer.dist;
            
            for(int i=0; i<4; i++){
                int r = row + rowPoint[i];
                int c = col + colPoint[i];
                
                if(r>=0 && r<m && c>=0 && c<n && !visited[r][c]){
                    int d = dist + 1;
                    distance[r][c] = d;
                    visited[r][c] = true;
                    queue.add(new Triple(r, c, d));
                }
            }
        }
        
        return distance;
    }
}
public class Triple{
    int row;
    int col;
    int dist;
    public Triple(int row, int col, int dist){
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}


