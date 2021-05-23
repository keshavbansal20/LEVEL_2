package Recursion_And_BAcktracking;

import java.util.*;
// Sample Input
// 5
// 3
// Sample Output
// 12300
// 12030
// 12003
// 13200
// 10230



//default approach h permutation ke liye


//levels pr items the or options m boxes 
public static class permutation1{
    public static void permutations(int[] boxes, int ci, int ti){
        // write your code here
        if(ci>ti){ //jha current item greater hojaye total iterms se 
            for(int i = 0 ; i < boxes.length ; i++){
                System.out.print(boxes[i]);    //ek ek krke jis box m item place h wha print krna baaki 0 place print hoga
                
            }
            System.out.println();
            return;
        }
        for(int i = 0 ; i < boxes.length;i++){
            if(boxes[i]==0){  //options sirf jis box m select nhi hua hai
                boxes[i] = ci; //place the items in box
                permutations(boxes , ci+1 , ti); // call the next level for items
                boxes[i] = 0; //unplace the item in box
            }
        }
      }
    
      public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nboxes = Integer.parseInt(br.readLine());
        int ritems = Integer.parseInt(br.readLine());
        permutations(new int[nboxes], 1, ritems);
      }
}