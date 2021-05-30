package Recursion_And_BAcktracking.word

import java.util.HashSet;

-selection;

public class selection1 {
    public static void generateSelection(int i , String ustr , int ssf , int ts , String asf){
        if(i==ustr.length()){
            if(ssf==ts){
                System.out.println(asf);
            }
            return;
        }
        
        
        char ch = ustr.charAt(i);
        generateSelection(i+1 , ustr , ssf+1 , ts , asf+ch);
        generateSelection(i+1 , ustr , ssf+0 , ts , asf+"");
        
    }

  public static void main(String[] args) throws Exception {
   Scanner sc = new Scanner(System.in);
   String s = sc.next();
   int k = sc.nextInt();
   
   HashSet<Character> unique = new HashSet<>();
   String ustr = "";
   for(char ch:s.toCharArray()){
       if(unique.contains(ch) == false){
           unique.add(ch);
           ustr += ch;
       }
   }
   generateSelection(0 , ustr , 0 , k , "" );
  }
    
}
