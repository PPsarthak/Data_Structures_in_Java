package com.dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Graph {

    // BFS Traversal
    static List<Integer> bfs(int start, int n, List<List<Integer>> adj){
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[n]; // n = no of vertices / nodes
        Queue<Integer> q = new LinkedList<>();

        //starting node is 0 but can vary
        q.offer(start);
        visited[0] = true;

        while (!q.isEmpty()){
            int node = q.poll();
            bfs.add(node);
            for (Integer i : adj.get(node)){
                //only if the node is not visited, we will add it to the queue and mark as visited
                if(!visited[i]){
                    q.offer(i);
                    visited[i] = true;
                }
            }

        }
        return bfs;
    }
    static List<Integer> dfs(int start, int n, List<List<Integer>> adj){
        boolean[] visited = new boolean[n]; // n = no of nodes/vertices
        List<Integer> dfs = new ArrayList<>();
        dfsRec(start, visited, dfs, adj);
        return dfs;
    }
    static void dfsRec(int start, boolean[] visited, List<Integer> dfs, List<List<Integer>> adj){
        visited[start] = true;
        dfs.add(start);
        for(Integer i : adj.get(start)){
            if(!visited[i]){
                dfsRec(i, visited, dfs, adj);
            }
        }
    }
    public static void main(String[] args) {

    }
}
