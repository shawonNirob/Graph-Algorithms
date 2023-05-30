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

