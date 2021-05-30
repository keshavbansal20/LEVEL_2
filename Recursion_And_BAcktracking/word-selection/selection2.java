package Recursion_And_BAcktracking.word-selection;

public class selection2 {
    public static void main(String[] args){
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
       
       generateSelection(ustr , 1 , k , -1 , "");
      }
      
      public static void generateSelection(String ustr , int cs , int ts , int lc , String asf){
          if(cs>ts){
              System.out.println(asf);
              return;
          }
          for(int i = lc+1;i < ustr.length();i++){
              char ch = ustr.charAt(i);
              generateSelection(ustr , cs+1 , ts , i , asf+ch);
          }
      }
    
    
}
