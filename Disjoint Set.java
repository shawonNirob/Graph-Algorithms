package dataStructure;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DisjointSet {
    public static int[] parrent;
    public static int[] rank;

    public DisjointSet(int size){
        parrent = new int[size];
        rank = new int[size];

        for(int i=0; i<size; i++){
            parrent[i] = i;
            rank[i] = 0;
        }
    }
    //Path Compression
    public int find(int u){
        if(parrent[u] == u){
            return u;
        }else{
            return parrent[u] = find(parrent[u]);
        }
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rank[rootX] > rank[rootY]){
            parrent[rootY] = rootX;
        }else if(rank[rootX] < rank[rootY]) {
            parrent[rootX] = rootY;
        }else{
            parrent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(9);
        set.union(1,2);
        set.union(2,3);
        set.union(4,5);
        set.union(6,7);
        set.union(5,6);
        set.union(3,7);

        System.out.println(set.find(1));
        //System.out.println(set.find(2)); //For path compression
        //System.out.println(set.find(3));
        System.out.println(set.find(4));
        System.out.println(set.find(5));
        System.out.println(set.find(6));
        System.out.println(set.find(7));
        System.out.println(set.find(8));

        for(int i=1; i<9; i++){
            System.out.println("Node of "+i+" Parrent: "+parrent[i]+" rank: "+rank[i]);
        }
    }
}

