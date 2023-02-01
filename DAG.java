package dataStructure;

import java.util.LinkedList;


public class DirectedGraph {
    public static class GraphD {
        public int vertices;
        public LinkedList<Integer> adj[];

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
    }

    public static void main(String[] args) {
        GraphD graph = new GraphD(6);

        graph.addEdgesD(2,3);
        graph.addEdgesD(3,1);
        graph.addEdgesD(3,1);
        graph.addEdgesD(4,0);
        graph.addEdgesD(4,1);

    }
}
