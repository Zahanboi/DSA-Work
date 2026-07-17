import java.util.*;

class Graphs{
    public class graphInit{
        public class Edge{
            int src;
            int dest;
            int weight = 0;
            Edge(int src, int dest, int weight){ // if no weight is given then it is 0 by default
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }
        }

    private ArrayList<ArrayList<Integer>> adj; // space complexity O(V+E)
    private int[][] adjMatrix;// space complexity O(V^2)
    private ArrayList<ArrayList<Edge>> edgeList; // space complexity O(V+E)
        graphInit(int v){
                adj = new ArrayList<ArrayList<Integer>>(v);
                for(int i=0; i<v; i++){
                    adj.add(new ArrayList<Integer>());
                }
                edgeList = new ArrayList<ArrayList<Edge>>();
                for(int i=0; i<v; i++){
                    edgeList.add(new ArrayList<Edge>());
                }
                adjMatrix = new int[v][v];
                for(int i=0; i<v; i++){
                    for(int j=0; j<v; j++){
                        adjMatrix[i][j] = 0;
                    }
                }
                for(int i=0; i<v; i++){// dont give reverse direction edges for directed graph
                    System.out.println("Enter the number of edges for vertex " + i);
                    int edges = new Scanner(System.in).nextInt();
                    for(int j=0; j<edges; j++){
                        System.out.println("Enter the edge -" + j + " for vertex " + i);
                        int edge = new Scanner(System.in).nextInt();
                        adj.get(i).add(edge); //time complexity O(1) to link the edge to the vertex or look for neighboring vertices
                        adjMatrix[i][edge] = 1;// time complexity O(1) to link the edge to the vertex or look for neighboring vertices and O(k) to look for all neighboring vertices where k is the number of edges for that vertex
                        System.out.println("Enter the weight of the edge:");
                        int weight = new Scanner(System.in).nextInt();
                        edgeList.get(i).add(new Edge(i, edge, weight)); // time complexity O(1) to link the edge to the vertex or look for neighboring vertices
                    }
                }

        }

