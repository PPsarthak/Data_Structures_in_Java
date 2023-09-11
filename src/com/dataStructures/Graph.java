package com.dataStructures;

import java.util.*;

class Graph {
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
    static int islands(char[][] grid){
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1' && !visited[i][j]){
                    counter++;
                    myBFS(i, j, visited, grid);
                }
            }
        }
        return counter;
    }
    static class XY{
        int i;
        int j;

        XY(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    static void myBFS(int x, int y, boolean[][] visited, char[][] grid){
        Queue<XY> q = new LinkedList<>();
        q.offer(new XY(x,y));
        visited[x][y] = true;
        while (!q.isEmpty()){
            XY node = q.poll();

            /* Checking the neighbours  */
            int i = node.i;
            int j = node.j;

            /* Top */
            if(i>0 && grid[i-1][j] == '1' && !visited[i-1][j]) {
                visited[i - 1][j] = true;
                q.offer(new XY(i - 1, j));
            }
            /* Bottom */
            if(i<grid.length-1 && grid[i+1][j] == '1' && !visited[i+1][j]) {
                visited[i+1][j] = true;
                q.offer(new XY(i + 1, j));
            }
            /* Left */
            if(j>0 && grid[i][j-1] == '1' && !visited[i][j-1]) {
                visited[i][j-1] = true;
                q.offer(new XY(i, j - 1));
            }
            /* Right */
            if(j< grid[0].length-1 && grid[i][j+1] == '1' && !visited[i][j+1]) {
                visited[i][j+1] = true;
                q.offer(new XY(i, j + 1));
            }
        }
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
