public void BFS(int n) {
        queue = new LinkedList<>();
        boolean visited[] = new boolean[V];
        visited[n] = true;
        queue.add(n);

        while(!queue.isEmpty()){
            n = queue.poll();
            System.out.print(n+" ");
            for(int i=0; i<adj[n].size(); ++i){
                int p = adj[n].get(i);
                if(!visited[p]){
                    visited[p] = true;
                    queue.add(p);
                }
            }
        }
    }
