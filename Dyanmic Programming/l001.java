import java.util.Arrays;

public class l001{
    /*
     1.faith
     2.recursion tree
     3.recursive code
     4.memorisation
     5.observatoin
     6.tabulation
     7.optimisation
     */


     public static void display(int[] arr){
         for(int ele:arr){
             System.out.print(ele+" ");
         }
         System.out.println();
     }

     public static void display2D(int[][] arr){
         for(int[] ar:arr){
             display(ar);
         }
         System.out.println();
     }

     //F(n) = F(n-1) + F(n-2)
     public static int fibo_memo(int n ,int[] dp){
        if(n<=1){
            return dp[n] = n;
        }
        if(dp[n]!=0)
            return dp[n];
        int ans = fibo_memo(n-1, dp)+fibo_memo(n-2, dp);
        return dp[n] = ans;
     }
     
     public static int fibo_tabu(int N ,int[] dp){
         for(int n = 0;n<=N;n++){
             if(n<=1){
                 dp[n] =n;
                 continue;
             }
             int ans = dp[n-1]+dp[n-2];
             dp[n] = ans;
         }
         return dp[N];
     }

     public static int fibo_opti(int n){
         int a = 0 , b = 1;
         for(int i =0;i<n;i++){
             int sum = a+b;
             a = b;
             b = sum;
         }
         return a;
     }

     public static void fibo_Set(){
         int n =5;
         int[] dp = new int[n+1];
         System.out.println(fibo_memo(n, dp));
     }

     //total no of paths from all given direction

     //f(x , y) = f(x+1 , y) + f(x , y+1)+ f(x+1 , y+1);

     public static int mazepath_memo( int sr ,int sc , int er , int ec ,int[][] dir , int[][] dp ){
         if((sr==er && sc == ec)){
             return dp[sr][sc] =1;
    
         }
         if(dp[sr][sc]!=0)
            return dp[sr][sc];
         
        int count = 0;
         for(int d = 0;d<3;d++){
             int r = sr+dir[d][0];
             int c = sc+dir[d][1];
             if(r>=0 && c>=0 && r<=er && c<=ec){
                  count+=mazepath_memo( r, c, er, ec,dir , dp);
             }
         }
        dp[sr][sc] = count;
            return count;
         
     }

     public static int mazepath_tabu( int SR ,int SC , int er , int ec , int[][] dir , int[][] dp ){
        for(int sr = er;sr>=SR;sr--){
            for(int sc = ec;sc>= SC;sc--){
                if((sr==er && sc == ec)){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for(int d = 0;d<3;d++){
                    int r = sr+dir[d][0];
                    int c = sc+dir[d][1];
                    if(r<=er && c<=ec){
                         count+=dp[r][c];
                           
                    }
                    dp[sr][sc] = count;
                }
                
                
            }
        }
        return dp[SR][SC];
                
    }

    public static int mazepathjump_tabu( int SR ,int SC , int er , int ec , int[][] dir , int[][] dp ){
        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];
                    while (r <= er && c <= ec) {
                        count += dp[r][c];// mazePath(r, c, er, ec, dir, dp);
                        r += d[0];
                        c += d[1];
                    }
                }

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
                
    }

    public static void mazepath_set(){
        int sr = 0 , sc = 0 , er = 2  , ec =2;
        int[][] dp = new int[er+1][ec+1];
        int[][] dir = {{0 , 1} , {1 , 0} ,{1 , 1}};
        System.out.println(mazepath_memo(sr , sc , er , ec , dir , dp));
        System.out.println(mazepath_tabu(sr , sc , er , ec , dir , dp));
        System.out.println(mazepathjump_tabu(sr , sc , er , ec , dir , dp));

    }

