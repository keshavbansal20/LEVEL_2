package Recursion_And_BAcktracking.Queens(2d as 1d)
import java.io.BufferedReader;
import java.util.*;

public static class QueenCombination_QueenChooses{
    public static void queensCombinations(int qpsf, int tq, boolean[][] chess, int lcno) {
        // write your code here
        if(qpsf==tq){
            for(int i = 0 ; i < chess.length ; i++){
                for(int j = 0 ; j < chess.length ; j++){
                    if(chess[i][j]==false){
                        System.out.print("-\t");
                    }else{
                        System.out.print("q\t");
                    }
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int i= lcno+1 ; i < tq*tq ;i++ ){
            int nr = i/tq;
            int nc = i%tq;
            chess[nr][nc] = true;
            queensCombinations(qpsf+1 , tq , chess , i);
            chess[nr][nc] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] chess = new boolean[n][n];

        queensCombinations(0, n, chess, -1);
    }
}