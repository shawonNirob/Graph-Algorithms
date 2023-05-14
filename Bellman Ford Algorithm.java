package dataStructure;

import java.util.*;

public class BellmanFordAlgo{
    public static class Edges {
        public int destination;
        public int weight;

        public Edges(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static class Graph {
        public int vertices;
        public LinkedList<Edges> adj[];

        public Graph(int vertices){
            this.vertices = vertices;
            adj = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdges(int source, int destination, int weight) {
            Edges edges = new Edges(destination, weight);
            adj[source].add(edges);

            //// For an undirected graph, add the reverse edge as well
            //edges = new Edges(source, weight);
            //adj[destination].add(edges);
        }
    }

    public void printGraph(LinkedList<Edges> adj[]){
        for(int i=0; i< adj.length; i++){
            for(Edges edge : adj[i]){
                System.out.println(i+" Connected with "+edge.destination + " with weight "+ edge.weight);
            }
        }
    }
    public int[] bellmanFord(int V, LinkedList<Edges> adj[], int source){
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for(int i=1; i<V; i++){
            for(int u=0; u<V; u++){
                for(Edges edges : adj[u]){
                    int v = edges.destination;
                    int wt = edges.weight;

                    if(distance[u] != Integer.MAX_VALUE && distance[u]+wt < distance[v]){
                        distance[v] = distance[u]+wt ;
                    }
                }
            }
        }
        //For neative cycle detection nth time
        for(int u=0; u<V; u++){
            for(Edges edges : adj[u]){
                int v = edges.destination;
                int wt = edges.weight;

                if(distance[u] != Integer.MAX_VALUE && distance[u]+wt < distance[v]){
                    System.out.println("Caution! There is a Negetive Cycle");
                }
            }
        }

        return distance;
    }
    public static void main(String[] args){
        Graph graph = new Graph(6);

        graph.addEdges(0, 1, 5);
        graph.addEdges(1, 2, -2);
        graph.addEdges(1, 5, -3);
        graph.addEdges(2, 4, 3);
        graph.addEdges(3, 4, -2);
        graph.addEdges(3, 2, 6);
        graph.addEdges(5, 3, 1);



        BellmanFordAlgo algo = new BellmanFordAlgo();

        algo.printGraph(graph.adj);
        System.out.println();


        int[] distance = algo.bellmanFord(6, graph.adj, 0);
        for(int i=0; i< distance.length; i++){
            System.out.println("distance from 0 to "+i+" is: "+distance[i]);
        }
    }
}
