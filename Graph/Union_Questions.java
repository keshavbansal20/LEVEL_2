public class Union_Questions {
    int[] par , size;
    public int findPar(int u){
        return par[u]==u?u:(par[u]=findPar(u));
    }
}
