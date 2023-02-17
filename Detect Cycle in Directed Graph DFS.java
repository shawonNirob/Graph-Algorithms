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

    /*
    public boolean topoBFS(int node, int[] visited){
        for(int i=0; i<node; i++){
            if(visited[i]==0 && dfs(i, visited)) return true;
        }
        return false;
    }
    public boolean dfs(int node, int[] visited){
        visited[node] = 1;
        for(int n : adj[node]){
            if(visited[n]==0 && dfs(n, visited)) {
                return true;
            }else if(visited[n]==1){
                return true;
            }else if(visited[n]==2){
                return false;
            }
        }
        visited[node] = 2;
        return false;
    }
     */
    public boolean cycleDetect(int node){
        boolean[] visited = new boolean[node];
        for(int i=0; i<node; i++){
            boolean[] callStack = new boolean[node];
            if(!visited[i] && dfs(i, visited, callStack)){
                return true;
            }
        }
        return false;
    }

    public boolean dfs(int node, boolean[] visited, boolean[] callStack){
        visited[node] = true; callStack[node] = true;
        for(int n: adj[node]){
            if(visited[n]){
                if(callStack[n]) return true;
            }else if(dfs(n,visited,callStack)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        TopoSortBFS graph = new TopoSortBFS(6);

        graph.addEdges(2,3);
        graph.addEdges(3,1);
        //graph.addEdges(3,5);
        graph.addEdges(4,0);
        graph.addEdges(4,1);
        graph.addEdges(5,0);
        graph.addEdges(5,2);

        System.out.println("Is there any cycle (T=Yes/F=No): "+graph.cycleDetect(6));
    }
}
