public class graph {
    public static class Edge{
        int src ;
        int nbr;
        int wt;
        Edge(int src , int nbr , int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    public static boolean hasPath(ArrayList<Edge>[] graph , int src , int dest , boolean[] vis){
        if(src==dest){
            return true;
        }
        
        vis[src]=true;
        for(Edge e:graph[src]){
            int nbr= e.nbr;
            boolean hasNbrPath = false;
            if(vis[nbr]==false){
                hasNbrPath=hasPath(graph , nbr , dest , vis);
            }
            if(hasNbrPath){
                return true;
            }
        }
        return false;
    }

    public static void printAllPaths(ArrayList<Edge>[] graph , int src , int dest ,  String psf , boolean[] vis){
        if(src==dest){
            System.out.println(psf);
            return;
        }
        
        vis[src]=true;
        for(Edge e:graph[src]){
            int nbr= e.nbr;
            if(vis[nbr]==false){
                printAllPaths(graph , nbr , dest ,psf+e.src , vis);
            }

        }
        vis[src]  = false;
    }


    //multisolver
    static String spath;
   static Integer spathwt = Integer.MAX_VALUE;
   static String lpath;
   static Integer lpathwt = Integer.MIN_VALUE;
   static String cpath;
   static Integer cpathwt = Integer.MAX_VALUE;
   static String fpath;
   static Integer fpathwt = Integer.MIN_VALUE;
   static PriorityQueue<Pair> pq = new PriorityQueue<>();
   public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k, String psf, int wsf) {
      if(src==dest){
          if(wsf<spathwt){
              spathwt = wsf;
              spath=spath+wsf+"";
          }
          if(wsf>lpathwt){
              lpathwt = wsf;
              lpath = lpath+wsf+"";
          }
          if(wsf>criteria && wsf<cpathwt){
              cpathwt = wsf;
              cpath = cpath+wsf+"";
          }
          if(wsf<criteria&&wsf>fpathwt){
                fpathwt = wsf;
                fpath = fpath+wsf+"";
          }
          if(pq.size()>k){
              Pair p = new Pair()
          }
      }
      vis[src] = true;
      for(Edge e:graph[src]){
          int nbr = e.nbr;
          if(vis[nbr]==false){
              multisolver(graph, nbr, dest, visited, criteria, k, psf+e.nbr, wsf+e.wt);
          }
      }
      vis[src] = false;
   }
}
