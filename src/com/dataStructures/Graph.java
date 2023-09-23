package com.dataStructures;

import java.util.*;

class Graph {
    static List<Integer> componentBFS(int start, List<List<Integer>> adj){
        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        for(int i=0; i<visited.length; i++){
            if(!visited[i]) componentBFSUtil(i, traversal, adj, visited);
        }
        return traversal;
    }
    static void componentBFSUtil(int start, List<Integer> traversal,
                                 List<List<Integer>> adj, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()){
            int temp = q.poll();
            traversal.add(temp);
            for(Integer i : adj.get(temp)){
                if(!visited[i]){
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
    // BFS Traversal
    static List<Integer> bfs(int start, int n, List<List<Integer>> adj){
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[n]; // n = no of vertices / nodes
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
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
    static boolean isBipartite(int[][] graph) {
        int[] colored = new int[graph.length];
        for(int i=0; i<colored.length; i++){
            if(colored[i] == 0){
                if(!bipartiteBfs(graph, colored, i)) return false;
            }
        }
        return true;
    }
    static boolean bipartiteBfs(int[][] graph, int[] colored, int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colored[start] = 2;
        while(!q.isEmpty()){
            int temp = q.poll();
            for(int i : graph[temp]){
                //if not colored yet, give appropriate color & add to Q
                if(colored[i] == 0){
                    colored[i] = 1-colored[temp];
                    q.offer(i);
                }
                //if color matches to current node, return false
                else if(colored[i] == colored[temp]) return false;
            }
        }
        return true;
    }
    //detect cycle in undirected graph using BFS Traversal
    static class cyclePair{
        int node;
        int parent;

        public cyclePair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
    static boolean udCycleBFS(List<List<Integer>> adj, int start){
        boolean[] visited = new boolean[adj.size()];
        Queue<cyclePair> q = new LinkedList<>();
        q.offer(new cyclePair(start, -1));
        visited[start] = true;
        while(!q.isEmpty()){
            cyclePair temp = q.poll();
            for(Integer i : adj.get(temp.node)){
                if(!visited[i] && i!= temp.parent){
                    visited[i] = true;
                    q.offer(new cyclePair(i, temp.node));
                } else if (visited[i] && i!= temp.node) {
                    return true;
                }
            }
        }
        return false;
    }
    //detect cycle in undirected graph using DFS Traversal
    static boolean udCycleDFS(List<List<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        for(int i=0; i<visited.length; i++){
            if(!visited[i]){
                if(udCycleDFSUtil(i, -1, visited, adj)) return true;
            }
        }
        return false;
    }
    static boolean udCycleDFSUtil(int start, int parent, boolean[] visited, List<List<Integer>> adj){
        visited[start] = true;
        for(Integer i : adj.get(start)){
            if(!visited[i]){
                if(udCycleDFSUtil(i, start, visited, adj)) return true;
            }
            else if(i!=parent) return true;
        }
        return false;
    }
    static List<List<Integer>> adjMat2List(int[][] matrix){
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] == 1 && i!=j){
                    adj.get(i).add(j);
                    adj.get(j).add(i);
                }
            }
        }
        return adj;
    }
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> myList = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
    }
}
