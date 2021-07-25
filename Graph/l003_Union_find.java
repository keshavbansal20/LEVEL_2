import java.util.ArrayList;
import java.util.Arrays;

public class l003_Union_find {
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

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] par, size;

    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);

        // return par[u] == u ? u : par[u] = find(par[u]);
    }

    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {{u1,v1,w1},{u2,v2,w2}...}
    public static void unionFind(int[][] edges) {
        int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }

    public int mrPresident(int[][] edges , int N , int K){
        par = new int[N+1];
        for(int i = 0 ;i<=N;i++){        
            par[i] = i;
        }
        Arrays.sort(edges , (a, b)->{
            return a[2]-b[2];
        });

        ArrayList<Integer> roads = new ArrayList<>();
        int components = N , mcost = 0;
            for(int[] e:edges){
                int u = e[0] , v=e[1] , w=e[2];
                int p1 = findPar(u) , p2 = findPar(v);
                if(p1!=p2){
                    par[p1] = p2;
                    components--;
                    mcost +=w;
                    roads.add(w);
                }
            }

        if(components>1){
            return -1;
        }

        int superroad = 0;
        for(int  i =  roads.size()-1;i>=0;i--){
            if(mcost<=K)
                break;
            mcost = mcost - roads.get(i)+1;
            superroad++;
        }
        return mcost<=K?superroad:-1;

    }

}