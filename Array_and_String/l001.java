import java.lang.annotation.Target;

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
        
        
    public static void main(String[] args){

    }
}
