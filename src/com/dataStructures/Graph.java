package com.dataStructures;

import java.util.*;

class Graph {
    /**
     * @param start starting vertex of traversal
     * @param n size of adj list
     * @param adj adjacency list of graph
     * @return a list of bfs traversal
     * @TimeComplexity O(V + E) where V is the number of vertices and E is the number of edges.
     *                 The algorithm explores each vertex and each edge once.
     *@SpaceComplexity  O(V): visited array.
     */
    static List<Integer> bfs(int start, int n, List<List<Integer>> adj){
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[n]; // n = no of vertices / nodes
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()){
            //the next 2 lines are a part of "traversing a node"
            //while "traversing a node", we poll it from the queue and add it to our ans

            int node = q.poll();
            bfs.add(node);

            for (Integer i : adj.get(node)){
                //only if the node is not visited, we will add it to the queue and mark as visited
                if(!visited[i]){
                    //the next 2 lines are a part of "visiting a node"
                    //while "visiting a node", we offer it to a queue and then mark it as visited
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        return bfs;

        //when using bfs, there are 2 operations: traversing and visiting
        //traversing happens just after a while loop and visiting inside for loop
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
            //here, unlike bfs where traversing and visiting is kept apart,
            //traversing and visiting is done at the same time
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
    static int componentDFS(List<List<Integer>> adj){
        int count = 0;
        boolean[] visited = new boolean[adj.size()];
        for(int i=0; i< adj.size(); i++){
            if(!visited[i]){
                componentDFSUtil(i, visited, adj);
                count++;
            }
        }
        return count;
    }
    static void componentDFSUtil(int node, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;
        for (Integer i : adj.get(node)){
            if(!visited[i]){
                componentDFSUtil(i, visited, adj);
            }
        }
    }
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
    /**
     * checks whether a graph is bipartite or not
     * @param graph adj matrix of the graph
     * @return true if graph is bipartite else false
     */
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
    /**
     * Detects cycles in an undirected graph using Breadth-First Search (BFS).
     * @param adj     The adjacency list representation of the graph.
     * @param start   The starting vertex for the cycle detection.
     * @return        True if a cycle is detected, false otherwise
     * @TimeComplexity   O(V + E) where V is the number of vertices and E is the number of edges.
     *                   The algorithm explores each vertex and each edge once.
     * @SpaceComplexity  O(V): visited array.
     */
    static boolean udCycleBFS(List<List<Integer>> adj, int start){
        boolean[] visited = new boolean[adj.size()];
        Queue<cyclePair> q = new LinkedList<>();
        q.offer(new cyclePair(start, -1));
        visited[start] = true;
        while(!q.isEmpty()){
            cyclePair temp = q.poll();
            for(Integer i : adj.get(temp.node)){
                if(!visited[i]){
                    visited[i] = true;
                    q.offer(new cyclePair(i, temp.node));
                } else if (i!= temp.parent) {
                    return true;
                }
            }
        }
        return false;
    }
    static class cyclePair{
        int node;
        int parent;

        public cyclePair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    /**
     * Detects cycle in undirected graph using DFS Traversal
     * @param adj adjacency list of the graph
     * @return whether the undirected graph contains cycle
     * @TimeComplexity O(V+E): similar to DFS
     * @SpaceComplexity O(V): visited array
     */
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
    static boolean dCycle(List<List<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        boolean[] pathVisited = new boolean[adj.size()];
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                if(dfsCheck(i, adj, visited, pathVisited)) return true;
            }
        }
        return false;
    }
    static boolean dfsCheck(int start, List<List<Integer>> adj, boolean[] visited, boolean[] pathVisited){
        visited[start] = true;
        pathVisited[start] = true;

        for (Integer i : adj.get(start)){
            if(!visited[i]){
                if(dfsCheck(i, adj, visited, pathVisited)) return true;
            }
            else if(pathVisited[i]) return true;
        }

        pathVisited[start] = false;
        return false;
    }
    /**
     * @DataStructures Uses a stack, along with
     * @param adj adjacency list of the graph
     * @return one of the topological order of the graph
     * @TimeComplexity O(V+E) i.e., similar to DFS algorithm
     * @SpaceComplexity O(N): for stack + O(N): visited array + O(N): auxiliary space
     */
    static List<Integer> topoSort(List<List<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> myStack = new Stack<>();

        for(int i=0; i<visited.length; i++){
            if(!visited[i])
                dfsTopo(i, adj, visited, myStack);
        }

        List<Integer> dist = new ArrayList<>();
        while(!myStack.isEmpty()){
            dist.add(myStack.pop());
        }
        return dist;
    }

    private static void dfsTopo(int start, List<List<Integer>> adj, boolean[] visited, Stack<Integer> myStack) {
        visited[start] = true;
        for(Integer i : adj.get(start)){
            if(!visited[i]) dfsTopo(i, adj, visited, myStack);
        }
        myStack.push(start);
    }

    /**
     * Topological Sort using BFS - Kahn's Algorithm: Vertex with 0 indegree should come first
     * @DataStructures Queue: BFS Traversal
     * @param adj adjacency list of the graph
     * @return a list of topological sorted order using BFS
     * @TimeComplexity O(V+E): same as BFS
     * @SpaceComplexity O(V): for the indegree array
     */
    static List<Integer> kahnAlgo(List<List<Integer>> adj){
        int[] inDegree = new int[adj.size()];
        for(int i=0; i< adj.size(); i++){
            for(int j : adj.get(i)){
                inDegree[j]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if(inDegree[i] == 0) q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()){
            int temp = q.poll();
            topo.add(temp);

            //after adding the vertex to dist, reduce the edges (indegree) of its neighbors by 1
            for(int i : adj.get(temp)){
                inDegree[i]--;
                if(inDegree[i] == 0) q.offer(i);
            }
        }
        return topo;
    }
    /**
     * DOES NOT WORK FOR GRAPH WITH NEGATIVE WEIGHTS!
     * Dijkstra's Algo is used to find min weighted path from source to all nodes
     * @DataStructures Priority Queue instead of Queue since we need to pop node with the smallest distance first
     * @param adj weighted edge list of graph
     * @param source vertex from which min dist of all nodes is stored in array
     * @return an array containing the min dist to reach a node from source
     * @TimeComplexity O(E*logV): additional logV in multiplication bcoz of the use of PQ
     * @SpaceComplexity O(N): PQ + O(N): dist array
     */
    static int[] dijkstraAlgo(List<List<List<Integer>>> adj, int source){
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((x, y)->x.distance - y.distance);

        int[] dijkstra = new int[adj.size()];
        Arrays.fill(dijkstra, (int)(1e9));

        dijkstra[source] = 0;
        pq.offer(new Pair(source, 0));

        while (!pq.isEmpty()){
            Pair temp = pq.poll();
            int currDist = temp.distance;
            int currNode = temp.node;

            for(int i=0; i<adj.get(currNode).size(); i++){
                int adjNode = adj.get(currNode).get(i).get(0);
                int edgeWt = adj.get(currNode).get(i).get(1);

                if(currDist + edgeWt < dijkstra[adjNode]){
                    dijkstra[adjNode] = currDist + edgeWt;
                    pq.offer(new Pair(adjNode, dijkstra[adjNode]));
                }
            }
        }
        return dijkstra;
    }
    static class Pair {
        int node;
        int distance;

        public Pair(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * USED ONLY IN ** DIRECTED ** GRAPHS
     * used to find the shortest path in directed graph + detect a negative weight cycle
     * @param V no of nodes
     * @param edges weighted edge list
     * @param source source node
     * @return array of min distances of all nodes from source node
     * @TimeComplexity O(V*E): V iterations for relaxation where we travel/relax all edges
     * @SpaceComplexity O(V): distance array
     */
    // Striver G-41: https://youtu.be/0vVofAhAYjc?feature=shared&t=304
    static int[] bellmanFord(int V, List<List<Integer>> edges, int source){
        int[] dist = new int[V];
        Arrays.fill(dist, (int)(1e9));
        dist[source] = 0;
        
        //relax for N-1 iterations: N = number of vertices
        for (int i = 0; i < V-1; i++) {
            for(List<Integer> list : edges){
                int u = list.get(0);
                int v = list.get(1);
                int w = list.get(2);

                //if u has been previously reached and now distance is min
                if(dist[u] != (int)(1e9) && dist[u] + w < dist[v]){
                    dist[v] = dist[u]+w;
                }
            }
        }

        //if there is a relaxation in this extra iteration
        //then there is a net-negative weighted cycle in the graph
        for(List<Integer> list : edges){
            int u = list.get(0);
            int v = list.get(1);
            int w = list.get(2);

            if(dist[u] != (int)(1e9) && dist[u] + w < dist[v]){
                //cycle found
                return new int[]{-1};
            }
        }
        return dist;
    }

    /**
     * Prim's Algorithm is a greedy algorithm to find min spanning tree
     * @param V number of vertices
     * @param adj this is the WEIGHTED EDGE LIST:= so we used V to denote no of nodes
     * @return edge list (w/o the weights) of the min spanning tree
     * @TimeComplexity O(E*logE) + O(E*logE)
     * @SpaceComplexity O(V):= visited array + O(E):= Priority Queue
     * @DataStructures Priority Queue
     */
    static List<List<Integer>> primAlgo(int V, List<List<List<Integer>>> adj){
        PriorityQueue<PrimPair> pq = new PriorityQueue<PrimPair>((x,y)->x.weight - y.weight);
        boolean[] visited = new boolean[V];
        List<List<Integer>> mst = new ArrayList<>();
        //assuming src = 0
        pq.offer(new PrimPair(0,0,-1));
        int sum = 0;
        while(!pq.isEmpty()){
            //if you do this then the space to store the object is reduced
            //no you are just using space to store 2 int
            //on the other hand, you would use Object + 2 int if you had polled first
            int node = pq.peek().node;
            int weight = pq.peek().weight;
            int parent = pq.peek().parent;
            pq.poll();

            if(!visited[node]){
                visited[node] = true;
                //only if current node is not the start node bcoz we don't want to add source node immediately
                if(parent!=-1){
                    List<Integer> edge = new ArrayList<>();
                    edge.add(node);
                    edge.add(parent);
                    mst.add(edge);
                    sum += weight;
                }
                //check for the neighbours of current node
                for(int i=0; i<adj.get(node).size(); i++){
                    int adjNode = adj.get(node).get(i).get(0);
                    int edgeWt = adj.get(node).get(i).get(1);
                    //only if adjacent nodes are not visited, add them to PQ, otherwise leave it
                    if(!visited[adjNode]) pq.offer(new PrimPair(adjNode, edgeWt, node));
                }
            }

        }
//        System.out.println("The sum of MST is: " + sum);
        return mst;
    }
    static class PrimPair{
        int node;
        int weight;
        int parent;

        public PrimPair(int node, int weight, int parent) {
            this.node = node;
            this.weight = weight;
            this.parent = parent;
        }
    }
    static class KruskalPair implements Comparable<KruskalPair>{
        int node;
        int parent;
        int weight;

        KruskalPair(int node, int parent, int weight){
            this.node = node;
            this.parent = parent;
            this.weight = weight;
        }
        public int compareTo(KruskalPair kruskalPair){
            return this.weight-kruskalPair.weight;
        }
    }
    static List<List<Integer>> kruskalAlgo(int V, List<List<List<Integer>>> adj){
        List<KruskalPair> edges = new ArrayList<>();
        for(int i=0; i<V; i++){
            for (int j = 0; j < adj.get(i).size(); j++) {
                int adjNode = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                edges.add(new KruskalPair(i, adjNode, wt));
            }
        }
        DisjointSet ds = new DisjointSet(V);
        Collections.sort(edges);
        int mstWeight = 0;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            int wt = edges.get(i).weight;
            int u = edges.get(i).node;
            int v = edges.get(i).parent;
            if (ds.findUParent(u) != ds.findUParent(v)) {
                mstWeight += wt;
                ds.unionBySize(u, v);
            }
        }
        return ans;
    }
    static int[] insertTime;
    static int[] lowestTime;
    static List<List<Integer>> tarjanAns = new ArrayList<>();
    static List<List<Integer>> tarjan(List<List<Integer>> adj){
        insertTime = new int[adj.size()];
        lowestTime = new int[adj.size()];
        boolean[] visited = new boolean[adj.size()];

        //given graph can contain 1 or more component
        //if it contains only 1, you can directly call dfs() but here
        //I have assumed that it has 1+ component hence used a for loop
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                tarjanDFS(i, -1, visited, adj);
            }
        }
        return tarjanAns;
    }
    static int time = 1;
    static void tarjanDFS(int node, int parent, boolean[] visited, List<List<Integer>> adj){
        visited[node] = true;

        //default values
        lowestTime[node] = time;
        insertTime[node] = time;
        time++;

        //going for neighbours
        for(Integer i : adj.get(node)){
            if(i!=parent && !visited[i]){
                tarjanDFS(i, node, visited, adj);

                //compare the insertTime values of the 2 nodes
                lowestTime[node] = Math.min(lowestTime[node], lowestTime[i]);

                //checking if the lowest > insertTime
                //i.e., if this is the only way to reach this node
                if(lowestTime[i] > insertTime[node]){
                    tarjanAns.add(Arrays.asList(i, node));
                }
            }
            else if(i!=parent && visited[i]){
                //compare the insertTime values of the 2 nodes
                lowestTime[node] = Math.min(lowestTime[node], lowestTime[i]);
            }
        }
    }


    //new codes go above ~ maintain 3 spaces
    static List<List<Integer>> adjMat2List(int[][] matrix){
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++){
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
        int[] nums = {2,2,3,3,3,4};
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        map.remove(3);
        System.out.println(map);

        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.remove(2);
        System.out.println(myList.get(2));
        Set<Integer> set = new HashSet<>();
        String s = "anagram";

        StringBuilder sb = new StringBuilder(s);
        StringBuilder sb2 = new StringBuilder("nagaram");

        sb.compareTo(sb2);
        System.out.println(sb.compareTo(sb2));

    }
}
