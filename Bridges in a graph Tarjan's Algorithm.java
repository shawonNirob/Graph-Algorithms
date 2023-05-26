package dataStructure;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BridgeTarjansAlgo {
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
    public void dfs(LinkedList<Edges> adj[], int parent, int node, boolean[] visited, int[] disc, int[] low, List<Edges> result){
        visited[node] = true;
        disc[node] = low[node] = time;
        time++;

        for(Edges edge: adj[node]){
            int u = edge.destination;

            if(u == parent) continue;
            if(!visited[u]){
                dfs(adj, node, u, visited, disc, low, result);

                low[node] = Math.min(low[node], low[u]);

              //the only rule for bridge
                if(low[u] > disc[node]){
                    result.add(new Edges(node, u));
                }
            }else{
                low[node] = Math.min(low[node], disc[u]);
            }
        }
    }

    public List<Edges>  findBridge(int V, LinkedList<Edges> adj[]){
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        List<Edges> result = new ArrayList<>();

        // Call the recursive dfs function to find bridge
        for(int i=1; i<V; i++){
            if(!visited[i]){
                dfs(adj,-1, i, visited, disc, low, result);
            }
        }

        // Collect the bridge
        return result;
    }


    public static void main(String[] args) {
        Graph graph = new Graph(13);

        graph.addEdges(1,2);
        graph.addEdges(1,4);
        graph.addEdges(2,3);
        graph.addEdges(3,4);
        graph.addEdges(4,5);
        graph.addEdges(5,6);
        graph.addEdges(6,7);
        graph.addEdges(6,9);
        graph.addEdges(7,8);
        graph.addEdges(8,9);
        graph.addEdges(8,10);
        graph.addEdges(10,11);
        graph.addEdges(10,12);
        graph.addEdges(11,12);


        BridgeTarjansAlgo bridge = new BridgeTarjansAlgo();
        bridge.printGraph(graph.adj);

        System.out.println();
        List<Edges> ans = bridge.findBridge(13, graph.adj);

        if(ans.size() == 0){
            System.out.println("Caution! There is no Bridge");
        }else{
            for(Edges edg: ans){
                System.out.println(edg.source+"-"+ edg.destination);
            }
        }
    }
}
