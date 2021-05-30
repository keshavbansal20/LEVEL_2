package Recursion_And_BAcktracking.BasicOFPandC;
import java.util.*;

// 5
// 3
// Sample Output
// iii--
// ii-i-
// ii--i
// i-ii-
// i-i-i

//level pr items hai aur options pr boxes hai
public class combination2 {
    public static void combinations(int[] boxes, int ci, int ti, int lb){
        // write your code here
        if(ci>ti){    // jab item jo level pr h wo total item se greater hojata hai tb base case hota hai
            for(int i = 0 ; i< boxes.length ;i++){  // boxes ki length pr loop lgega base jha pr item hoga
                //wha pr i print hoga else - print hoga
                if(boxes[i]==0){
                    System.out.print("-");
                } else{
                    System.out.print("i");
                }
            }
            System.out.println();
            return;
        }
        //lb ka mtlb last box selected uusse aage ke box m check krenge or call lgegi to avoid permutaions
        for(int i = lb+1 ; i < boxes.length ; i++){  //loop for calling the next options for next item
            if(boxes[i]==0){ //check kr rhe hai item place hai h box m ya nhi 
                boxes[i]= ci; //phir place krdenge item ko box m (item identical hai toh farq nh pdega bas indication ke liye place krna hai)
                combinations(boxes , ci+1 , ti , i);//call the next level items
                boxes[i] = 0;    //unplace kr rhe h level pr aakr taki aage call lge toh boxes availble ho combination ke liye
            }
            
        }
        
      }
    
      public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nboxes = Integer.parseInt(br.readLine());
        int ritems = Integer.parseInt(br.readLine());
        combinations(new int[nboxes], 1, ritems, -1);
        //boxes--> total boxes
        //1--> current item
        //ritems--> total items
        //-1 --> last selected box
      }
}
