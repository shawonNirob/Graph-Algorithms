package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArticulationPoint {
    private static int time; //to keep track of discovery time
    public static class Edges {
        public int destination;
        public int source;

        public Edges(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    private static class Graph {
        public int vertices;
        public LinkedList<Edges> adj[];

        public Graph(int vertices){
            time = 1;
            this.vertices = vertices;
            adj = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdges(int source, int destination) {
            Edges edges = new  Edges(source, destination);
            adj[source].add(edges);

            //// For an undirected graph, add the reverse edge as well
            edges = new Edges(destination, source);
            adj[destination].add(edges);
        }
    }
    public void printGraph(LinkedList<Edges> adj[]){
        for(int i=0; i< adj.length; i++){
            for(Edges edge : adj[i]){
                System.out.println(i+" Connected with "+edge.destination);
            }
        }
    }
    public void findArticulationPoints(LinkedList<Edges> adj[], int node, int parent, boolean[] visited, int[] disc, int[] low, boolean[] ap){
        int children = 0;
        visited[node] = true;
        disc[node] = low[node] = time;
        time++;

        for(Edges edge : adj[node]){
            int u = edge.destination;

            if(parent == u) continue;
          
            if (!visited[u]){
                findArticulationPoints(adj, u, node, visited, disc, low, ap);

                low[node] = Math.min(low[node], low[u]);
              
                if(low[u] >= disc[node] && parent != -1){
                    ap[node] = true;
                }
                children++;
              
            }else{
                low[node] = Math.min(low[node], disc[u]);
            }
            if(parent == -1 && children > 1){
                ap[node] = true;
            }
        }
    }

    public List<Integer>  findArticulationPoints(int V, LinkedList<Edges> adj[]){
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] ap = new boolean[V];

        int parent = -1;
        // Call the recursive helper function to find articulation points
        for(int i=1; i<V; i++){
            if(!visited[i]){
                findArticulationPoints(adj, i, parent, visited, disc, low, ap);
            }
        }

        // Collect the articulation points
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<V; i++){
            if(ap[i]) result.add(i);
        }

        return result;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(8);

        graph.addEdges(1, 2);
        graph.addEdges(1, 3);
        graph.addEdges(3, 4);
        graph.addEdges(3, 7);
        graph.addEdges(4, 5);
        graph.addEdges(4, 6);
        graph.addEdges(6, 7);

        ArticulationPoint ap  = new ArticulationPoint();
        ap.printGraph(graph.adj);

        System.out.println();
        List<Integer> artPoint = ap.findArticulationPoints(8, graph.adj);
        System.out.println(artPoint);

    }
}

