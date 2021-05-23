package Recursion_And_BAcktracking;

import java.util.*;

//level pr boxes hai or options m items or no wali call ( combination se derive krke permutation banaya)
public class permutation2 {
    public static void permutations(int cb, int tb, int[] items, int ssf, int ts, String asf){
        // write your code here
        if(cb>tb){
            if(ssf==ts){
                System.out.println(asf);
            }
            return;
        }
        
        for(int i = 0 ; i < items.length ; i++){
            if(items[i] == 0){
                items[i] = cb;//place the items in box 
                permutations(cb+1 , tb , items , ssf+1 , ts , asf+(i+1)+"");//call next level with yes call
                items[i] = 0;    //unplace the item in the box
            }
        }
        permutations(cb+1 , tb , items , ssf , ts , asf+"0"); //call next level with no call
        
        

      }
      public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nboxes = Integer.parseInt(br.readLine());
        int ritems = Integer.parseInt(br.readLine());
        permutations(1, nboxes, new int[ritems], 0, ritems, "");
        //1--> current boxes
        //nboxes--> total boxes
        //items--> total items lenght array
        //0-->selected items so far
        //ritems --> total items 
        // ""--->answer so far
      }
}
