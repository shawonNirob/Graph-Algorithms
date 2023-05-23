package dataStructure;

import java.util.*;

public class KruskalAlgo{
    public static class Edges {
        public int source;
        public int destination;
        public int weight;

        public Edges(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
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

        public void addEdges(int source, int destination, int weight) {
            Edges edges = new Edges(source, destination, weight);
            adj[source].add(edges);
            //// For an undirected graph, add the reverse edge as well
            edges = new Edges(destination, source, weight);
            adj[destination].add(edges);
        }
    }

    public void printGraph(LinkedList<Edges> adj[]){
        for(int i = 0; i < adj.length; i++) {
            for (Edges edge : adj[i]) {
                System.out.println(i + " Connected with " + edge.destination + " with weight " + edge.weight);
            }
        }
    }

    public static class DisJointSetMST{
        public static int[] parrent;
        public static int[] rank;
        public static int[] size;

        public DisJointSetMST(int V) {
            parrent = new int[V];
            rank = new int[V];
            size = new int[V];

            for (int i = 0; i < V; i++) {
                parrent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        //Path Compression
        public int find(int u) {
            if (parrent[u] == u) {
                return u;
            } else {
                return parrent[u] = find(parrent[u]);
            }
        }

        public void unionByRank(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            //cycle detection
            if (rootX == rootY) {
                System.out.println("Cycle detected");
                return;
            }

            if (rank[rootX] > rank[rootY]) {
                parrent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parrent[rootX] = rootY;
            } else {
                parrent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        public void unionBySize(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            //cycle detection
            if (rootX == rootY) {
                System.out.println("Cycle detected");
                return;
            }

            if (size[rootX] > size[rootY]) {
                parrent[rootY] = rootX;
                size[rootX] += size[rootY];
            } else if (size[rootX] < size[rootY]) {
                parrent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parrent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
    }

    static class solution{
        public int KruskalAlgorithm(int V, LinkedList<Edges> adj[]){
            List<Edges> edges = new ArrayList<>();
            List<Edges> resultMST = new ArrayList<>();
            int totalWeight = 0;

            //copy all edges to list
            for(int i = 1; i < adj.length; i++) {
                for (Edges edge : adj[i]){
                    int src = edge.source;
                    int des = edge.destination;
                    int wt = edge.weight;

                    if(src > des) continue; //for avoid duplication edges of directed graph;

                    edges.add(new Edges(src, des, wt));
                }
            }

            //sort the list
            Collections.sort(edges, Comparator.comparingInt(Edges -> Edges.weight));

            //print the list
            System.out.println("w u v :");
            for(int i=0; i<edges.size(); i++){
                Edges edg = edges.get(i);
                System.out.println(edg.weight+" "+edg.source+" "+edg.destination);
            }

            //use disjoint set Kruskal minimum spanning tree
            DisJointSetMST mst = new DisJointSetMST(V);
            for(int i=0; i<edges.size(); i++){
                int src = edges.get(i).source;
                int des = edges.get(i).destination;
                int wt = edges.get(i).weight;

                if(mst.find(src) != mst.find(des)){
                    resultMST.add(new Edges(src,des,wt));
                    totalWeight += wt;
                    mst.unionByRank(src, des);
                }
            }

            //print the minimum spanning tree
            System.out.println("Kruskals Minimum Spanning tree: ");
            for(int i=0; i<resultMST.size(); i++){
                System.out.println(resultMST.get(i).weight+" "+resultMST.get(i).source+" "+resultMST.get(i).destination);
            }
            return totalWeight;
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph(7);

        graph.addEdges(1,2,2);
        graph.addEdges(1,4,1);
        graph.addEdges(1,5,4);
        graph.addEdges(2,4,3);
        graph.addEdges(2,3,3);
        graph.addEdges(2,6,7);
        graph.addEdges(3,6,8);
        graph.addEdges(3,4,5);
        graph.addEdges(4,5,9);

        KruskalAlgo call = new KruskalAlgo();
        call.printGraph(graph.adj);

        System.out.println();

        solution mst = new solution();
        System.out.println("Total Weight of Minimum Spanning Tree: "+mst.KruskalAlgorithm(7, graph.adj));
    }
}