        public void printGraph(){
            for(int i = 0; i<adj.size(); i++){
                System.out.print(i + "->");
                for (int j = 0; j < adj.get(i).size(); j++) {
                    System.out.print("(" + adj.get(i).get(j) + " " + ")");
                }
                System.out.println();
            }
        }
        public void printAdjMatrix(){
            for(int i = 0; i<adjMatrix.length; i++){
                for(int j = 0; j<adjMatrix[i].length; j++){
                    System.out.print(adjMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        public void printEdgeList(){
            for(int i = 0; i<edgeList.size(); i++){
                System.out.print(i + "->");
                for (int j = 0; j < edgeList.get(i).size(); j++) {
                    System.out.print("(" + edgeList.get(i).get(j).src + " " + edgeList.get(i).get(j).dest + ")");
                }
                System.out.println();
            }
        }

        public void bfs(){
            boolean[] visited = new boolean[adj.size()];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while(queue.size() > 0){
                int node = queue.poll();
                if(!visited[node]){
                    visited[node] = true;
                    System.out.print(node + " ");
                    for(int i = 0; i<adj.get(node).size(); i++){
                        queue.add(adj.get(node).get(i));
                    }
                }
            }
            System.out.println();
            boolean[] newVisited = new boolean[adj.size()];
            dfs(0, newVisited);
        }

        public void dfs(int node ,boolean[] vis){
            vis[node] = true;
            System.out.print(node + " ");
            for(int i = 0; i<adj.get(node).size(); i++){
                if(!vis[adj.get(node).get(i)]){
                    dfs(adj.get(node).get(i), vis);
                }
            }
        }

        public boolean hasPath(int src, int dest, boolean[] vis){
            if(src == dest){
                return true;
            }
            vis[src] = true;
            for(int i = 0; i<adj.get(src).size(); i++){
                if(!vis[adj.get(src).get(i)]){
                    if(hasPath(adj.get(src).get(i), dest, vis)){
                        return true;
                    }
                }
            }
            return false;

        }

        public void traverseConnectedComponents(){
            boolean[] visited = new boolean[adj.size()];
            for(int i = 0; i<adj.size(); i++){
                if(!visited[i]){
                    dfs(i, visited);
                    System.out.println();
                }
            }
        }

        public boolean undirectedCycleDetect(int parent, int node, boolean[] vis){
            //if using dfs vertex is visited and not parent then cycle exists not use bfs because we need to check for parent and bfs does not have parent concept
            vis[node] = true;
            for(int i = 0; i<adj.get(node).size(); i++){
                if(vis[adj.get(node).get(i)] && adj.get(node).get(i) != parent){
                    return true;
                }
                if(!vis[adj.get(node).get(i)]){
                    if(undirectedCycleDetect(node, adj.get(node).get(i), vis)){
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean directedCycleDetect(int node, boolean[] vis, boolean[] recStack){
            //if using dfs vertex is visited and in recursion stack then cycle exists not use bfs because we need to check for recursion stack and bfs does not have recursion stack concept
            vis[node] = true;
            recStack[node] = true;
            for(int i = 0; i<adj.get(node).size(); i++){
                if(!vis[adj.get(node).get(i)]){
                    if(directedCycleDetect(adj.get(node).get(i), vis, recStack)){
                        return true;
                    }
                } else if(recStack[adj.get(node).get(i)]){
                    return true;
                }
            }
            recStack[node] = false; //backtracking step
            return false;
        }

        public boolean bipartiteGraphDetect(int node, int[] color){
           if(color[node] == -1){
                color[node] = 1;
            }
            for(int i = 0; i<adj.get(node).size(); i++){
                if(color[adj.get(node).get(i)] == -1){
                    color[adj.get(node).get(i)] = 1 - color[node];
                    if(!bipartiteGraphDetect(adj.get(node).get(i), color)){
                        return false;
                    }
                } else if(color[adj.get(node).get(i)] == color[node]){
                    return false;
                }
            }
            return true;
        }

        public void topologicalSort(){// only for directed acyclic graph if call for cyclic graph then it will give wrong answer or will go into infinite loop there is no concept of topological sort for cyclic graph because there is no order of execution for cyclic graph
            boolean[] visited = new boolean[adj.size()];
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i<adj.size(); i++){
                if(!visited[i]){
                    topologicalSortUtil(i, visited, stack);// dfs
                }
            }
            while(!stack.isEmpty()){
                System.out.print(stack.pop() + " ");
            }
        }

        public void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack){
            visited[node] = true;
            for(int i = 0; i<adj.get(node).size(); i++){
                if(!visited[adj.get(node).get(i)]){
                    topologicalSortUtil(adj.get(node).get(i), visited, stack);
                }
            }
            stack.push(node);// add after loop so that the node is added after all its neighbors are visited so that least dependent node is added first and most dependent node is added last so that the order is correct
        }

        public void kahnsAlgorithm() {//bfs form of topological sort and also a check for cycle in a directed graph via bfs
            int n = adj.size();
            int[] inDegree = new int[n];
            
            // 1. Calculate the in-degree of every node
            for (int i = 0; i < n; i++) {
                for (int neighbor : adj.get(i)) {
                    inDegree[neighbor]++;// building this makes sure that we do not need to use a visited array since one thing graph is directed secondly if a node exist in a cycle this array will make the loop exist early before finishing all nodes so no need of vis array
                }
            }// cannot just use this order since some can have same indegrees and we need to know which comes in which order so we traverse the graph 
            
            // 2. Queue to store nodes with an in-degree of 0
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            
            List<Integer> topoSort = new ArrayList<>();
            int visitedCount = 0;
            
            // 3. Process the queue
            while (!queue.isEmpty()) {
                int node = queue.poll();
                topoSort.add(node);
                visitedCount++;
                
                // 4. Decrease the in-degree of all neighbors by 1
                for (int neighbor : adj.get(node)) {
                    inDegree[neighbor]--;
                    
                    // 5. If in-degree becomes 0, add to queue
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
            
            // 6. Check for cycles
            if (visitedCount != n) {
                System.out.println("Graph contains a cycle! Topological sort not possible.");
            } else {
                System.out.println("Topological Order: " + topoSort);
            }
        }

        public void allPaths(int src, int dest, boolean[] vis, List<Integer> path) {
            vis[src] = true;
            path.add(src);
            
            if (src == dest) {
                System.out.println(path);
            } else {
                for (int neighbor : adj.get(src)) {
                    if (!vis[neighbor]) {
                        allPaths(neighbor, dest, vis, path);
                    }
                }
            }
            
            // Backtrack
            path.remove(path.size() - 1); // imp steps as we may need to go to tha tpath again from another node so we need to remove the last node from the path and mark it as unvisited
            vis[src] = false;
        }

        public void dijkstra(int src) { // TC: O((V + E) log V) where V is the number of vertices and E is the number of edges, SC: O(V) for distance array and priority queue if use both priority queue and vis array then also SC: O(V) for distance array and vis array but more stricter because here after traversing all neighbours to a node we mark it as visited and will skip it if it comes again in the priority queue
            int n = adj.size();
            int dist[] = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;// meaning we can reach this first so settign it to 0

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{src, dist[src]});

            while(!pq.isEmpty()){
                int[] current = pq.poll();
                int vertex = current[0];
                int dist_src = current[1];
            //      if (visited[vertex]) {
            //     continue;
            // }

            // // 2. Mark it as finalized. We will NEVER process this vertex again.
            // visited[vertex] = true;
                if(dist_src > dist[vertex]) continue; // because we may have already found a shorter path to this vertex, so we can skip processing it and sine we are using pq so shorter paths will be processed first and longer paths will be processed later so we can skip processing longer paths if we have already found a shorter path to this vertex
                for(Edge neighbour : edgeList.get(vertex)){
                    if(dist_src + neighbour.weight < dist[neighbour.dest]){
                        dist[neighbour.dest] = dist_src + neighbour.weight;
                        pq.offer(new int[]{neighbour.dest, dist[neighbour.dest]});
                    }
                }
            }
            System.out.println("Shortest distances from source " + src + ":");
            for(int i = 0; i < n; i++){
                System.out.println("Vertex " + i + ": " + dist[i]);
            }
            // this dijkstra will give right answer for negative weights as well because of pq but if found a smaller path due to negative edge then it will process all its children again leading to a worst case TC of O(2^V)
            // using a normal vis array to mark visited nodes will not give right answer for negative weights as it will not process the children of the node again if found a smaller path to it due to negative edge leading to wrong answer
        }

        public void bellmanFord(int src) { // TC: O(V*E) where V is the number of vertices and E is the number of edges, SC: O(V) for distance array
            int n = adj.size();
            int dist[] = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            for (int i = 1; i < n; i++) {
                for (int u = 0; u < n; u++) {
                    for (Edge edge : edgeList.get(u)) {
                        int v = edge.dest;
                        int weight = edge.weight;
                        if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                            dist[v] = dist[u] + weight;
                        }
                    }
                }
            }

            // Check for negative-weight cycles because negative adding will get smaller until - infinity so always detect
            for (int u = 0; u < n; u++) {
                for (Edge edge : edgeList.get(u)) {
                    int v = edge.dest;
                    int weight = edge.weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        System.out.println("Graph contains a negative-weight cycle");
                        return;
                    }
                }

            }

            System.out.println("Shortest distances from source " + src + ":");
            for (int i = 0; i < n; i++) {
                System.out.println("Vertex " + i + ": " + dist[i]);
            }
            //brute force approach
        }
        
        public void prim(int src) { // TC: O((V + E) log V) where V is the number of vertices and E is the number of edges, SC: O(V) for distance array and priority queue
            int n = adj.size();
            int dist[] = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{src, dist[src]});

            boolean[] inMST = new boolean[n];

            while(!pq.isEmpty()){
                int[] current = pq.poll();
                int vertex = current[0];
                if (inMST[vertex]) {
                    continue;
                }
                inMST[vertex] = true;

                for(Edge neighbour : edgeList.get(vertex)){
                    if(!inMST[neighbour.dest] && neighbour.weight < dist[neighbour.dest]){
                        dist[neighbour.dest] = neighbour.weight;
                        pq.offer(new int[]{neighbour.dest, dist[neighbour.dest]});
                    }
                }
            }

            System.out.println("Minimum Spanning Tree weights from source " + src + ":");
            for(int i = 0; i < n; i++){
                System.out.println("Vertex " + i + ": " + dist[i]);
            }
        }


    }

    public static void main(String[] args) {
        System.out.println("Graphs");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int v = sc.nextInt();
        Graphs g = new Graphs();
        graphInit graph = g.new graphInit(v);
        graph.printGraph();
        graph.printAdjMatrix();
        graph.printEdgeList();
        graph.bfs();//O(V+E) time complexity to traverse the graph using BFS or DFS
        System.out.println();
        if(graph.hasPath(0, v - 1, new boolean[v])){
            System.out.println("Path exists between 0 and " + (v - 1));
        } else {
            System.out.println("No path exists between 0 and " + (v - 1));
        }
        graph.traverseConnectedComponents();
        if(graph.undirectedCycleDetect(-1, 0, new boolean[v])){
            System.out.println("Cycle exists in the graph");
        } else {
            System.out.println("No cycle exists in the graph");
        }
        if(graph.directedCycleDetect(0, new boolean[v], new boolean[v])){
            System.out.println("Cycle exists in the directed graph");
        } else {
            System.out.println("No cycle exists in the directed graph");
        }
        int [] color = new int[v];
        Arrays.fill(color, -1);
        if(graph.bipartiteGraphDetect(0, color)){
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not bipartite");
        }
        
        graph.topologicalSort();
        System.out.println();
        graph.kahnsAlgorithm();
        graph.dijkstra(0);
    }
} 
