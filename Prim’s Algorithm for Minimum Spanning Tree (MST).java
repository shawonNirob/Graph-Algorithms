package dataStructure;

import java.util.*;

public class PrimsAlgo{
    static int[] parrent; //for print the MST
  
    public static class Edges {
        public int source;
        public int destination;
        public int weight;

        public Edges(int source, int destination, int weight){
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Graph{
        public int vertices;
        public LinkedList<Edges> adj[];

        public Graph(int vertices){
            this.vertices = vertices;
            adj = new LinkedList[vertices];

            for(int i = 0; i < vertices; i++){
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdges(int source, int destination, int weight) {
            Edges edges = new Edges(source, destination, weight);
            adj[source].add(edges);
            //// For an undirected graph, add the reverse edge as well
            edges = new Edges(destination,source, weight);
            adj[destination].add(edges);
        }
    }

    public void printGraph(LinkedList<Edges> adj[]){
        for(int i=0; i< adj.length; i++){
            for(Edges edge : adj[i]){
                System.out.println(i+" Connected with "+edge.destination + " with weight "+ edge.weight);
            }
        }
    }

    public int primMST(int source,LinkedList<Edges> adj[]){
        boolean[] visited = new boolean[adj.length];
        parrent = new int[adj.length];
        PriorityQueue<Edges> pq = new PriorityQueue<>(Comparator.comparingInt(Edges -> Edges.weight));
        int totalCost = 0;
        pq.add(new Edges(-1,0, 0));

        while(!pq.isEmpty()) {
            Edges edge = pq.poll();
            int node = edge.destination;
            int weight = edge.weight;
            int parr = edge.source;

            if(visited[node]) continue;

            if(parr != -1){
                parrent[node] = parr;
            }

            totalCost += weight;
            visited[node] = true;

            for (Edges edg : adj[node]) {
                int u = edg.destination;
                int w = edg.weight;

                if(!visited[u]){
                    pq.add(new Edges( node, u, w));
                }
            }
        }
        return totalCost;
    }
    public static void main(String[] args){
        Graph graph = new Graph(5);

        graph.addEdges(0, 1, 2);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 2, 1);
        graph.addEdges(2, 4, 2);
        graph.addEdges(2, 3, 2);
        graph.addEdges(3, 4, 1);

        PrimsAlgo algo = new PrimsAlgo();
        algo.primMST(0, graph.adj);
        algo.printGraph(graph.adj);

        System.out.println();
        System.out.println("Total cost For Minimum Spanning Tree: "+algo.primMST(0, graph.adj));

        System.out.println("Minimum Spanning Tree:");
        for(int i=0; i<parrent.length; i++){
            System.out.println(parrent[i]+"-"+i);
        }
    }
}
