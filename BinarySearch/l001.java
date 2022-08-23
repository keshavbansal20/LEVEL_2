public class l001{
    public static void main(String[] args){
        
    }

    public static int binarySearch(int[] arr , int data){
        
        int n = arr.length;
        int si = 0 , ei = n-1;
        while(si<ei){
            int mid = si+(ei-si)/2;
            if(arr[mid]==data){
                return mid;
            }else if(arr[mid]>data){
                si=mid+1;
            } else{
                ei = mid-1;
            }
        }

        return -1;
    }

    //data for dry run --> 10 , 20 , 20 , 20 , 20 , 23, 23, 23  , 52 , 53 , 57, 62 , 62 , 72, 76
    //characets are repetive
    public int[] searchRange(int[] nums, int target) {

        int n =nums.length;
        int[] ans = new int[2];
        ans[0] = firstPosition(nums ,target);
        ans[1] = lastPosition(nums,target);
        return ans;

    }
    
    public int firstPosition(int[] arr , int target){
        int n = arr.length-1;
        int si = 0 , ei = n;
        while(si<=ei){
            int mid = si + (ei-si)/2;
            if(arr[mid]==target){
                if((mid-1)>=0 && arr[mid-1]==target){
                    ei = mid-1;
                }else{
                    return mid;
                }
            }else if(arr[mid]>target){ 
                ei  = mid-1;
            }else{
                si = mid+1;
            }
        }
        return -1;
    }
    
    public int lastPosition(int[] arr , int target){
                int n = arr.length-1;

        int si = 0 , ei = n;
       
        while(si<=ei){
            
            int mid = si + (ei-si)/2;
            if(arr[mid]==target){
                if((mid+1)<=(n) && arr[mid+1]==target){
                    si = mid+1;
                }else{
                    return mid;
                }
            }else if(arr[mid]>target){ 
                ei  = mid-1;
            }else{
                si = mid+1;
            }
        }
        return -1;
    }

    //lower bound
    //edge cases like first index , last index , if data is present 
    public int searchInsert(int[] nums, int target) {
        int si = 0  , ei = nums.length -1 ;
        while(si<=ei){
            int mid = si+(ei-si)/2;
            if(nums[mid]<=target){
                si = mid+1;
            }else{
                ei = mid-1;
            }
        }
    
        int insertPos = si;
        int lastIndex = si-1;
        return (lastIndex>=0 && nums[lastIndex]==target?lastIndex:insertPos );
}

//nearest element like ceil and floor,focus on edge cses
public  static int nearestPosition(int[] nums , int target){
    int n = nums.length;
    if(nums.length==0){
        return 0;
    }
    if(target<=nums[0] || nums[n-1]>= target){
        return target<=nums[0]?nums[0]:nums[n-1];
    }

    int si = 0 , ei = n-1;
    while(si<=ei){
        int mid = si + (ei-si)/2;
        if(nums[mid]<=target){
            si = mid+1;
        }else{
            ei = mid -1;
        }
    }
    //si==ceil value index , ei = floor value index

    return target-nums[ei]<=nums[si]-target?nums[ei]:nums[si];
}   

public int firstPosition_01(int[] arr , int target){
    int n = arr.length-1;
    int si = 0 , ei = n;
    while(si<=ei){
        int mid = si + (ei-si)/2;

         if(arr[mid]>=target){ 
            ei  = mid-1;
        }else{
            si = mid+1;
        }
    }
    return si<=n && arr[si]==target?si:-1;
}

public int lastPosition_01(int[] arr , int target){
            int n = arr.length-1;

    int si = 0 , ei = n;
   
    while(si<=ei){
        
        int mid = si + (ei-si)/2;
         if(arr[mid]>target){ 
            ei  = mid-1;
        }else{
            si = mid+1;
        }
    }
    int insertPos = si;
    int lastpos = si-1;
    return lastpos>=0 && arr[lastpos]==target?lastpos:-1;
}
    //floor<=data <ceil same as lower bound and upper bound


    //cl2
    //(r,c)==>(r*m+c) 
    //convert 2d into virtually 1d 
    //si less than "equal" to ei jab element find krna hota hai 
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length , m = matrix[0].length   , si= 0 , ei = n*m-1;
        while(si<=ei){
            int mid= (si+ei)/2;  //index in 1D
            int r = mid/m , c = mid%m; //index in 2d (r,c)
            if(matrix[r][c]==target){
                return true;
            } else if(matrix[r][c]<target){
                si = mid+1;
            }else {
                ei = mid-1;
            }
            
        }
        return false;
    }

    public boolean searchMatrixII(int[][] matrix, int target) {
        int n = matrix.length , m = matrix[0].length , si = n-1 , ei = 0;
        while(si>=0 && ei<m){
            int ele = matrix[si][ei];
            if(ele==target){
                return true;
            }else if(ele < target){
                ei++;
            }else{
                si--;
            }
        }
        return false;
    }


    //count inversion
    static long inversionCountArray(long[] arr ,int si ,int mid , int ei ,long[] sortedArray ){
        int lsi = si , lei = mid;
        int rsi = mid+1 , rei = ei;
        int k = 0;
        long count= 0;
        while(lsi <= lei && rsi<=rei){
            if(arr[lsi]>arr[rsi]){
                count+= (lei-lsi)+1;
                sortedArray[k++] = arr[rsi++];
            }else{
                sortedArray[k++] = arr[lsi++];
            }
        }

        while(lsi<=lei)
            sortedArray[k++] = arr[lsi++];
        while(rsi<=rei)
            sortedArray[k++] = arr[rsi++];
        
        k = 0;
        for(int i = si ; i <= ei ;i++){
            arr[i] = sortedArray[k++];
        }

        return count;
    }
    static long inversionCount(long[] arr , int si , int ei ,long[] sortedArray){
        if(si>=ei) return 0;
        int mid = si +(ei - si)/2;
        long lci = inversionCount(arr, si , mid , sortedArray);
        long rci = inversionCount(arr, mid+1, ei , sortedArray);

        return lci + rci + inversionCountArray(arr ,si ,mid,  ei , sortedArray );
    }
    static long inversionCount(long arr[], long N)
    {   
        long[] sortedArray = new long[(int)N];
        return inversionCount(arr , 0,(int)N-1,sortedArray);
    }


    
    
}