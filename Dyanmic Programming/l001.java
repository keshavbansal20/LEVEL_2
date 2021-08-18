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
     //f(x , y) = f(x+1 , y) + f(x , y+1)+ f(x+1 , y+1);

     public static int mazepath_memo(int[][] arr, int sr ,int sc , int er , int ec , int[][] dp ){
         if((sr==er && sc == ec)){
             return dp[sr][sc] =1;
            //  continue;
         }
         if(dp[sr][sc]!=-1)return dp[sr][sc];
         int count = 0;
         for(int d = 0;d<3;d++){
             int r = sr+dir[d][0];
             int c = sc+dir[d][1];
             if(r>=arr.length && c>=arr[0].length){
                  count+=mazepath_memo(arr, r, c, er, ec,dp);
                    
             }
             dp[r][c] = count;
         }
        return count;
         
     }

     public static int mazepath_tabu(int[][] arr, int SR ,int SC , int er , int ec , int[][] dp ){
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
                    if(r>=arr.length && c>=arr[0].length){
                         count+=mazepath_tabu(arr, r, c, er, ec,dp);
                           
                    }
                    dp[r][c] = count;
                }
               return dp[SR][SC];
        

            }
        }
                
    }

    public static int mazepathjump_tabu(int[][] arr, int SR ,int SC , int er , int ec , int[][] dp ){
        for(int sr = er;sr>=SR;sr--){
            for(int sc = ec;sc>= SC;sc--){
                if((sr==er && sc == ec)){
                     dp[sr][sc] =1;
                    continue;
                }
                int count = 0;
                for(int d = 0;d<3;d++){
                    int r = sr+dir[d][0];
                    int c = sc+dir[d][1];
                    while(r>=arr.length && c>=arr[0].length){
                         count+=mazepathjump_tabu(arr, r, c, er, ec,dp);
                           r+=dir[d][0];
                           c+=dir[d][1];
                    }
                    dp[r][c] = count;
                }
               return dp[SR][SC];
            }
        }
                
    }

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
        if((s.charAt(n-1))>'0')
        count+=numDecodings_memo(s , n-1 ,dp);
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
}