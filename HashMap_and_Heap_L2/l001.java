import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
public class l001 {
    
    public static void employeeundermanager(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String , String> map = new HashMap<String , String>();
        for(int i = 0 ;i < n ; i++){
            map.put(sc.next() , sc.next());
        }
        findcount(map);
    }
    private static void findcount(HashMap<String , String> map){
        HashMap<String, HashSet<String>>  tree = new HashMap<>();
        String ceo="";
        for(String emp:map.keySet()){
            String man = map.get(emp);
            if(man.equals(emp)){
                ceo = man;
                continue;
            }else{
                if(tree.containsKey(man)){
                    HashSet<String> emps = tree.get(man);
                    emps.add(emp);
                   
                }else{
                    HashSet<String> emps = new HashSet<>();
                    emps.add(emp);
                    tree.put(man , emps);
                }
            }

        }
        HashMap<String , Integer> result = new HashMap<>();
        getSize(tree , ceo , result);
        for(String emp:result.keySet()){
            System.out.println(emp+" "+result.get(emp));
        }
    }
    public static int getSize(HashMap<String , HashSet<String>> tree , String man , HashMap<String , Integer> result){
        if(tree.containsKey(man)==false){
            result.put(man , 0);
            return 1;
    }
        int sz = 0;
        for(String emp:tree.get(man)){
            int cs = getSize(tree, emp, result);
            sz+=cs;
        }
        result.put(man , sz);
        return sz+1;
    }


    //maxConsecutiveOnes
    public int findMaxConsecutiveOnes(int[] arr) {
        int j =-1;
        int count = 0;
        int ans = Integer.MIN_VALUE;
        for(int i =0 ;i < arr.length ; i++){
            if(arr[i]==0){
                count++;
            }
            while(count>1){
                j++;
                if(arr[j]==0){
                    count--;
                }
            }
            int len = i-j;
            if(len>ans){
                ans = len;
            }
        }

        return ans;
    }


    //atmostkuniquecharacter
    public static int solution(String str, int k){
		// write your code here

		int i = -1;
		int j =-1;
		int ans = Integer.MIN_VALUE;
		HashMap<Character , Integer> map = new HashMap<>();
		while(true){
			boolean f1 = false;
			boolean f2 = false;
            while(i<str.length()-1){
                f1 = true;
                i++;
                char ch = str.charAt(i);
                map.put(ch , map.getOrDefault(ch,0)+1);
                if(map.size()<=k){
                    int len = i-j;
                    if(len>ans){
                        ans = len;
                    }
                }else{
                    break;
                }

            }

            while(j<i){
                f2=true;
                j++;
                char ch = str.charAt(j);
                if(map.get(ch)==1){
                    map.remove(ch);
                }else{
                    map.put(ch , map.get(ch)-1);
                }
                if(map.size()>k){
                    continue;
                }else{
                    int len = i-j;
                    if(len>ans){
                        ans = len;
                    }
                    break;
                }
            }
		
            if(f1==false || f2==false){
			    break;
		    }
		}


        
        
		return ans;
	}
    //count of substring with at most k unique character
    public static int count(String str, int k){
		// write your code here
        int i = -1;
        int j   = -1;
        int count = 0;
        HashMap<Character ,Integer> map = new HashMap<>();
        while(true){
            while(i<str.length()-1){
                i++;
                char ch =str.charAt(i);
                map.put(ch , map.getOrDefault(ch, 0)+1);
                if(map.size()<=k){
                    int len = i-j;
                    count+=len;
                }else{
                    break;
                }
            }
            if(i==str.length()-1 && map.size()<=k){
                break;
            }
            while(j<i){
                j++;
                char ch = str.charAt(j);
                if(map.get(ch)==1){
                    map.remove(ch);
                }else{
                    map.put(ch , map.get(ch)-1);
                }
                if(map.size()>k){
                    continue;
                }else{
                    int len = i-j;
                    count+=len;
                    break;
                }
            }
        }
        return count;
		
	}

    //findallanagramsinastring

    public static boolean compare(HashMap<Character,  Integer> pmap ,HashMap<Character,  Integer> smap ){
        for(char sch:smap.keySet()){
            if(pmap.getOrDefault(sch , 0) != smap.get(sch)){
                return false;
            }
        }
        return true;
    }
    public static void findAnagrams(String s, String p) {
        // write your code here
        HashMap<Character , Integer> smap = new HashMap<>();
        HashMap<Character , Integer> pmap=  new HashMap<>();
        for(int i = 0 ; i < p.length() ; i++){
            char ch = p.charAt(i);
            pmap.put(ch , pmap.getOrDefault(ch , 0)+1);
        }
        for(int i = 0 ; i < p.length() ; i++){
            char ch = s.charAt(i);
            smap.put(ch , smap.getOrDefault(ch , 0)+1);
        }
    
        int i = p.length();
        int count = 0;
        String ans="" ;
        while(i < s.length()){
            if(compare(pmap , smap)==true){
                count++;
                ans+=(i-p.length())+" ";
            }

            char ch = s.charAt(i);
            smap.put(ch , smap.getOrDefault(ch, 0)+1);

            char chr = s.charAt(i-p.length());
            if(smap.get(chr)==1){
                smap.remove(chr);
            }else{
                smap.put(chr , smap.get(chr)-1);
            }
            i++;
        }
        if(compare(pmap , smap)==true){
            count++;
            ans+=(i-p.length())+" ";
        
        }
        System.out.println(count);
        System.out.println(ans);
        
        
	}
    public static void main(String[] args){
        employeeundermanager();
    }
}
