package dataStructure.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class GraphImp {
    private int V;
    private LinkedList<Integer> adj[];
    private Queue<Integer> queue;
    private Stack<Integer> stack;

    GraphImp(int v) {
        V = v;
        adj = new LinkedList[V];
        for(int i=0; i<V; i++){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int v,int w) {
        adj[v].add(w);
    }

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
    public static void main(String args[]) {
        GraphImp graph = new GraphImp(8);

        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,3);
        graph.addEdge(0,4);
        graph.addEdge(0,7);
        graph.addEdge(1,0);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,0);
        graph.addEdge(2,1);
        graph.addEdge(2,5);
        graph.addEdge(2,6);
        graph.addEdge(3,0);
        graph.addEdge(3,1);
        graph.addEdge(3,4);
        graph.addEdge(4,0);
        graph.addEdge(4,3);
        graph.addEdge(4,5);
        graph.addEdge(4,7);
        graph.addEdge(5,2);
        graph.addEdge(5,4);
        graph.addEdge(5,6);
        graph.addEdge(6,2);
        graph.addEdge(6,5);
        graph.addEdge(7,0);
        graph.addEdge(7,4);


        System.out.println("The Breadth First Traversal of the graph is as follows :");
        graph.BFS(0);
        System.out.println();
        System.out.println("The Depth First Traversal of the graph is as follows :");
        graph.DFS(0);
    }
}
