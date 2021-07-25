import java.util.ArrayList;
import java.util.Collections;

public class l004_algo_questions {
    static int[] par;
    public static int findPar(int u){
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public static int minCostToSupplyWater(int n ,int[][] pipes , int[] wells){
        ArrayList<int[]> allPipes = new ArrayList<>();
        for(int[] a:pipes) allPipes.add(a);
        for(int i = 0 ; i< wells.length;i++){
            allPipes.add(new int[]{0 , i+1 , wells[i]});
        }
        Collections.sort(allPipes,(a,b)->{
            return a[2]-b[2];
        });

        par = new int[n+1];
        int ans = 0;
        for(int i = 0;i<=n;i++){
            par[i] = i;
        }

        for(int[] e:allPipes){
            int u = e[0] , v = e[1] , w = e[2];
            int p1 = findPar(u) , p2 = findPar(v);
            if(p1!=p2){
                par[p1]=p2;
                ans+=w;
            }
        }

        return ans;

    }
}
