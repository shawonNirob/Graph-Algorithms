//This is according to Kahn's Algorithm
//not better approch
//see DFS

package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TopoSortBFS {
    private int node;
    private LinkedList<Integer> adj[];

    TopoSortBFS(int node){
        node = node;
        adj = new LinkedList[node];
        for(int i=0; i<node; i++){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdges(int source, int destination) {
        adj[source].add(destination);
    }

    public boolean topoBFS(int node){
        int[] indegree = new int[node];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<node; i++){
            for(int n: adj[i]){
                indegree[n]++;
            }
        }
        for(int i=0; i<node; i++){
            if(indegree[i]==0) queue.add(i);
        }

        int count = 0;
        while(!queue.isEmpty()){
            int n = queue.poll();
            count++;
            for(int i : adj[n]){
                indegree[i]--;
                if(indegree[i]==0) queue.add(i);
            }
        }
        if(count==node) return false;
        return true;
    }

    public static void main(String[] args) {
        TopoSortBFS graph = new TopoSortBFS(6);

        graph.addEdges(2,3);
        graph.addEdges(3,1);
        graph.addEdges(3,5);
        graph.addEdges(4,0);
        graph.addEdges(4,1);
        graph.addEdges(5,0);
        graph.addEdges(5,2);

        System.out.println(graph.topoBFS(6));
    }
}
