//dfs
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces =0;
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                provinces++;
                dfs(i, isConnected, visited);
            }
        }
        return provinces;
    }
    public void dfs(int node, int[][] isConnected, boolean[] visited){
        visited[node] = true;
        
        for(int i=1; i<isConnected.length; i++){
            if(isConnected[node][i] == 1 && !visited[i]){
                dfs(i, isConnected, visited);
            }
        }
    }
}

//bfs
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces =0;
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                provinces++;
                bfs(i, isConnected, visited);
            }
        }
        return provinces;
    }
    public void bfs(int node, int[][] isConnected, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        visited[node] = true;
        
        while(!q.isEmpty()){
            node = q.poll();
            
            for(int i=0; i<isConnected.length; i++){
                if(isConnected[node][i] == 1 && !visited[i]){
                    q.add(i);
                    visited[node] = true;
                }
            }
        }
    }
}
              
              
//union find (union by rank)
class Solution {
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        int[] parrent = new int[n];
        int[] rank = new int[n];
        
        //initialize the parrent
        for(int i=0; i<n; i++){
            parrent[i] = i;
        }
        
        //call the union
        for(int i=0; i<n; i++){
            for(int j=0; j<isConnected[0].length; j++){
                if(isConnected[i][j] == 1){
                    unionByRank(i, j, parrent, rank);
                }
            }
        }
        
        
        int provinces = 0;
        for(int i=0; i<n; i++){
            if(parrent[i]==i) provinces++;
        }
        
        return provinces;
    }
    
    public void unionByRank(int x, int y,int[] parrent, int[] rank){
        int rootX = find(x, parrent);
        int rootY = find(y, parrent);
        
        if(rank[rootX] > rank[rootY]){
            parrent[rootY] = rootX;
        }else if(rank[rootX] < rank[rootY]){
            parrent[rootX] = rootY;
        }else{
            parrent[rootY] = rootX;
            rootX++;
        }
    }
    
    public int find(int u, int[] parrent){
        if(parrent[u] == u){
            return u;
        }else{
            return parrent[u] = find(parrent[u], parrent);
        }
    }
}
              


//Union by size
class Solution {
    public int findCircleNum(int[][] isConnected){
        int n = isConnected.length;
        int[] parrent = new int[n];
        int[] size = new int[n];
        
        //initialize the parrent
        for(int i=0; i<n; i++){
            parrent[i] = i;
            size[i] = 1;
        }
        
        //call the union
        for(int i=0; i<n; i++){
            for(int j=0; j<isConnected[0].length; j++){
                if(isConnected[i][j] == 1){
                    unionBySize(i, j, parrent, size);
                }
            }
        }
        
        //find the ultimate parrent
        int provinces = 0;
        for(int i=0; i<n; i++){
            if(parrent[i]==i) provinces++;
        }
        
        return provinces;
    }
    
    public void unionBySize(int x, int y,int[] parrent, int[] size){
        int rootX = find(x, parrent);
        int rootY = find(y, parrent);
        
        if(size[rootX] > size[rootY]){
            parrent[rootY] = rootX;
            size[rootX] += size[rootY];
            
        }else if(size[rootX] < size[rootY]){
            parrent[rootX] = rootY;
            size[rootX] += size[rootY];
            
        }else{
            parrent[rootY] = rootX;
             size[rootX] += size[rootY];
        }
    }
    
    public int find(int u, int[] parrent){
        if(parrent[u] == u){
            return u;
        }else{
            return parrent[u] = find(parrent[u], parrent);
        }
    }
}

//O(n^2) TC
//O(n) SC
              