// Gold Mine.
public static int goldMine_memo_(int sr, int sc, int[][] mat, int[][] dir, int[][] dp) {
    if (sc == mat[0].length - 1) {
        return dp[sr][sc] = mat[sr][sc];
    }

    if (dp[sr][sc] != -1)
        return dp[sr][sc];

    int maxGold = 0;
    for (int[] d : dir) {
        int r = sr + d[0];
        int c = sc + d[1];

        if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
            maxGold = Math.max(maxGold, goldMine_memo_(r, c, mat, dir, dp) + mat[sr][sc]);
    }

    return dp[sr][sc] = maxGold;
}
    public static int goldMine_memo(int n, int m, int[][] mat) {
        int[][] dir = { { -1, 1 }, { 1, 1 }, { 0, 1 } };
        int[][] dp = new int[n][m];

        int maxGold = 0;
        for (int r = 0; r < n; r++) {
            maxGold = Math.max(maxGold, goldMine_memo_(r, 0, mat, dir, dp));
        }

        return maxGold;
    }

    public static int goldMine_Tabu(int SR, int SC, int[][] mat, int[][] dir, int[][] dp) {
        for (int sc = mat[0].length - 1; sc >= SC; sc--) {
            for (int sr = mat.length - 1; sr >= SR; sr--) {
                if (sc == mat[0].length - 1) {
                    dp[sr][sc] = mat[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
                        maxGold = Math.max(maxGold, dp[r][c] + mat[sr][sc]);
                }

                dp[sr][sc] = maxGold;
            }
        }

        int maxGold = 0;
        for (int r = 0; r < mat.length; r++) {
            maxGold = Math.max(maxGold, dp[r][0]);
        }

        return maxGold;
    }

    //Leetcode 1219
    //Leetcode 70
    //Leetcode 746
    public int minCostClimbingStairs_memo(int[] cost , int n , int[] dp ){
        if(n<=1){
            return dp[n] =cost[n];
        }
        if(dp[n]!=-1) return dp[n];
        int res = Math.min(minCostClimbingStairs_memo(cost , n-1 ,dp)  , minCostClimbingStairs_memo(cost , n-2,dp));
        dp[n] = res ;
        return res;
    }



    public int minCostClimbingStairs(int[] cost) {
        int n =cost.length;
        int res = minCostClimbingStairs_memo(cost , n , dp );                   
        return res;
    }
                                                            
    // board Path
    public static int boardPath_memo(int sp, int ep, int[] dp) {
        if (sp == ep) {
            return dp[sp] = 1;
        }
        if (dp[sp] != 0)
            return dp[sp];

        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardPath_memo(sp + dice, ep, dp);
        }

        return dp[sp] = count;
    }
    
    public static int boardPath_tabu(int SP, int ep, int[] dp) {
        for (int sp = ep; sp >= SP; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += dp[sp + dice];// boardPath_memo(sp + dice, ep, dp);
            }
            dp[sp] = count;
        }

        return dp[SP];
    }

    public static void board_path() {
        int sp = 0, ep = 20;
        int[] dp = new int[ep + 1];
        System.out.println(boardPath_tabu(sp, ep, dp));
        display(dp);
    }

     public int numDecodings_memo(String s , int n  ,int[] dp){
        if(n==0){
            return dp[n] = 1;

        }
        if(dp[n]!=-1) return dp[n];
        int count = 0;
        if((s.charAt(n-1))>'0')  //agar 0 hai piche to answer nhi bangea
        count+=numDecodings_memo(s ,     n-1 ,dp);
        if(n>1){
            int num = (s.charAt(n-2) -'0')*10+(s.charAt(n-1)-'0');
            if(num<=26 && num>=10){

                count+=numDecodings_memo(s , n-2 , dp);
            }
        }   
        return dp[n] = count;

     }  
     public int numDecodings_memo2(String s , int idx ,int[] dp){
        if(idx == s.length()){
            return dp[idx]=1;
        }

        if(s.charAt(idx)=='0'){
            return dp[idx] =0;
        }
        int count = 0;
        count+=numDecodings_memo2(s , idx+1,dp);

        if(idx < s.length()-1){
            int num = (s.charAt(idx)-'0') * 10 + (s.charAt(idx+1)-'0');
            if(num<=26 ){

                count+=numDecodings_memo2(s , idx+2,dp);
            }
        }

        dp[idx] = count;
        return count;
     }
     
     public int numDecodings_tabu(String s , int IDX ,int[] dp){
         for(int idx = s.length();idx>=0;idx--){

             if(idx == s.length()){
                  dp[idx]=1;
                  continue;
             }
     
             if(s.charAt(idx)=='0'){
                  dp[idx] =0;
                  continue;
             }
             int count = 0;
             count+=dp[idx+1];
     
             if(idx < s.length()-1){
                 int num = (s.charAt(idx)-'0') * 10 + (s.charAt(idx+1)-'0');
                 if(num<=26 ){
     
                     count+=dp[idx+2];
                 }
             }
     
             dp[idx] = count;
        }
        
        return dp[IDX];
     }


     public int numDecodings(String s){
         int n = s.length();
         int[] dp = new int[n+1];
        Arrays.fill(dp , -1);
        // int ans = numDecodings_memo(s , n , dp);
        // int ans = numDecodings_memo2(s , 0 ,dp);
        int ans = numDecodings_tabu(s , 0 ,dp);



        return ans;

     }

    // ============================================================

     public long numDecodingsStar(String s , int idx , long[] dp){
        if(idx==s.length()){
            return dp[idx] = 1;
        }
        if(dp[idx] !=-1){
            return dp[idx];
        }
        if(s.charAt(idx)=='0'){
            return dp[idx] = 0;
        }
        long count = 0 ;
        int mod = (int) 1e9 + 7;
        char ch1 = s.charAt(idx);
        if(ch1=='*'){
            count = (count+9 * numDecodingsStar(s , idx+1 ,dp))%mod;
            if(idx<s.length()-1){
                if(s.charAt(idx+1)=='*'){
                    count = (count + 15 * numDecodingsStar(s , idx+2 , dp)) % mod;
                }else if(s.charAt(idx+1)>='0' && s.charAt(idx+1)<='6'){
                    count = (count + 2 * numDecodingsStar(s , idx+2 , dp)) % mod;
                }else if(s.charAt(idx+1)>='7'){
                    count = (count + numDecodingsStar(s, idx+2, dp))%mod;
                }
            }
        } else {
            count = (count + numDecodingsStar(s, idx+1, dp)) % mod;
            if(idx < s.length()-1){
                if(s.charAt(idx+1)!='*'){
                    int num = (s.charAt(idx) - '0')*10 + (s.charAt(idx+1)-'0');
                    if(num <= 26){
                        count = (count + 1 * numDecodingsStar( s , idx+2 , dp))%mod;
                    }
                }else {
                    if(ch1=='1'){
                        count = (count + 9 * numDecodingsStar(s ,idx+2 , dp))%mod;
                   } 
                   else if(ch1=='2'){
                       count = (count + 6 * numDecodingsStar(s , idx+2 , dp))%mod;
                   }
                }
            }
        }
        return dp[idx] = count;
    }
    
    public long numDecodingsStar_tabu(String s , int IDX , long[] dp){
        for(int idx =s.length();idx>=0;idx--){
            if(idx==s.length()){
                 dp[idx] = 1;
                 continue;
            }
            
            if(s.charAt(idx)=='0'){
                dp[idx] = 0;
                continue;
            }
            long count = 0;
            int mod = (int) 1e9 + 7;
            char ch1 = s.charAt(idx);
            if(ch1=='*'){
                count = (count+9 * dp[idx+1])%mod;
                if(idx<s.length()-1){
                    if(s.charAt(idx+1)=='*'){
                        count = (count + 15 * dp[idx+2]) % mod;
                    }else if(s.charAt(idx+1)>='0' && s.charAt(idx+1)<='6'){
                        count = (count + 2 * dp[idx+2]) % mod;
                    }else if(s.charAt(idx+1)>='7'){
                        count = (count + dp[idx+2])%mod;
                    }
                }
            } else {
                count = (count + dp[idx+1]) % mod;
                if(idx < s.length()-1){
                    if(s.charAt(idx+1)!='*'){
                        int num = (s.charAt(idx) - '0')*10 + (s.charAt(idx+1)-'0');
                        if(num <= 26){
                            count = (count + 1 * dp[idx+2])%mod;
                        }
                    }else {
                        if(ch1=='1'){
                            count = (count + 9 * dp[idx+2])%mod;
                       } 
                       else if(ch1=='2'){
                           count = (count + 6 * dp[idx+2])%mod;
                       }
                    }
                }
            }
            dp[idx] = count;
        }
        return dp[IDX];
        
    }
    
   public int numDecodings_star(String s) {
       int n = s.length();
       long[] dp = new long[n+1];
    //    Arrays.fill(dp , -1);
    //    long ans  = numDecodingsStar(s , 0  , dp);
       long ans = numDecodingsStar_tabu(s, 0, dp);
       return (int)ans;
   }
    public static void main(String[] args){
        fibo_Set();
    }


    //frineds pairing


    public static long friendsPairing(int n , long[] dp){
        if(n<=1){
            return dp[n] = 1;
        }

        if(dp[n]!=0){
            return dp[n];
        }
        int mod = (int) 1e9 + 7;
        long single = friendsPairing(n-1, dp);
        long pairup = friendsPairing(n-2, dp)*(n-1);

        return dp[n] = (single+pairup%mod)%mod;
    }

    public long friendsPairing_tabu(int N ,long[] dp) 
    { 
        int mod = (int)1e9+7;
        for(int n = 0 ; n <= N ; n++){
            if(n<=1){
                 dp[n] = 1;
                continue;
            }
    
            if(dp[n]!=0){
                return dp[n];
            }
            long single = dp[n-1];
            long pairup = dp[n-2]*(n-1);
            dp[n] = (single + pairup%mod) %mod;

        }
        return dp[N];
        

    }


    public long countFriendsPairings(int n){
        long[] dp = new long[n+1];
        return friendsPairing_tabu(n, dp);
    }


     // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally
    //count the number of ways to divide    N in k groups incrementally

    public static int divideInKGroup(int n ,  int k , int[][] dp){
        if(k==1 || n==k){
            return dp[n] = 1;
        }

        if(dp[n][k]!= - 1){
            return dp[n][k];
        }

        int selfSet = divideInKGroup(n-1, k-1, dp);
        int pairOfAnotherSet = divideInKGroup(n-1, k, dp)* k;

        return dp[n][k] = selfSet + pairOfAnotherSet;
    }

    public static int divideInKGroup_Dp(int N ,  int K , int[][] dp){
        for(int n = 1 ; n <= N ; n++){
            for(int k = 1; k <= K ; k++){
                if(k>n){
                    break;
                }
                if(k==1 || n==1){
                    dp[n][k] = 1;
                    continue;
                }

                int selfSet = dp[n-1][k-1]; //divideInKGroup
                int pairOfAnotherSet = dp[n-1][k]*k;
                dp[n][k] = selfSet + pairOfAnotherSet;
            }
        }
        return dp[N][K];
    }

    public static void divideInKGroup(){
        int n = 5 , k = 3;
        int[][] dp = new int[n+1][k+1];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        System.out.println(divideInKGroup(n , k ,dp));
        
    }

    
    //mobile keypad wala question
    

}