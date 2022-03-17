import java.util.Arrays;

// import java.util.*;
public class l002_StringSet {
    public static void display(int[] dp){
        for(int ele:dp){
            System.out.print(ele+" ");
        }
        System.out.println();
    }
    public static void display2D(int[][] dp){
        for(int[] ar:dp){
            display(ar);
        }
        System.out.println();
    }
    
    public static int lpss(String s , int si , int ei, int[][] dp ){
        if (si >= ei) {
            return dp[si][ei] = (si == ei) ? 1 : 0;
        }
        if(dp[si][ei]!=0)
            return dp[si][ei];
        if(s.charAt(si)!=s.charAt(ei))
            dp[si][ei] =  Math.max(lpss(s , si+1 , ei,dp) ,lpss(s , si  , ei-1,dp));
        else 
            dp[si][ei] =  lpss( s, si+1 , ei-1 ,dp)+2; 
        
        return dp[si][ei];
    }

    public static int lpss_dp(String s , int SI , int EI, int[][] dp ){
        int n = s.length();
        for(int gap = 0;gap<n;gap++){
            for(int si = 0, ei = gap; ei<n ;si++ , ei++){
                if (si >= ei) {
                     dp[si][ei] = (si == ei) ? 1 : 0;
                     continue;
                }
                
                if(s.charAt(si)!=s.charAt(ei))
                    dp[si][ei] =  Math.max(dp[si+1][ei] , dp[si][ei-1]);
                else 
                    dp[si][ei] =  dp[si+1][ei-1]+2; 
                
            }
        }   
        
        return dp[SI][EI];
    }

    public static String lpss_ReverseEngi(String s , int si ,int ei , int[][] dp){
        if(si>=ei){
            return si==ei ? (s.charAt(si)+""):"";
        }

        if(s.charAt(si)== s.charAt(ei))
            return s.charAt(si) + lpss_ReverseEngi(s , si+1 , ei-1, dp) + s.charAt(si);
        else if(dp[si+1][ei]>dp[si][ei-1])
            return lpss_ReverseEngi(s , si+1 , ei , dp);
        else 
            return lpss_ReverseEngi(s , si , ei - 1 , dp);
    }


    //longest palindromic subsequence
    public static void longestPalindromicSubseq(String s){
        int n = s.length();
        int[][] dp = new int[n][n];


        // int ans = lpss(s , 0 , n-1,dp);
        int ans = lpss_dp(s , 0 , n-1,dp);
        
        display2D(dp);
        System.out.println(lpss_ReverseEngi(s , 0 , n-1 , dp));
    }


    //max gold wala mine reverse engineering



    //longest common subs 1143

    public static int lcss(String str1 , int n , String str2 , int m , int[][] dp){
        if(n==0 ||  m==0){
            return dp[n][m] = 0;
        }
        if(dp[n][m]=-1){
            return dp[n][m];
        }
        if(str1.charAt(n-1)==str2.charAt(n-1)){
           return dp[n][m] = lcss(str1 , n-1 , str2 , m-1 , dp)+1;
        }else  {
            return dp[n][m] = Math.max(lcss(str1 , n ,str2 , m-1 , dp) , lcss(str1 , n-1 , str2 , m ,dp));
        }
    }

    public static int lcss_dp(String str1 , int N , String str2 , int M , int[][] dp){
        for(int n = 0 ; n <= N ; n++){
            for(int m = 0 ;m <= M ; m++){
                if(n==0 ||  m==0){
                    dp[n][m] = 0;
                    continue;
                }
    
                if(str1.charAt(n-1)==str2.charAt(n-2)){
                   return dp[n][m] = dp[n-1][m-1]+1;
                }else  {
                    return dp[n][m] = Math.max(dp[n][m-1] , dp[n-1][m]);
                }
                
            }
        }

        return dp[N][M];
     
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        // for (int[] d : dp)
        // Arrays.fill(d, -1);
        int ans = lcss_Dp(text1, n, text2, m, dp);
        display2D(dp);
        return ans;
    }




    //longest palindromic substring 
    public String longestPalindrome(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int gap = 0 ; gap < n ; gap++){
            for(int i = 0 , j = gap ; j < n ; j++ , i++){
                if(gap==0){
                    dp[i][j] = true;
                }else if(gap==1 && s.charAt(i) == s.charAt(j)){
                    dp[i][j] = true;
                }else{
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                }

                if(dp[i][j] && j-i+1 >len){
                    len = j-i + 1;
                    si = i;
                }
            }
        }

