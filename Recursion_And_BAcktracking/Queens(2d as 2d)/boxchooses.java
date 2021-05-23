

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

     //level pr cell no hoga aur options m queen aayengei ya nhi  
public static class boxchooses{
      
    public static void queensCombinations(int qpsf, int tq, int row, int col, String asf){
        // write your code here
        if(col==tq){ //jab ek  row fill hojati hai tab dusre row pr jane keliye 
            col = 0;  
            row++;
            asf+="\n";
        }
        if(row==tq){  //jab pura chess bar jayega uske baad fill krdenge   
            if(qpsf==tq){
                System.out.println(asf);
            }
            return;
        }
        queensCombinations(qpsf+1 , tq , row , col+1 , asf+"q"); //yes call --> call for next row,col+1 and place the queen
        queensCombinations(qpsf , tq , row , col+1 , asf+"-"); //No call
        
    }
        
        
        
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        queensCombinations(0, n, 0, 0, "");
        //0--> queen placed so far
        //n--> total queens
        //0--> row number
        //0 --> col number
        //""--> answer so far        
    }

}