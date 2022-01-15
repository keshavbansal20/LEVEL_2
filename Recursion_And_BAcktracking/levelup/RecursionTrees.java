public class RecursionTrees {
    public static int  permutationWithInfi(int[] arr , int tar , String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i= 0 ; i < arr.length ; i++){
            if(tar-arr[i]>=0){
                count+=permutationWithInfi(arr, tar - arr[i], ans+arr[i]);

            }
        }
        return count;
    }

    public static int combinationWithInfi(int[] arr , int tar , int idx ,String ans){
        
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx ; i < arr.length ;i++){
            if(tar-arr[i]>=0){
                count+=combinationWithInfi(arr, tar-arr[i], i, ans+arr[i]);
            }
        }

        return count;
    }

    public static int combinationWithSingle(int[] arr , int tar , int idx , String ans){
        
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx ; i < arr.length ; i++){
            if(tar-arr[i]>=0){
                count+=combinationWithSingle(arr, tar-arr[i], i+1, ans+arr[i]);
            }
        }
        return count;
    }

    public static int permutationWithSingleCoin(int[] arr , int tar , String ans){
        if(tar==0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0 ; i < arr.length ; i++){
            if(arr[i]>0 && tar-arr[i]>=0){
                int val = arr[i];
                arr[i] = - arr[i];
                count+=permutationWithSingleCoin(arr , tar-val , ans+val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }


    public static int permutationWithInfi_subSeq(int[] arr ,int tar , int idx , String ans){
        if(tar==0 || idx == arr.length){
            if(tar==0){

                System.out.println(ans);
                return 1;
            }
            return 0;

        }
        int count = 0;
        if(tar-arr[idx]>=0){
            count+=permutationWithInfi_subSeq(arr, tar-arr[idx],0 , ans+arr[idx]);
        }
        count+=permutationWithInfi_subSeq(arr , tar , idx+1 , ans);
        return count;
    }



    public static int combinationWithInfi_subSeq(int[] arr , int tar , int idx , String ans){

        if(tar==0 || idx==arr.length){
            if(tar==0){

                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0){
            count+=combinationWithInfi_subSeq(arr, tar-arr[idx], idx, ans+arr[idx]);
        }
        count+=combinationWithInfi_subSeq(arr , tar , idx+1 , ans);
        return count;
    }

    public static int combinationWithSingle_subSeq(int[] arr , int tar , int idx , String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(tar-arr[idx]>=0){
            count+=combinationWithSingle_subSeq(arr, tar-arr[idx], idx+1, ans+arr[idx]);
        }
        count+=combinationWithSingle_subSeq(arr, tar, idx+1, ans);
        return count;
    }
    
    public static int permutationWithSingleCoin_subSeq(int[] arr , int tar , int idx , String ans){
        if(tar==0 || idx==arr.length){
            if(tar==0){
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if(tar-arr[idx]>=0){
            int val = arr[idx];
            arr[idx] = - arr[idx];
            count+=permutationWithSingleCoin_subSeq(arr, tar-arr[idx], 0, ans+arr[idx]);
            arr[idx] = -arr[idx];
        }
        count+=permutationWithSingleCoin_subSeq(arr, tar, idx+1, ans);
        return count;
    }
    public static void main(String[] args){
        int[] arr = { 2, 3 , 5 , 7};
        int tar = 10;
        // System.out.println(permutationWithInfi(arr , tar  , ""));
        // System.out.println(permutationWithSingleCoin(arr , tar  , ""));
        // System.out.println(combinationWithInfi(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        
        // System.out.println(permutationWithInfi_subSeq(arr, tar, 0, ""));
        System.out.println(combinationWithInfi_subSeq(arr , tar , 0 , ""));



    }
    
}
