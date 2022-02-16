import java.lang.annotation.Target;
import java.util.ArrayList;

import org.omg.CORBA.SystemException;

public class l001 {
    
    public int maxArea(int[] height) {
        int i =0;
        int j = height.length -1;
        int maxwater = 0;
        while(i!=j){
            int l = j-i;
            int h = Math.min(height[i] , height[j]);
            int vol = l*h;
            if(vol>maxwater){
                maxwater = vol;
            }
            if(height[i]>height[j]){
                j--;
            }else{
                i++;
            }
        }
        return maxwater;
    }

    public static int maximumProduct(int[] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for(int i = 0 ; i < arr.length ;i++){
            if(arr[i]>max1){
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            }else if(arr[i]>max2){
                max3 = max2;
                max2 = arr[i];
            }else if(arr[i]>max3){
                max3 = arr[i];
            }
            if(arr[i]<min1){
                min2 = min1;
                min1 = arr[i];
            }else if(arr[i]<min2){
                min2 = arr[i];
            }
        }
        return Math.max(max1*max2*max3 , min1*min2*max1);
    }
    
    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunk = 0;
        for(int i = 0 ; i< arr.length ; i++){
            max = Math.max(arr[i] , max);
            if(i==max){
                chunk++;
            }
        }
        return chunk;
    }

    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] min = new int[n];
        min[n-1] = arr[n-1];
        for(int i = n-2;i >=0 ; i--){
            min[i] = Math.min(min[i+1] ,arr[i]);
        }
        int leftmax = Integer.MIN_VALUE;
        int chunk = 1;
        for(int i =0 ; i < n-1 ; i++){
            leftmax = Math.max(leftmax , arr[i]);
            if(leftmax<=min[i+1]){
                chunk++;
            }
        }
        return chunk;
    }
    public int dominantIndex(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int idx= 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(max1<nums[i]){
                max2 = max1;
                max1 = nums[i];
                idx = i;
            }else if(max2<nums[i]){
                max2 = nums[i];
            }
        }
        return max1 >= max2*2 ?idx:-1;
    }
        public boolean isVowel(char c){
            String str = "AEIOUaeiou";
            return str.contains(c+"");
        }
        public static void swap(char[] arr , int l , int r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        public String reverseVowels(String s) {
            char[] arr = s.toCharArray();
            int left = 0;
            int right = s.length()-1;
            while(left<right){
                while(left<right && isVowel(arr[left])==false){
                    left++;
                }
                while(left < right && isVowel(arr[right])==false){
                    right--;
                }
                swap(arr , left , right);
                left++;
                right--;
            }
            String str = String.valueOf(arr);
            return str;
            
        }

        public int numSubarrayBoundedMax(int[] nums, int left, int right) {
            int prevcount = 0;
            int count = 0;
            int i = 0;
            int j = 0;
            while(i<nums.length){
                if(left<=nums[i] && nums[i]>=right){
                    count+=(i-j+1);
                    prevcount = i-j+1;
                }else if(nums[i]<left){
                    count+=prevcount;
                }else{
                    prevcount =0;
                    j = i+1;
                }
                i++;
            }
            return count;
        }
        public void wiggleSort(int[] nums) {
            int[] darr = new int[nums.length];
            for(int i = 0; i < nums.length ; i++){
                darr[i] = nums[i];
            }
            Arrays.sort(darr);
            int n = nums.length;
            int i = 1;
            int j = n-1;
            while(i< n){
                nums[i]=darr[j];
                j--;
                i+=2;
            }
            i=0;
            while(i<n){
                nums[i] = darr[j];
                j--;
                i+=2;
            }
            
        }
        //LintCode 903
        public int[] getModifiedArray(int length, int[][] updates) {
            // Write your code here
            int[] arr = new int[length];
            for(int i = 0 ;i < updates.length;i++){
                int st = updates[i][0];
                int et = updates[i][1];
                int val = updates[i][2];
                arr[st] +=val;
                if(et!=length-1){
                    arr[et+1] -=val;
                }
            }
            for(int i = 1 ;i < length ; i++){
                arr[i] +=arr[i-1];
            }
            return arr;
        }
        //leetcode 238
        public int[] productExceptSelf(int[] nums) {
            int[] right = new int[nums.length];
            
            int prod = 1;
            // right array contain product from i to n-1;
            for(int i = right.length-1;i>=0;i--){
                prod *= nums[i];
                right[i] = prod;
            }
            
            
            int lp = 1;
            int[] res = new int[nums.length];
            for(int i =0;i<nums.length-1;i++){
                int rp = right[i+1];
                res[i] = lp*rp;
                lp*=nums[i];
            }
            res[nums.length-1] = lp;
            return res;
        }
        //leetcode 849
        public int maxDistToClosest(int[] seats) {
            //step1 traverse left to right and mark i distance from left preson and occupy to -1;
            int index = Integer.MAX_VALUE;
            int i = 0;
            while(i<seats.length){
                if(seats[i]==1){
                    seats[i] = -1;
                    index = i;
                }else{
                    seats[i] = Math.abs(i-index);
                }
                i++;
            }
            //step2 traverse right to left and main min between left and right and update max
             index = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
    
            i = seats.length-1;
            while(i>=0){
                if(seats[i]==-1){
                    index = i;
                }else{
                    seats[i]= Math.min(seats[i] , Math.abs(index-i));
                    max = Math.max(seats[i] , max);
                }
                i--;
            }
            return max;
            
        }
        //Leetcode 41
        public int firstMissingPositive(int[] nums) {
            
            boolean one = false;
            int i = 0;
            while(i < nums.length){
                if(nums[i]==1){
                    one = true;
                }else if(nums[i]<0 || nums[i]>nums.length){
                    nums[i] = 1;
                }
            }
            if(one) return 1;
            i=0;
            while(i<nums.length){
                
                    int idx = nums[i]-1;
                    nums[i] = -Math.abs(nums[idx]);
                
            }
            for(i = 0;i<nums.length;i++){
                if(nums[i]>0){
                    return i+1;
                }
            }
            return nums.length+1;
        }

        //Lintcode 912
        public int minTotalDistance(int[][] grid) {
            ArrayList<Integer> xcord = new ArrayList<>();
            for(int i = 0 ;i < grid.length;i++){
                for(int j =0;j < grid[0].length;j++){
                    if(grid[i][j]==1){
                        xcord.add(i);
                    }
                }
            }
            ArrayList<Integer> ycord = new ArrayList<>();

            for(int i = 0 ;i < grid[0].length;i++){
                for(int j =0;j < grid.length;j++){
                    if(grid[j][i]==1){
                        ycord.add(i);
                    }
                }
            }

            //find median points
            int x =xcord.get( xcord.size()/2);
            int y = ycord.get(ycord.size()/2);

            int dist = 0;
            for(int i = 0 ; i < xcord.size();i++){
                dist+=Math.abs(x-xcord.get(i));
                dist+=Math.abs(y-ycord.get(i));

            }
            return dist;

        }

        //Maximum swap
        //portal ka question
        public static void swap2(char[] arr , int i , int j){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    public static String maximumSwap(String num) {
        char[] arr = num.toCharArray();

        int[] lasindex = new int[10];
        for(int i = 0 ; i < arr.length;i++){
            lasindex[arr[i]-'0'] = i;
        }
        for(int i = 0;i < arr.length;i++){
            int digit = arr[i]-'0';
            boolean flag = false;
            for(int j = 9;j>digit;j--){
                if(lasindex[j]>i){

                    swap2(arr , i , lasindex[j]);
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        return String.valueOf(arr);
      }

      //twosum:unique pair
      public static List<List<Integer>> twoSum(int[] arr, int target) {
        Arrays.sort(arr);
          int left = 0;
          int right = arr.length-1;
          List<List<Integer>> res = new ArrayList<>();
          while(left<right){
              if(left!=0 && arr[left]==arr[left-1]){
                  left++;
                  continue;
              }
              int sum = arr[left]+arr[right];
              if(sum==target){
                  List<Integer> subres = new ArrayList<>();
                  subres.add(arr[left]);
                  subres.add(arr[right]);
                  res.add(subres);
                  left++;
                  right--;
              }else if(sum>target){
                  right--;
              }else{
                  left++;
              }
  
          }
          return res;
     }
     
     //Leetcode 15 3sum
     public static List<List<Integer>> twoSum_(int[] arr, int target , int si) {
        
          int left = si;
          int right = arr.length-1;
          List<List<Integer>> res = new ArrayList<>();
          while(left<right){
              if(left!=st && arr[left]==arr[left-1]){
                  left++;
                  continue;
              }
              int sum = arr[left]+arr[right];
              if(sum==target){
                  List<Integer> subres = new ArrayList<>();
                  subres.add(arr[left]);
                  subres.add(arr[right]);
                  res.add(subres);
                  left++;
                  right--;
              }else if(sum>target){
                  right--;
              }else{
                  left++;
              }
  
          }
          return res;
     }

    public List<List<Integer>> threeSum_(int[] nums , int target) {
        Arrays.sort(nums)
        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0 ;i <=nums.length-3;i++){
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int val = nums[i];
            int newtarget = target-nums[i];
            List<List<Integer>>  subres= twoSum_(nums, newtarget, i+1);
            for(List<Integer> list:res){
                list.add(val);
                res.add(list1);
            }

        }
        return res;
        
    }

    //Leetcode 537
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0 , num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+")+1 , num1.length()-1));
        int a2 = Integer.parseInt(num2.substring(0 , num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+")+1 , num2.length()-1));
        int a = a1*a2 - b1*b2;
        int b = a1*b2+a2*b1;
        
        return a+"+"+b+"i";

    }

    //gfg Minimum platform or max trains
    static int findPlatform(int arr[], int dep[], int n)
    {
        // add your code here
        	Arrays.sort(arr);
    	Arrays.sort(dep);
    	int i = 0;
    	int j = 0;
    	int platform= 0;
    	int max = 0;
    	while(i< arr.length){
    	    if(arr[i]<=dep[j]){
    	        platform++;
    	        i++;
    	    }else{
    	        platform--;
    	        j++;
    	    }
    	    max = Math.max(platform , max);
    	}
    	return max;
    }

    public static void printPrimeUsingSieve(int n) {
        //pre calculation
        boolean[] isprime = new boolean[n+1];
        Arrays.fill(isprime , true);

        for(int i=2 ; i*i<=n ; i++){
            if(isprime[i]==false){
                continue;  //kuki multiple bhi prime mark hochuke honge
            }
            for(int j = i+i ; j <=n ; j+=i){
                isprime[j] =false;
            }
        }

        for(int i= 2 ; i <=n ; i++){
            if(isprime[i]==true) {
                System.out.print(i+" ");
            }
        }
    }

    //segemented sieve
    private static ArrayList<Integer> sieve(int n){
        //pre calculation
        boolean[] isprime = new boolean[n+1];
    
        //begin from 2 to root(n)
        for(int i=2 ; i*i<=n ; i++){
            if(isprime[i]==true){
                continue;  //kuki multiple bhi prime mark hochuke honge
            }
            for(int j = i+i ; j <=n ; j+=i){
                isprime[j] =true;
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i= 2 ; i <=n ; i++){
            if(isprime[i]==false) {
               res.add(i);
            }
        }
        return res;
    }
    public static void segmentedSieveAlgo(int a, int b) {
        int rootb = (int)Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);

        int m = b-a;
        boolean[] isprime = new boolean[m +1];
        for(int prime:primes){
            int multiple = (int) Math.ceil(a*1.0/prime);
            if(multiple==1) multiple++;
            int si = multiple*prime-a;
            for(int i = si ; i <isprime.length; i+=prime){
                    isprime[i] = true; //mark it as not prime;
            }
        }
        //travel and print prime
        for(int i = 0 ; i < isprime.length ; i++){
            if(isprime[i]==false && i+a!=1){
                System.out.println(i+a);
            }
        }
      }
    //find pair with given diff
    public boolean findPair(int arr[], int size, int n)
    {
        //code here.
        Arrays.sort(arr);
        int left = 0;
        int right =1;
        while(right<size){
            int diff = arr[right] - arr[left];
            if(diff==n){
                return true;
            }else if(diff>n){
                left++;
            }else{
                right++;
            }
        }
        return false;
    }
    //Leetcode 881
    public int numRescueBoats(int[] people, int capacity) {
        Arrays.sort(people);
        int left = 0;
        int right = people.length-1;
        int boats =0;
        while(left<=right){
            int sum =people[left]+people[right];
            if(sum>capacity){
                right--;
            }else{
                left++;
                right--;
            }
            boats++;
        }
        return boats;
    }

    //tranpose 1
    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] res =new int[col][row];
       for(int r = 0 ; r < col ; r++){
           for(int c = 0 ; c < row ; c++){
               res[r][c] = matrix[c][r];
           }
       }
        return res;
    }

    //tranpose2 portal
    public static void transpose2(int[][] matrix) {
        // write your code here
        for(int i=0 ; i < matrix.length ; i++){
            for(int j = 0 ; j < i ; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    //rotate by 90
    public void rotate(int[][] matrix) {
        transpose2(matrix);
        //reverse row
        for(int r = 0 ; r < matrix.length ; r++){
            int left =0 ;
            int right = matrix[0].length-1;
            while(left<right){
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;
                left++;
                right--;
            }
        }
    }

    //Push Dominoes

    public void solveConfig(char[] arr , int i , int j){
        if(arr[i]=='L' && arr[j]=='L'){
            for(int k= i+1;  k < j;k++){
                arr[k]='L';
            }
        }else if(arr[i]=='R' && arr[j]=='R'){
            for(int k= i+1;  k < j;k++){
                arr[k]='R';
            }
        }else if(arr[i]=='L' && arr[j]=='R'){
            //nothing
        }else{
            int left = i+1;
            int right = j-1;
            while(left<right){
                arr[left]='R';
                arr[right]='L';
                left++;
                right--;
            }
        }
        
    }
    public String pushDominoes(String s) {
        int n = s.length();
        char[] arr= new char[n+2];
        arr[0] ='L';
        arr[n+1]='R';
        for(int i = 0 ; i < n;i++){
            arr[i+1] = s.charAt(i);
        }
        int i = 0;
        int j =1;
        while(j < arr.length){
            while(arr[j]=='.'){
                j++;
            }
            if(j-i>1){
                solveConfig(arr ,i , j);
            }
            i = j;
            j++;
        }

        // String res = "";
        // for(int k = 1; k < arr.length - 1; k++) {
        //     res += arr[k];
        // }
        // return res;

        StringBuilder sb = new StringBuilder();
        for(int k= 1 ; k < arr.length-1;k++){
            sb.append(arr[k]);
        }
        return sb.toString();


    }

    //Leetcode 829 
    public int consecutiveNumbersSum(int n) {
        int count = 0;
        for(int i = 1; i*(i-1) < (2*n) ; i++){
            //we have to find it is possible to make sum with i numbers
            int numerator = n - ((i-1)*i)/2;
            if(numerator%i==0){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){

    }
}
