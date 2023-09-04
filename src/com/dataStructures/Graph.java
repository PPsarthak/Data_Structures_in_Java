package com.dataStructures;

import java.util.*;

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
    //iterative is better for larger graph - needs manual stack handling
    static List<Integer> dfsIt(int start, int n, List<List<Integer>> adj){
        boolean[] visited = new boolean[n];
        List<Integer> dfs = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        st.push(start);
        while (!st.isEmpty()){
            int vertex = st.pop();
            visited[vertex] = true;
            dfs.add(vertex);
            for(Integer i: adj.get(vertex)){
                if(!visited[i]){
                    st.push(i);
                }
            }
        }
        return dfs;
    }
    public static void main(String[] args) {

    }
}
