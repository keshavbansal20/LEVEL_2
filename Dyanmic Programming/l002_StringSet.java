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
                    dp[si][ei] =  Math.max(lpss(s , si+1 , ei,dp) ,lpss(s , si  , ei-1,dp));
                else 
                    dp[si][ei] =  lpss( s, si+1 , ei-1 ,dp)+2; 
                
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
    public static void main(String[] args){
        longestPalindromicSubseq("abaccab");
    }

}

