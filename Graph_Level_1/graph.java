import java.rmi.ConnectIOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

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
    public static Pair implements Comparable<Pair>{
        int wsf;
        String psf;

        Pair(int wsf , String psf){
            this.wsf = wsf;
            this.psf = psf;
        }
        public int compareTo(Pair o){
            return this.wsf - o.wsf;
        }
    }
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
          if(pq.size()<k){
             pq.add(new pair(wsf , psf));
          }else{
              if(wsf > pq.peek().wsf){
                 pq.remove();
                 pq.add(new Pair(wsf , psf));
              }
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
   // get_connected_comps
   public static void getSingleComp(ArrayList<Edge>[] graph , int src , ArrayList<Integer> list , boolean[] vis){
       list.add(src);
       vis[src]= true;
       for(Edge edge:graph[src]){
           int nbr = edge.nbr;
           if(vis[nbr]==false){
               getSingleComp(graph,nbr, list, vis);
           }
       }
       vis[src]= false;
   }

   public static void getConnectedComps(ArrayList<E> graph ,  ArrayList<ArrayList<Integer>> comps){
       boolean[] vis = new boolean[graph.length];

       for(int v = 0 ; v < graph.length ; v++){
           if(vis[v]==false){
               ArrayList<Integer> scc = new ArrayList<>();
               getSingleComp(graph , v , scc , vis);
               comps.add(scc);
           }
       }
   }


   //is graph connected

   public static void dfs(ArrayList<Edge>[] graph , int src , boolean[] vis){
       vis[src] = true;
       for(Edge edge:graph[src]){
           if(vis[edge.nbr]==false){
               dfs(graph , edge.nbr , vis);
           }
       }
   }

   public static void isGraphConnected(){
       boolean[] vis = new boolean[graph.length];
       dfs(graph , 0 , vis);
       for(int v = 0 ; v < graph.length ; v++){
           if(vis[v]==false){
               System.out.println("false");
               return;
           }
       }
       System.out.println("true");
   }

   //number_of_islands
   public static void dfs(int[][] arr , int i , int j , boolean[][] vis){
       if(i <0 || j <0 || i == arr.length || j==arr[0].length || vis[i][j] == true || arr[i][j] ==1){
           return;
       }
        vis[i][j]= true;
        dfs(arr , i-1 , j , vis);
        dfs(arr , i , j-1 , vis);
        dfs(arr , i+1 , j , vis);
        dfs(arr , i , j+1 , vis);
        
   }
   public static int islands(int[][] arr){
       boolean[][] vis = new boolean[arr.length][arr[0].length];
       int count = 0;
       for(int i = 0 ; i < arr.length ; i++){
           for(int j = 0 ; j < arr[0].length ; j++){
               if(arr[i][j] == 0 && vis[i][j]==false){
                   count++;
                   dfs(arr , i , j , vis);
               }
           }
       }
       return count;
   }

   //Perfect Friends
    public static void dfs(ArrayList<Integer>[] graph  , int src , ArrayList<Integer>list , boolean[] vis){
        vis[src]= true;
        for(int nbr:graph[src]){
            if(vis[nbr]==false){
                dfs(graph , nbr ,list , vis);
            }
        }
    }
    public static int perfectFriends_(ArrayList<Integer>[] graph){
        boolean[] vis = new boolean[graph.length];
        ArrayList<Integer> ccs = new ArrayList<>();

        for(int v = 0 ; v < graph.length ;v++){
            if(vis[v]==false){
                ArrayList<Integer> scc = new ArrayList<>();
                dfs(graph , v , scc , vis);
                ccs.add(scc.size());
            }
        }

        int count = 0;
        for(int i = 0 ; i < css.size() ; i++){
            for(int j = 0 ;j < ccs.size() ;j++){
                int s1 = ccs.get(i);
                int s2 = ccs.get(j);
                count+=s1*s2;
            }
        }
        
        return count;
    }
    public static void perfectFriends(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<E> graph = new ArrayList[n];
        for(int i = 0 ; i < graph.length ; i++){
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        int ways = perfectFriends_(graph);

    }


    //Hamitolian path and cycle
    public static void Hamitolian(ArrayList<Edge>[] graph , int src , String psf , boolean[] vis , int osrc){
         if(psf.length()==graph.length){
             System.out.print(psf);

             //if hamiltonian path is cycle or not
             for(Edge edge:graph[osrc]){
                 int nbr = edge.nbr;
                 if(nbr==osrc){
                     System.out.println("*");
                     return;
                 }
             }
             System.out.println(".");
             return;
         }

        vis[src]= true;
        for(Edge edge:graph[src]){
            int nbr = edge.nbr;
            if(vis[nbr]==false){
                Hamitolian(graph, nbr, psf+nbr, vis, osrc);
            }
        }
        vis[src]=false;


    }



    //knight tour

    public static void printKnightTour(int[][] chess , int r , int c , int move){
        if(r<0 || c<0 || r>=chess.length || c>=chess.length || chess[r][c]>0){
            return;
        }else if(move = chess.length * chess.length){
            chess[r][c]= move;
            displayboard(chess);
            chess[r][c] = 0;
            return;
        }
    



        chess[r][c]=move;
        printKnightTour(chess, r-2, c+1, move);
        printKnightTour(chess, r-1, c+2, move);
        printKnightTour(chess, r+1, c+2, move);
        printKnightTour(chess, r+2, c+1, move);
        printKnightTour(chess, r+2, c-1, move);
        printKnightTour(chess, r+1, c-2, move);
        printKnightTour(chess, r-1, c-2, move);
        printKnightTour(chess, r-2, c-1, move);
        chess[r][c]=0;

    }


    //Breadth First traversal  rm*wa*
     public static class Pair{
         int v;
         String psf;
         Pair(int v , String psf){
             this.v = v;
             this.psf = psf;
         }
    }

    public static void bfs(ArrayList<Edge>[] graph , int src){
        boolean[] vis = new boolean[graph.length];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src , src+""));
        while(q.size()>0){
            Pair rem = q.remove();  //remove
            if(vis[rem.v]==true){
                continue;
            }

            vis[rem.v]=true;   //mark
            System.out.println(rem.v+"@"+rem.psf);  //work

            for(Edge edge:graph[rem.v]){  //add
                int nbr = edge.nbr;
                if(vis[nbr]==true){
                    q.add(new Pair(nbr , rem.psf+ nbr));
                }
            }
        }
    }

    //levelwise
    public static class LPair{
        int v;
        int lev;

        LPair(int v , int lev){
            this.v = v;
            this.lev = lev;
        }

    }
    public static void bfs_level_wise(ArrayList<Edge>[] graph , int src){
        boolean[] vis = new boolean[graph.length];
        Queue<LPair> q = new ArrayDeque<>();

        q.add(new Pair(src ,0));
        while(q.size()>0){
            int s = q.size();
            for(int i = 0 ; i < s ; i++){
                LPair rem = q.remove();
                if(vis[rem.v]==true){
                    continue;
                }
                vis[rem.v]= true;
                System.out.println(rev.v+" ");
                for(Edge edge:graph[rem.v]){
                    int nbr = edge.nbr;
                    if(vis[nbr]==false){
                        q.add(new Lpair(nbr , rem.lev+1));
                    }
                }
            }
        }
        System.out.println();
    }


    //is_graph_cyclic --> bfs se vetrice pehle hi dal jayega
    public static boolean isSingleCompCyclic(ArrayList<Edge>[] graph , int src , boolean[] vis){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(src);
        while(q.size()>0){
            int rem = q.remove(); //remove

            if(vis[rem]==true){
                return true;
            }

            vis[rem] = true; //mark
            
            //add nbr
            for(Edge edge:graph[rem]){
                int nbr = edge.nbr;
                if(vis[nbr]==false){
                    q.add(nbr);
                }
            }

        }

        return false;
    }

    public static boolean isGraphCyclic(ArrayList<Edge>[] graph){
        boolean[] vis = new boolean[graph.length];
        for(int v = 0 ; v < graph.length ; v++){
            boolean temp = isSingleCompCyclic(graph , v , vis);
            if(temm==true){
                return true;
            }
        }

        return false;
    }

    //Bipartite Graph-->
    //divide vertices into mutually exclusive or exhaustive sets
    //such that all edges are across sets
    //acyclic , cyclic-->even size
    //cyclic--> odd size not possible
     
            public static class Pair{
                int v;
                int set;
                Pair(int v , int set){
                    this.v = v;
                    this.set = set;
                }
            }

            public static boolean isSingleCompBipartite(ArrayList<Edge>[] graph , int src , int[] vis){

                Queue<Pair> q = new ArrayDeque<>();
                q.add(new Pair(src , 1));
                while(q.size()>0){
                    Pair rem  = q.remove(); //remove

                    if(vis[rem.v]!=0){
                        //old set number is stored is vis , new set number
                        if(vis[rem.v]!=rem.set){
                            return false;
                        }
                        continue;

                    }

                    //add unvisited nbrs

                    vis[rem.v] = rem.set;
                    for(Edge edge:graph[rem.v]){
                        int nbr = edge.nbr;
                        if(vis[nbr]==0){
                            int nbrset = (rem.set==2)? 1:2;
                            q.add(new Pair(nbr , nbrset));
                        }
                    }
                }

                return true;
            }

            public static boolean bipartite(ArrayList<Edge>[] graph){
                int[] vis = new int[graph.length];
                for(int src = 0 ; src < graph.length ; src++){
                    if(vis[src]==0){
                        boolean sca = isSingleCompBipartite(graph , src , vis);

                        if(sca == false){
                            return false;
                        }
                    }
                }
                return true;
            }
    

            //spread Infection
            public static class  Pair{
                int vtx;
                int t;

                Pair(int vtx , int t){
                    this.vtx = vtx;
                    this.t = t;
                }
            }

            public static int bfs(ArrayList<Edge>[] graph , int src , int t){
                boolean[] vis = new boolean[graph.length];
                ArrayDeque<Pair> q = new ArrayDeque<>();
                q.add(new Pair(src , 1));
                int count = 0;              
                while(q.size()>0){
                    //remove
                    Pair rem  = q.remove();

                    if(rem.t>t){
                        break;
                    }

                    //mark
                    if(vis[rem.vtx]==true){
                        continue;
                    }
                    vis[rem.vtx]= true;
                    count++;
                    for(Edge edge:graph[src]){
                        int nbr = edge.nbr;
                        if(vis[nbr]==false){
                            q.add(new Pair(nbr , rem.t+1));
                        }
                    }

                }

                return count;
            }

            //Djikstra
            public static class Dpair implements Comparable<Dpair> {
                int vtx;
                String psf;
                int wsf;

                Dpair(int vtx , String psf , int wsf){
                    this.vtx = vtx;
                    this.psf = psf;
                    this.wsf = wsf;
                }

                public int compareTo(Dpair o){
                    return this.wsf - o.wsf;
                }
            }

            public static void dijksta(ArrayList<Edge>[] graph , int src){
                boolean[] vis =new boolean[graph.length];

                PriorityQueue<Dpair> que = new PriorityQueue<>();
                que.add(new Dpair(src , src+"" , 0));

                while(que.size()>0){
                    //remove
                    Dpair rem = que.remove();

                    //mark
                    if(vis[rem.vtx] == true){
                        continue;
                    }

                    vis[rem.vtx]=true;

                    //work
                    
                }
            }
    
    









   

}
