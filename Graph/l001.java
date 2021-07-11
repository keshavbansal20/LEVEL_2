import java.util.ArrayList;
import java.util.LinkedList;
public class l001{
    public static class Edge{
        int v = 0 , w = 0;
        Edge(int v , int w){
            this.v = v;
            this.w = w;
        }
    }
    public static void addEdge(ArrayList<Edge>[] graph , int u , int v , int wt ){
        graph[u].add(new Edge( v , wt));
        graph[v].add(new Edge( u , wt));

    }


    public static int findEdge(ArrayList<Edge>[] graph , int u , int v){
        ArrayList<Edge> list = graph[u];
        for(int i= 0 ; i < list.size();i++){
            Edge e = list.get(i);
            if(e.v==v){
                return i;
            }
        }
        return -1;
    }
    public static void removeEdge(ArrayList<Edge>[] graph ,int u , int v){
        int idx = findEdge(graph , u , v);
        graph[u].remove(idx);

        idx = findEdge(graph , v , u);
        graph[v].remove(idx);

    }

    public static boolean dfs_findPath(ArrayList<Edge>[] graph , int src , int dest , boolean[] vis){
        if(src==dest) return true;
        vis[src] = true;
        boolean res= false;
        for(Edge e:graph[src]){
            if(!vis[e.v]){ //wo node visit nhi h jab jana h uspr                                       
                res = res || dfs_findPath(graph ,e.v , dest , vis); // res pehle true hojayega isliye aage dfs run nhi hoga
            }
        }
        return res;
    }

    

    public static int printAllPath(ArrayList<Edge>[] graph , int src , int dest , String psf , int wsf , boolean[] vis){
        if(src==dest){
            System.out.println(psf+dest+" @ "+wsf ); //add dest in psf in base case   
            return 1;
        }
        vis[src] = true;
        int count=0;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                // System.out.print(e.v+" ------>"+count+" ");
               count+= printAllPath(graph, e.v , dest , psf+src , wsf+e.w , vis);
            }
        }
        vis[src]= false;

        return count;
    }

    public static void dfs_GCC(ArrayList<Edge>[] graph, int src, boolean[] vis) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_GCC(graph, e.v, vis);
        }
    }

    // GCC
    // O(E + V)
    public static void getConnectedComponent(ArrayList<Edge>[] graph) {
        int components = 0, N = graph.length;
        boolean[] vis = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                components++;
                dfs_GCC(graph, i, vis);
            }
        }

        System.out.println(components);
        
    }

    


    public static void display(ArrayList<Edge>[] graph){
        int N = graph.length;
        for(int i= 0 ; i < N;i++){
            System.out.print(i+" -> ");
            for(Edge e:graph[i]){
                System.out.print("("+e.v+", "+e.w+") ");

            }
            System.out.println();
        }
    }
    public static void constructGraph(){
        int N= 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for(int i =0 ;i < N ; i++){
            graph[i] = (new ArrayList<>());
        }
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);
        

        boolean[] vis = new boolean[N];
        // display(graph);
        // System.out.println(findEdge(graph, 3, 4));
        //  System.out.println(dfs_findPath(graph, 0, 6, vis));
        // int res = printAllPath(graph, 0, 6, "", 0, vis);
        // System.out.println(res);
        // getConnectedComponent(graph);
        // BFS_forCycle(graph , 0 , vis);
        BFS_withoutCycle(graph , 0 , vis);
    }

    //O(E)
    public static void BFS_forCycle(ArrayList<Edge>[] graph , int src , boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        int level = 0;
        boolean iscycle = false;
        
        while(que.size()!=0){
            int size = que.size();
            System.out.print("Min No Edges: " + level + " -> ");
            while(size-->0){
                int rvtx = que.removeFirst();

                //for check cycle
                if(vis[rvtx]){
                    iscycle = true;
                    continue;
                }
                System.out.print(rvtx+" ,");
                vis[rvtx] = true;
                for(Edge e:graph[rvtx]){
                    if(!vis[e.v])
                        que.addLast(e.v);
                }
            }
            System.out.println();
            level++;
        }
    }


    //o(v)
    public static void BFS_withoutCycle(ArrayList<Edge>[] graph , int src , boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        int level = 0;
        que.addLast(src);
        vis[src]=true; //mark visired
        while(que.size() !=0){
            int size = que.size();
            System.out.print("Min No. of Edges: "+ level +  " -> " );
            while(size-- >0){
                int rvtx = que.removeFirst();
                System.out.print(rvtx+ ", ");
                for(Edge e:graph[rvtx]){
                    if(!vis[e.v]){
                        vis[e.v]=true;
                        que.addLast(e.v);
                    }
                }
                System.out.println();
                level++;
            }
        }
        
    }
    public static void main(String[] args){
        constructGraph();
    }
}