import java.util.*;

public class l001 {
    public static boolean isPossible(String name ,String typed){
        //if length of name is gratet than typed one
        if(name.length()>typed.length()) return false;
        int i = 0;
        int j = 0;
        //check on curr pointer of i and j and (i-1) and j
        while(i<name.length()&& j<typed.length()){
            if(name.charAt(i)==typed.charAt(j)){
                i++;
                j++;
                //i must be greater than 0
            } else if(i>0 && name.charAt(i-1)==typed.charAt(j)){
                j++;
            } else {
                return false;
            }

        }
        //if typed length is higher then check (i-1) && j
        while(j<typed.length()){
            if(name.charAt(i-1)==typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        // if name string is not traverse full
        return i<name.length()?false:true;
    } 

    public static int[] getModifiedArray(int length , int[][] queries){
        int[] res = new int[length];

        //provide impact to res
        for(int q = 0;q<queries.length;q++){
            int si = queries[q][0];
            int end = queries[q][1];
            int inc = queries[q][2];
            
            res[si]+=inc;
            if(end+1<length){
                res[end+1] -= inc;
            }


        }
        int sum = 0;
        //prefix sum
        for(int i =0 ;i<length;i++){
            sum+=res[i];
            res[i] = sum;
        }
        return res;

    }
}