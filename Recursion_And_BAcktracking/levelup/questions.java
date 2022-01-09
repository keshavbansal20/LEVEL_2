public class questions {


    //https://practice.geeksforgeeks.org/problems/special-matrix4201/1#
    public int dfs(int sr , int sc , int er , int ec , boolean[][] block , int[][] dp){
        int mod = (int) 1e9 + 7;
        if(sr==er && sc==ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc]!=-1) return dp[sr][sc];
        int count = 0;
        if(sc+1<=ec && block[sr][sc+1]==false){
            count = (count%mod + dfs(sr , sc+1 , er , ec , block ,dp)%mod)%mod;
        }
        if(sr+1<=er && block[sr+1][sc]==false){
            count = (count%mod +  dfs(sr +1 , sc , er , ec , block , dp)%mod)%mod;
        }
        return dp[sr][sc] =  count;
    }
    
    public int FindWays(int n, int m, int[][] blocked_cells, Object Arrays)
    {
        // Code here
        n++ ;
        m++;
        boolean[][] block = new boolean[n][m];
        for(int[] b:blocked_cells) {
            block[b[0]][b[1]] = true;
        }
        int[][] dp = new int[n][m];
        if(block[1][1] || block[n-1][m-1]){
            return 0;
        }
        for(int[] d:dp){
            Arrays.fill(d , -1);
        }
         dfs(1 , 1 , n -1, m -1, block , dp);
        return dp[1][1];
    }
    
}