        return s.subtring(si , si+len);
    }
    
    
    //longest common substring

    public static int LCSSubStr(char X[] , char Y[] , int m , int n){
        int[][] LCS = new int[m+1][n+1];
        int result = 0;
        for(int i = 0 ; i <= m ; i++){
            for(int j = 0; j <= n;j++){
                if(i==0 || j==0)
                    LCS[i][j] = 0;
                else if( X[i-1] == Y[j-1]){
                    LCS[i][j] = LCS[i-1][j-1]+1;
                    result = Integer.max(result , LCS[i][j]);
                }else{
                    LCS[i][j] = 0;
                }
            }
        }

        return result;
    }
    public static void main(String[] args){
        longestPalindromicSubseq("abaccab");
    }


    //max dot product by lcss
    public int maximum(int... arr){
        int max = arr[0];
        for(int ele:arr){
            max = Math.max(ele , max);
        }
        return max;
    }
    public int maxDotProduct(int[] nums1 , int[] nums2 , int n , int m , int[][] dp){
        if(n==0||m==0){
            return dp[n][m] = -(int)1e8;
        }
        if(dp[n][m]!=-(int)1e9){
            return dp[n][m];
        }
        int val = nums1[n-1]*nums2[m-1];
        int acceptTwoNumbers = maxDotProduct(nums1 , nums2 , n-1 , m-1 , dp)+val;
        int a = maxDotProduct(nums1 , nums2 , n , m-1 , dp);
        int b = maxDotProduct(nums1, nums2 , n-1 , m , dp);

        return dp[n][m] = maximum(val , acceptTwoNumbers , a , b);
    }

    public int maxDotProduct(int[] nums1 , int[] nums2){
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n+1][m+1];
        
        for(int[] d:dp)
            Arrays.fill(d , -(int)1e9);
        
        return maxDotProduct(nums1, nums2, n, m , dp);
            
    }


    //1035
    public int maxUncrossedLines(int[] nums1 , int[] nums2 , int n , int m , int[][] dp){
        if(n==0 || m==0){
            return dp[n][m] = 0;
        }
        if(dp[n][m] !=-1)
            return dp[n][m];
        
        if(nums1[n-1] == nums2[m-1])
            return dp[n][m] = maxUncrossedLines(nums1, nums2 ,n-1,m-1 , dp)+1;
        else
            return dp[n][m] = Math.max(maxUncrossedLines(nums1, nums2, n-1, m, dp),maxUncrossedLines(nums1, nums2, n, m-1, dp));

    }

    public int maxUncrossedLines(int[] nums1 , int[] numm2){
        int n = nums1.length;
        int m =  numm2.length;
        int[][] dp = new int[n+1][m+1];
        for(int[] d:dp)
            Arrays.fill(d , -1);
        
        return maxUncrossedLines(nums1, numm2 , n , m , dp)
        
    }


    //72
    public int minDistance(String word1 , String word2 , int n ,int m , int[][] dp){
        if(n==0)
            dp[n][m] = m; //insert
        else if(m==0)
            dp[n][m] = n; //delete
        
        if(dp[n][m]!=-1)
            return dp[n][m];
        
        if(word1.charAt(n-1)==word2.charAt(m-1))
            return dp[n][m] = minDistance(word1, word2, n-1, m-1, dp);
        
        int insert = minDistance(word1, word2 , n , m-1 , dp);
        int delete = minDistance(word1, word2 , n-1 , m , dp);
        int replace = minDistance(word1,  word2 , n-1 , m-1, dp);

        return dp[n][m] = Math.min(Math.min(insert , delete) , replace)+1;
    }

    public int minDistance(String word1 , String word2){
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] d:dp)
            Arrays.fill(d ,-1);
        
        return minDistance(word1, word2 , n ,m ,dp);
    }
    //583  -->. Delete Operation for Two Strings
    public int minDistance_dp2(String word1 ,int n , String word2 ,int m , int[][]dp ){
        if(n==0 && m==0) return 0;
        if(n==0) return m;
        if(m==0) return n;
        if(dp[n][m]!=-1) 
            return dp[n][m];
        if(word1.charAt(n-1)==word2.charAt(m-1)){
            return dp[n][m] = minDistance_dp2(word1 , n-1 , word2 , m-1 , dp);
        }else{
            return dp[n][m] = Math.min(minDistance_dp2(word1 , n-1 ,word2 , m , dp),minDistance_dp(word1 , n ,word2 , m-1 , dp))+1;
        }
        
        
        
    }
    public int minDistance2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] d:dp){
            Arrays.fill(d , -1);
        }
        return minDistance_dp2(word1 , n , word2 , m , dp);
    }
}

