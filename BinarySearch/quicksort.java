public class quicksort {
    //quicksot
    //data--> -12 , 2 , 7 , 4 , 34 , 23 , 0 , 1 , -1 , -50 , 16 , 23 , 7 , 4 , 2 , 3 
    public static void swap(int[] arr , int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int segragate(int[] arr , int si , int ei , int pivot){
        swap(arr , pivot , ei);
        int itr = si; 
        int p= si-1 ;
        while(itr<=ei){
            if(arr[itr]<=arr[ei]){
                swap(arr , ++p , itr);
            }
            itr++;
        }
        return p;
    }

    public static void quickSort(int[] arr , int si , int ei ){
        if(si>ei) return ;
                int pivot = si;
        int pidx = segragate(arr, si, ei, pivot);
        quickSort(arr, si, pidx-1);
        quickSort(arr, pidx+1, ei);


    }
    public static void main(String[] args) {
       int[] arr ={ -12 , 2 , 7 , 4 , 34 , 23 , 0 , 1 , -1 , -50 , 16 , 23 , 7 , 4 , 2 , 3};

       quickSort(arr, 0, arr.length - 1);
       for(int ele:arr){
        System.out.print(ele+",");
       }
    }

    //0-(p-1)==> less tha pivot
    //p-itr ==> greater than p and iterator
    //itr+1-ei==>undefined
    public int seg(int[] arr , int si , int ei , int pivot){
        swap(arr, pivot ,ei);
        int itr = si;
        int p = si-1;
        while(itr<=ei){
            if(arr[itr]<=arr[ei])
                swap(arr , ++p , itr);
            itr++;
        }
        return p;
    }
    
   public void qs(int[] arr , int si , int ei ){
    if(si>ei) return;
    int pivot = ei;
    int pidx = seg(arr , si  , ei , pivot);
    qs(arr , si , pidx-1);
    qs(arr , pidx+1 , ei);
   }
}
