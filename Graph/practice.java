import java.util.*;
public class practice{
    public static  class Edge{
        int v =0 , w = 0;
        Edge(int v , int w){
            this.v = v;
            this.w = w;
        }
    }

    public static void topologicalOrder(ArrayList<Edge>[] graph[]){
        int N = graph.length;
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0;i<N;i++){
            if(!vis[i]){
                dfs_topo(graph ,i , vis ,ans);
            }
        }
    }
    public static void dfs_topo(ArrayList<Edge>[] graph ,int src , boolean[] vis , ArrayList<Integer> ans){
        vis[src] = true;
        for(Edge e : graph[src]){
            if(!vis[e.v]){
                dfs_topo(graph , e.v , vis , ans);
            }

            
        }
        ans.add(src);
    }
    
    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph){
        int N = graph.length;
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> que = new LinkedList<>();
        int[] indegree = new int[N];
        for(int i = 0 ;i< N;i++){
            for(Edge e: graph[i]){
                indegree[e.v]++;
            }
        }
        for(int i = 0 ;i<N;i++){
            if(indegree[i]==0){
                que.addLast(i);
            }
        }
        while(que.size()!=0){
            int rvtx = que.removeFirst();
            ans.add(rvtx);
            for(Edge e:graph[rvtx]){
                if(--indegree[e.v]==0){
                    que.addLast(e.v);
                }
            }
        }
        if(ans.size()!=0){
            ans.clear();
        }
        return ans;

    }
}