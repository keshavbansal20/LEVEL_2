package Recursion_And_BAcktracking.Queens(2d as 2d);
import java.io.BufferedReader;
import java.util.*;
public class QueensPermutation_Queenchoose {
    public static void queensPermutations(int qpsf, int tq, int row, int col, String asf, boolean[] queens) {
        // write your code here
        if(tq==col){
            row++;
            col=0;
            asf+="\n";
        }
        if(tq==row){
            if(qpsf==tq){
                System.out.println(asf);
                System.out.println();
            }
            
            return;
        }
        
        for(int i= 0 ; i < queens.length ; i++){
            if(queens[i]==false){
                queens[i] = true;
                queensPermutations(qpsf+1 , tq , row , col+1 , asf+"q"+(i+1)+"\t" , queens);
                queens[i] = false;
            }
        }
        queensPermutations(qpsf , tq , row , col+1 , asf+"-"+"\t" , queens);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] queens = new boolean[n];

        queensPermutations(0, n, 0, 0, "", queens);
    }
    
}
