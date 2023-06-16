//Undirected graph
package dataStructure;

import java.util.*;

public class DijkstraAlgo{
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
        }
    }

    public void printGraph(LinkedList<Edges> adj[]){
        for(int i=0; i< adj.length; i++){
            for(Edges edge : adj[i]){
                System.out.println(i+" Connected with "+edge.destination + " with weight "+ edge.weight);
            }
        }
    }
    public int[] dijkstra(int N, LinkedList<Edges> adj[], int source){
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Edges> pq = new PriorityQueue<>(Comparator.comparingInt(Edges -> Edges.weight));
        pq.add(new Edges(source, 0));

        while (!pq.isEmpty()){
            Edges curr = pq.poll();
            int u= curr.destination;
            int dist = curr.weight;

            if(dist > distance[u]) continue; //execute at the last line of the pq in this graph

            for(Edges neighbor : adj[u]){
                int v = neighbor.destination;
                int costUV = neighbor.weight;

                if(distance[u] + costUV < distance[v]){
                    distance[v] = distance[u] + costUV;
                    pq.add(new Edges(v, distance[v]));
                }
            }
        }

        for(int i=source; i<N; i++){
            System.out.println("Distance from source to Node "+i+" : "+distance[i]+" ");
        }

        return distance;
    }



    public static void main(String[] args){
        DijkstraAlgo.Graph graph = new DijkstraAlgo.Graph(6);


        graph.addEdges(1, 2, 2);
        graph.addEdges(1, 4, 1);
        graph.addEdges(2, 1, 2);
        graph.addEdges(2, 3, 4);
        graph.addEdges(2, 5, 5);
        graph.addEdges(3, 4, 3);
        graph.addEdges(3, 2, 4);
        graph.addEdges(3, 5, 1);
        graph.addEdges(4, 1, 1);
        graph.addEdges(4, 3, 3);
        graph.addEdges(5, 2, 5);
        graph.addEdges(5, 3, 1);


        DijkstraAlgo dij = new DijkstraAlgo();

        dij.printGraph(graph.adj);
        System.out.println();
        dij.dijkstra(6, graph.adj, 1);
        
    }
}


//Undirected graph
package dataStructure;

import java.util.*;

public class DijkstraAlgo{
    public static class Edges {
        public int destination;
        public int weight;

        public Edges(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class Graph {
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
            edges = new Edges(source, weight);
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
    public int[] dijkstra(int N, LinkedList<Edges> adj[], int source){
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        //sort the queue according to weight
        PriorityQueue<Edges> pq = new PriorityQueue<>(Comparator.comparingInt(Edges -> Edges.weight));
        pq.add(new Edges(source, 0));

        while (!pq.isEmpty()){
            Edges curr = pq.poll();
            int u= curr.destination;
            int dist = curr.weight;

            if(dist > distance[u]) continue; //execute at the last line of the pq in this graph

            for(Edges neighbor : adj[u]){
                int v = neighbor.destination;
                int costUV = neighbor.weight;

                if(distance[u] + costUV < distance[v]){
                    distance[v] = distance[u] + costUV;
                    pq.add(new Edges(v, distance[v]));
                }
            }
        }

        for(int i=source; i<N; i++){
            System.out.println("Distance from source to Node "+i+" : "+distance[i]+" ");
        }

        return distance;
    }



    public static void main(String[] args){
        DijkstraAlgo.Graph graph = new DijkstraAlgo.Graph(6);


        graph.addEdges(1, 2, 2);
        graph.addEdges(1, 4, 1);
        graph.addEdges(2, 3, 4);
        graph.addEdges(2, 5, 5);
        graph.addEdges(3, 4, 3);
        graph.addEdges(3, 5, 1);



        DijkstraAlgo dij = new DijkstraAlgo();

        dij.printGraph(graph.adj);
        System.out.println();
        dij.dijkstra(6, graph.adj, 1);

    }
}

//
