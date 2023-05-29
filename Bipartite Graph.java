//A bipartite graph is a graph whose vertex set can be partitioned into two disjoint sets, 
//often referred to as "left" and "right" sets or "X" and "Y" sets. 
//In other words, every edge in the graph connects a vertex from one set to a vertex from the other set. 
//No edges exist within the same set. This partitioning creates a bipartite graph with two distinct subsets of vertices.


package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BipartiteGraph {
    public static class Edges {
        public int source;
        public int destination;

        public Edges(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    private static class Graph {
        public int vertices;
        public LinkedList<Edges> adj[];

        public Graph(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdges(int source, int destination) {
            Edges edges = new Edges(source, destination);
            adj[source].add(edges);

            //// For an undirected graph, add the reverse edge as well
            edges = new Edges(destination, source);
            adj[destination].add(edges);
        }
    }

    public void printGraph(LinkedList<Edges> adj[]) {
        for (int i = 0; i < adj.length; i++) {
            for (Edges edge : adj[i]) {
                System.out.println(i + " Connected with " + edge.destination);
            }
        }
    }

    public boolean dfs(int node, int kind, int[] color, LinkedList<Edges> adj[]){
        color[node] = kind;

        for(Edges edge : adj[node]){
            int u = edge.destination;

            if(color[u] == -1) {
                if(!dfs(u, 1-kind, color, adj)) return false;
            } else if (color[u] == kind) {
                return false;
            }
        }
        return true;
    }
    public boolean bipartite(int V, LinkedList<Edges> adj[]){
        int[] color  = new int[V];
        Arrays.fill(color, -1);

        for(int i=0; i<V; i++){
            if(color[i] == -1){
                if(!dfs(i, 0, color, adj))  return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Graph graph = new Graph(9);

        graph.addEdges(0,1);
        graph.addEdges(1,2);
        graph.addEdges(1,5);
        graph.addEdges(2,3);
        graph.addEdges(3,4);
        graph.addEdges(4,8);
        graph.addEdges(5,8);
        graph.addEdges(3,6);
        graph.addEdges(6,7);


        BipartiteGraph bipartite = new BipartiteGraph();
        bipartite.printGraph(graph.adj);

        if(bipartite.bipartite(9, graph.adj)){
            System.out.println("Graph is Bipartite");
        }else{
            System.out.println("Graph is not Bipartite");
        }
    }
}

