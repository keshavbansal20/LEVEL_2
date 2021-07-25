import java.util.*;

public class l004_algo {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    static int[] par , size;
    public static int findPar(int u ){
        return par[u] == u?(par[u]=findPar(par[u]));
    }
    
    public static void union(int p1 , int p2){
        if(size[p1]<size[p2]){
            par[p1] = p2;
            size[p2]+=size[p1];
        } else {
            par[p2] = p1;
            size[p1]+=size[p2];
        }

    }

    public static void unionFind(int[][] edges , ArrayList<Edge>[] graph) {
        
        int N = graph.length;
        par = new int[N];
        size = new int[N];
        for(int i = 0 ; i< N;i++){
            par[i] = i;
            size[i] = i;
        }
        for(int[]e: edges){
            int u = e[0] , v=e[1] , w=e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
            if(p1!=p2){
                union(p1, p2);
                addEdge(graph , u , v , w);
            }
        }
    }

    public static void kruskalAlgo(int[][] edges , int N){
        Arrays.sort(edges , (a , b) -> {
            return a[2]-b[2];
        });
        // int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i =0 ;i<N;i++){
            graph[i]=new ArrayList<>();

        }
        unionFind(edges , graph);

    }

    
}
