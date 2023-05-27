package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SCC {

    public static int countSCC;
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
            //edges = new Edges(destination, source);
            //adj[destination].add(edges);
        }
    }
    public void printGraph(LinkedList<Edges> adj[]){
        for(int i=0; i< adj.length; i++){
            for(Edges edge : adj[i]){
                System.out.println(i+" Connected with "+edge.destination);
            }
        }
    }

    public void dfs(LinkedList<Edges> adj[], int node, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;
        for(Edges edge : adj[node]){
            int u = edge.destination;
            if(!visited[u]){
                dfs(adj, u, visited, stack);
            }
        }
        stack.push(node);
    }
    public void dfs2(List<List<Integer>> adjR, int node, boolean[] visited, List<Integer> component){
        visited[node] = true;
        component.add(node);
        for(int u : adjR.get(node)){
            if(!visited[u]){
                dfs2(adjR, u, visited, component);
            }
        }
    }

    public List<List<Integer>> findSCC(int V, LinkedList<Edges> adj[]){
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();

        //step - 01
        // Call the dfs to find max finishing time node
        for(int i=0; i<V; i++){
            if(!visited[i]){
                dfs(adj, i, visited, stack);
            }
        }

        //step - 02
        //reverse the graph
        List<List<Integer>> adjR = new ArrayList<>();
        for(int i=0; i<V; i++){
            adjR.add(new ArrayList<>());
        }
        for(int i=0; i<V; i++){
            visited[i] = false;
            for(Edges edg: adj[i]){
                int src = edg.source;
                int des  =edg.destination;

                adjR.get(des).add(src);
            }
        }

        //step - 03
        //call the dfs2 method to find SCC
        countSCC = 0;
        while(!stack.isEmpty()){
            int node = stack.pop();
            if(!visited[node]){
                countSCC++;
                List<Integer> component = new ArrayList<>();
                dfs2(adjR, node, visited, component);
                result.add(component);
            }
        }
        // Collect the SCC
        return result;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);

        graph.addEdges(0,1);
        graph.addEdges(1,2);
        graph.addEdges(2,0);
        graph.addEdges(2,3);
        graph.addEdges(3,4);
        graph.addEdges(4,5);
        graph.addEdges(4,7);
        graph.addEdges(5,6);
        graph.addEdges(6,4);
        graph.addEdges(6,7);

        SCC scc = new SCC();
        scc.printGraph(graph.adj);

        System.out.println();
        List<List<Integer>> result = scc.findSCC(8, graph.adj);

        if(result.size() == 0){
            System.out.println("Caution! There is no SCC");
        }else{
            for(List<Integer> list : result){
                for(int u : list){
                    System.out.print(u+" ");
                }
                System.out.println();
            }
            System.out.println("Total SCC Count: "+countSCC);
        }
    }
}
