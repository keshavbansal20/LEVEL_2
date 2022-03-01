import java.util.HashMap;
import java.util.HashSet;
import java.lang.reflect.Array;
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

    //are k anagrams
    public static boolean areKAnagrams(String str1, String str2, int k) {
		// write your code here
        if(str1.length()!=str2.length()){
            return false;
        }
        HashMap<Character,Integer> hmap = new HashMap<>();
        for(int i = 0 ; i < str1.length() ; i++){
            char ch = str1.charAt(i);
            hmap.put(ch , hmap.getOrDefault(ch, 0)+1);
        }

        for(int i = 0 ; i < str2.length() ; i++){
            char ch = str2.charAt(i);
            if(hmap.getOrDefault(ch, 0)>0){
                hmap.put(ch , hmap.get(ch)-1);
            }
        }
        int count = 0;
        for(char ch:hmap.keySet()){
            count+= hmap.get(ch);
        }

        if(count>k){
            return false;
        }else{
            return true;
        }
		
	}

    //anagram Mappings
    public static class Pair{
        int idx = 0;
        ArrayList<Integer> list = new ArrayList<>();
    }
    public static int[] anagramMappings(int[] arr1, int[] arr2) {
		// write your code here
        HashMap<Integer , Pair> map = new HashMap<>();
        for(int i = 0 ; i < arr2.length  ; i++){
            if(map.containsKey(arr2[i])){
                Pair p = map.get(arr2[i]);
                p.list.add(i);
            }else{
                Pair p = new Pair();
                p.list.add(i);
                map.put(arr2[i] , p);
            }
        }
        int[] ans = new int[arr1.length];
        for(int i = 0 ;i < arr1.length ;i++){
            Pair p = map.get(arr1[i]);
            ans[i] = p.list.get(p.idx);
            p.idx++;
        }

		return ans;
	}

    //valid anagrams

    public static boolean solution(String s1, String s2){
		// write your code here
        HashMap<Character , Integer> map = new HashMap<>();
        for(int i = 0 ; i < s1.length() ; i++){
            char ch = s1.charAt(i);
            map.put(ch ,map.getOrDefault(ch, 0)+1);
        }
        for(int i = 0 ; i < s2.length() ; i++){
            char ch = s2.charAt(i);
            if(map.containsKey(ch)==false){
                return false;
            }else if(map.get(ch)==1){
                map.remove(ch);
            }else{
                map.put(ch , map.get(ch)-1);
            }
        }

        return map.size()==0;

	}


    // groupAnagrams

    public static ArrayList<ArrayList<String>> groupAnagrams(String[] strs){
        HashMap<HashMap<Character , Integer> , ArrayList<String>> bmap = new HashMap<>();
        for(String str:strs){
            HashMap<Character , Integer> fmap = new HashMap<>();
            for(int i = 0 ; i < str.length() ;i++){
                char ch = str.charAt(i);
                fmap.put(ch , fmap.getOrDefault(ch, 0)+1);
            }
            
            if(bmap.containsKey(fmap)==false){
                ArrayList<String> arr = new ArrayList<>();
                arr.add(str);
                bmap.put(fmap , arr);
            }else{
                ArrayList<String> list = bmap.get(fmap);
                list.add(str);
            }
        }

        ArrayList<ArrayList<String>> res=  new ArrayList<>();
        for(ArrayList<String> arr:bmap.values()){
            res.add(arr);
        }
        return res;
    }

    //
    public static String getKey(String str){
        String key="";
        for(int i = 1; i < str.length() ;i++){
            char curr = str.charAt(i);
            char prev= str.charAt(i-1);
            int diff = curr-prev;
             if(diff<0){
                diff+=26;
            }
            key+=diff+"#";
        }
        key+=".";
        return key;
    }
    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {
		// write your code here
        HashMap<String , ArrayList<String>> map = new HashMap<>();


        for(String str:strs){
            String key = getKey(str);
            if(map.containsKey(key)==false){
                ArrayList<String> list = new ArrayList<>();
                list.add(str);
                map.put(key , list);
            }else{
                ArrayList<String> list = map.get(key);
                list.add(str);
                

            }
        }

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(ArrayList<String> list:map.values()){
            res.add(list);
        }
        return res;


		
	}

    //Isomorphi String
    public static boolean isIsomorphic(String s, String t) {
		// write your code here
        HashMap<Character , Character> map1 = new HashMap<>();
        HashMap<Character , Boolean> map2 = new HashMap<>();

        for(int i = 0 ; i < s.length() ; i++){
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            if(map1.containsKey(ch1)==true){
                if(map1.get(ch1)!=ch2){
                    return false;
                }
            }else{
                if(map2.containsKey(ch2)==true){
                    return false;
                }else{
                    map1.put(ch1, ch2);
                    map2.put(ch2 , true);
                }
            }
        }

		return true;
	}


    //word pattern
    public static boolean wordPattern(String pattern, String str) {
		// write your code here
        String[] words = str.split(" ");
        HashMap<Character , String> map = new HashMap<>();
        HashMap<String , Boolean> used = new HashMap<>();
        
        for(int i = 0 ; i < pattern.length() ;i++){
            char ch = pattern.charAt(i);
            if(map.containsKey(ch)==false){
                if(used.containsKey(words[i])==false){
                    used.put(words[i], true);
                    map.put(ch ,words[i]);
                }else{
                    return true;
                }
            }else{
                String mwith = map.get(ch);
                if(mwith.equals(words[i])==false){
                    return false;
                }
            }
        }
		
		return true;
	}

    //Count Of Subarrays Having Sum Equals To K
    public static int solution(int[] arr, int target){
		// write your code here
        int ans = 0;
        HashMap<Integer , Integer> map =new HashMap<>();
        map.put(0 , 1);
        int sum = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sum+=arr[i];
            if(map.containsKey(sum-target)){
                ans+=map.get(sum-target);
            }
            map.put(sum , map.getOrDefault(sum, 0)+1);
        }

		return ans;
		
	}

    //Longest Subarray With Sum Divisible By K
    public static int Longestsub(int[] arr, int k) {
        // write your code here
        int ans = 0;
        HashMap<Integer , Integer> map = new HashMap<>();
        int sum = 0;
        int rem = 0;
        map.put(0 , -1);
        for(int i    = 0 ;i < arr.length ;i++){
            sum+=arr[i];
            rem = sum%k;
            if(rem<0){
                rem+=k;
            }
            if(map.containsKey(rem)){
                int idx  = map.get(rem);
                int len = i - idx;
                if(len>ans)
                    ans =len;
            }else{
                map.put(rem , i);
            }
        }

        return ans;
    }

    //Count Of Subarrays With Sum Divisible By K
    public static int countSub(int[] arr, int k) {
        int ans = 0;
        HashMap<Integer ,Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int rem = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sum+=arr[i];
            rem = sum % k;
            if(rem<0){
                rem+=k;
            }

            if(map.containsKey(rem)){
                ans+=map.get(rem);
                map.put(rem , map.get(rem)+1);
            }else{
                map.put(rem ,1);
            }

        }
        return ans;

    
    }


    //Longest Subarray With Equal Number Of Zeroes And Ones
    public static int zeroOne(int[] arr) {
        // write your code here
        int ans = 0;
        HashMap<Integer ,Integer> map = new HashMap<>();
        map.put(0 , -1);
        int sum = 0;
        for(int i = 0 ; i < arr.length ;i++){
            if(arr[i]==0){
                sum+= -1;
            }else{
                sum+=1;
            }

            if(map.containsKey(sum)){
                int idx = map.get(sum);
                int len = i - idx;
                if(len>ans){
                    ans = len;
                }
            }else{
                map.put(sum  , i);
            }
        }

        return ans;

     
    }
    //Count Of Subarrays With Equal Number Of 0s 1s And 2s
    public static int solution(int[] arr) {
        // write your code here
        int ans = 0;
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(0 , 1);
        int sum = 0;
        for(int val: arr){
            if(val==0){
                sum+= -1;
            }else{
                sum+= 1;
            }
            if(map.containsKey(sum)){
                ans+=map.get(sum);
                map.put(sum , map.get(sum)+1);
            }else{
                map.put(sum , 1);
            }
        }
        return ans;
    }
    public static void main(String[] args){
        employeeundermanager();
    }
}
