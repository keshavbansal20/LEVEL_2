package Recursion_And_BAcktracking.word-selection;

public class wordk-selection1 {
    
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int k = Integer.parseInt(br.readLine());

    HashSet<Character> unique = new HashSet<>();
    String ustr = "";
    for (char ch : str.toCharArray()) {
      if (unique.contains(ch) == false) {
        unique.add(ch);
        ustr += ch;
      }
    }
    
    generateSelection(ustr , 0 , new Character[k], k);

    
  }
  
  public static void generateSelection(String ustr , int idx ,Character[] spots, int k){
      
        if(idx==ustr.length()){
            if(k==0){
                for(Character a:spots){
                    System.out.print(a);
                }
                System.out.println();
            }
            return;
        }
        char ch = ustr.charAt(idx);
        
        // yes
      for(int i = 0 ; i < spots.length;i++){
          
          if(spots[i]==null){
              spots[i] = ch;
              generateSelection(ustr , idx+1 , spots,k-1);
              spots[i] = null;
          }
      }
    //   no
      generateSelection(ustr , idx+1 , spots, k);
  }
    
}
