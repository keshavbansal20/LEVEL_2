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
    public static void main(String[] args){

    }
}
