package Recursion_And_BAcktracking;

import java.util.*;

// Sample Input
// 5
// 3
// Sample Output
// iii--
// ii-i-
// ii--i
// i-ii-
// i-i-i
// i--ii

//level pr boxes or options m items h isliye yes or no ki call lgegi;
//2*4 = 16 option benge end m pr jha pr ssf== ts hoga wo anser print krna hai 
public class combination1 {

    
    public static void combinations(int cb, int tb, int ssf, int ts, String asf){
        // write your code here
        if(cb>tb){  //jab current box level total boxes se bda hojata hai tab base case
            if(ssf == ts){   //agar ssf equal hojata hai total selection ke toh wha hamara answer hai
                System.out.println(asf);
            }
            return;
        }

        if(ssf<ts){   //agar selection so far greater hojat ah total selection se toh sird no wali call hi lgegi
        combinations(cb+1 , tb , ssf+1 , ts , asf+"i");  //yes wali call 
        }
        combinations(cb+1 , tb , ssf , ts , asf+"-");  //no wali call
      }
    
      public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nboxes = Integer.parseInt(br.readLine());
        int ritems = Integer.parseInt(br.readLine());
        combinations(1, nboxes, 0, ritems, "");
        //cb--> choosen box 
        // nboxes --> total boxes 
        //0--> items choosen so far
        //ritems--> total items
        //""--> answer so far required to print in base case;
        //
        
      }
    
}
