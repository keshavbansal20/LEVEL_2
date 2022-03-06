import java.util.*;
public class L001{


    //maximum freq character
    public static void sol(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        HashMap<Character ,  Integer> hm = new HashMap<>();
        for(int i  = 0 ; i < str.length() ;i++){
            char ch = str.charAt(i);
            if(hm.containsKey(ch)){
                hm.put(ch , hm.get(ch)+1);
            }else{
                hm.put(ch ,1);
            }
        }   

        char mfc  = str.charAt(0);
        for(Character key:hm.keySet()){
            if(hm.get(key)>hm.get(mfc)){
                mfc = key;
            }
        }

    }


    //Get Common Elemenets 1
    public static void gcl1(){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] a1 = new int[n1];
        for(int i = 0 ;i < a1.length ; i++){
            a1[i] = sc.nextInt();
        }

        int n2 = sc.nextInt();
        int[] a2 = new int[n2];
        for(int i = 0 ; i< a2.length ; i++){
            a2[i] = sc.nextInt();
        }

        HashMap<Integer , Integer> fmap = new HashMap<>();

        for(int val:a1){
            if(fmap.containsKey(val)){
                fmap.put( val , fmap.get(val)+1 );
            }else{
                fmap.put(val  , 1);
            }
        }

        for(int val:a2){
            if(fmap.containsKey(val)){
                System.out.println(val);
                fmap.remove(val);
            }
        }

    }


    //get common elements 2
    public static void gcl2(){
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int[] a1 = new int[n1];
        for(int i = 0 ;i < a1.length ; i++){
            a1[i] = sc.nextInt();
        }

        int n2 = sc.nextInt();
        int[] a2 = new int[n2];
        for(int i = 0 ; i< a2.length ; i++){
            a2[i] = sc.nextInt();
        }

        HashMap<Integer , Integer> fmap = new HashMap<>();

        for(int val:a1){
            if(fmap.containsKey(val)){
                fmap.put( val , fmap.get(val)+1 );
            }else{
                fmap.put(val  , 1);
            }
        }

        for(int val:a2){
            if(fmap.containsKey(val) && fmap.get(val)>0){
                System.out.println(val);
                fmap.put(val  , fmap.get(val)-1);
            }
        }

    }

    //longest consecutive sequence
    public static void lcs(){
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < arr.length ; i++){
            arr[i] =sc.nextInt();
        }

        HashMap<Integer , Boolean> map = new HashMap<>();
        for(int val:arr){
            if(map.containsKey(val)){
                map.put(val, true);
            }
        }
        for(int val:arr){
            if(map.get(val-1)){
                map.put(val , false);
            }
        }

        int msp = 0;
        int ml = 0;
        for(int val:arr){
            if(map.get(val)==true){
                int tl = 1;
                int tsp = val;
                while(map.containsKey(tsp+tl)){
                    tl++;
                }   
                if(tl>ml){
                    msp = tsp;
                    ml = tl;
                }
            }
        }

        for(int i = 0 ; i < ml ; i++){
            System.out.println(msp+i); 
        }


    }

    //pq
    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //default m min heap -> min element dega
        PriorityQueue<Integer> pq1 = new PriorityQueue<>() ; //Collections.reverseOrder() to get max element

        int[] ranks = { 22 , 99 , 3 , 11 , 88 , 4 , 1};

        for(int val:ranks){
            pq.add(val);  //nlogn
        }

        for(pq.size()>0){
            System.out.println(pq.peek());
            pq.remove(); //nlogn
        }

    }

    //k largest element
    public static void klargest(int[] arr , int k){
        PriorityQueue<Integer> pq =new PriorityQueue<>();

        for(int i = 0 ; i < arr.length; i++){ // nlogk
            if(i < k ){
                pq.add(arr[i]); //log k
            }else{
                if(arr[i]>pq.peek()){
                    pq.remove(); //log k
                    pq.add(arr[i]);
                }
            }
        }
        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }

    //sort k-sorted array
    public static void ksort(int[] arr , int k ){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0 ; i <= k ; i++){
            pq.add(arr[i]);
        }
        for(int i = k+1 ; i < arr.length ; i++){
            System.out.print(pq.remove());
            pq.add(arr[i]);
        }
        while(pq.size()<0){
            System.out.println([pq.remove());
        }
    }

    //median priority 
    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;
    
        public MedianPriorityQueue() {
          left = new PriorityQueue<>(Collections.reverseOrder());
          right = new PriorityQueue<>();
        }
    
        public void add(int val) {
          // write your code here
          if(right.size()>0 && val>right.peek()){
              right.add(val);
          }else{
              left.add(val);
          }
          if(left.size()-right.size()==2){
              right.add(left.remove());
          }else if(right.size() - left.size() == 2){
              left.add(right.remove());
          }
        }
    
        public int remove() {
          // write your code here
          if(this.size()==0){
              System.out.println("Underflow");
              return -1;
          }else if(left.size()>=right.size()){
              return left.remove();
          }else{
              return right.remove();
          }
        }
    
        public int peek() {
          // write your code here
          if(this.size()==0){
              System.out.println("Underflow");
              return -1;
          }
          if(left.size()>=right.size()){
                return left.peek();
          }else{
                return right.peek();
          }
        }
    
        public int size() {
          // write your code here
          return left.size()+right.size();
        }
      }

      //merke k sorted list
      public static class Pair Comparable<Pair>{
          int li;
          int di;
          int val;
          Pair(int li , int di , int val){
              this.li = li;
              this.di = di;
              this.val = val;
          }
          public int compareTo(Pair o){
              return this.val - o.val;
          }
      }

      public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists){
        ArrayList<Integer> rv = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i = 0 ; i < lists.size() ; i++){
            Pair p = new Pair(i , 0 , lists.get(i).get(0));
            pq.add(p);
        }
        while(pq.size()>0){
            Pair p = pq.remove();
            rv.add(p.val);
            p.di++;

            if(p.di < lists.get(p.li).size()){
                p.val = lists.get(p.li).get(p.di);
                pq.add(p);
            }
        }

        return rv;
      }

      public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> arr){
          ArrayList<Integer> rv = new

      }

    
} 