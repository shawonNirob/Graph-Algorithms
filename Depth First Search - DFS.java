    public void DFS(int n) {
        stack = new Stack<>();
        boolean visited[] = new boolean[V];
        stack.push(n);

        while (!stack.isEmpty()) {
            n = stack.pop();

            if(visited[n]) {
                continue;
            }

            visited[n] = true;
            System.out.print(n + " ");
            for (int i = 0; i < adj[n].size(); ++i) {
                int p = adj[n].get(i);
                if (!visited[p]) {
                    stack.push(p);
                }
            }
        }
    }
