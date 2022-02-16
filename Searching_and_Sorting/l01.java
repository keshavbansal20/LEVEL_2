public class l01 {
    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for(int val:piles){
            max = Math.max(val , max);
        }
        if(h==piles.length){
            return max;
        }

        int lo = 0;
        int hi = max;
        int speed = Integer.MAX_VALUE;
        while(lo<=hi){
            int sp = lo+(hi-lo)/2;
            if(isPossible(piles , sp , hi)){
                speed = sp;
                hi = sp-1;
            }else{
                lo = sp+1;
            }
        }
        return speed;
    }
    public static boolean isPossible(int[] piles , int h , int sp){
        int time = 0;
        for(int i = 0;i<piles.length ; i++){
            time+=(int)Math.ceil(piles[i]*(1.0)/sp);
        }

        return time<=h;
    }
    public int shipWithinDays(int[] weights, int days) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int val:weights){
            max = Math.max(val , max);
            sum+=val;
        }
        
        int lo = max;
        int hi = sum;
       
        int ans = 0;
        while(lo<=hi){
            int lwc  = lo + (hi-lo)/2;
            if(isPossibledays(weights  , days , lwc)){
                ans = lwc;
                hi = lwc-1;
            }else{
                lo = lwc+1;
            }
            
        }
        return ans;
    }
    public boolean isPossibledays(int[] weights , int days, int lwc){
        int d = 1;
        int sum = 0;
        for(int val:weights){
            sum+=val;
            if(sum>lwc){
                d++;
                sum = val;
            }
        }
        return d<=days;
    }

    //pcm
    
    public static void main(String[] args){

    }
    
}
