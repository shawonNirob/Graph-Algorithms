package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class DirectedGraph {
    public static class GraphD {
        public int vertices;
        public LinkedList<Integer> adj[];

        Stack<Integer> stack;
        boolean[] visited;

        public GraphD(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdgesD(int source, int destination) {
            adj[source].add(destination);
        }

        public ArrayList<Integer> topoSortDfs(int vertices){
            stack = new Stack<>();
            visited = new boolean[vertices];
            for(int i=0; i<vertices; i++){
                if(!visited[i]){
                    dfs(i, visited, adj, stack);
                }
            }
            ArrayList<Integer> list = new ArrayList<>();
            while(!stack.isEmpty()){
                list.add(stack.pop());
            }
            return list;
        }
        public void dfs(int node, boolean[] visited, LinkedList<Integer> adj[], Stack<Integer> stack){
             visited[node] = true;
             for(int i: adj[node]){
                 if(!visited[i]) {
                     dfs(i, visited, adj, stack);
                 }
             }
             stack.push(node);
        }
    }

    public static void main(String[] args) {
        GraphD graph = new GraphD(6);

        graph.addEdgesD(2,3);
        graph.addEdgesD(3,1);
        graph.addEdgesD(3,1);
        graph.addEdgesD(4,0);
        graph.addEdgesD(4,1);

        System.out.println(graph.topoSortDfs(6));
    }
}
