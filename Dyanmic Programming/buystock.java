import java.util.ArrayList;
import java.util.Scanner;

public class buystock {
    
    public static int Buy_and_sell_stock_1(int[] stocks ){

        int min = Integer.MAX_VALUE;
        int oprofit = 0;
        for(int i = 0 ; i < stocks.length ;i++){
            int price = stocks[i];
            min = Math.min(min , price);
            oprofit = Math.max(oprofit , price - min);
        }
        return oprofit;
    }
    public static int Buy_and_sell_stock_2(int[] stocks){
        int bd = 0;
        int sd= 0;
        int profit =0;
        for(int i =1 ;i<stocks.length ; i++){
            if(stocks[i]>stocks[i-1]){
                sd++;
            }else{
                profit+=stocks[sd] - stocks[bd];
                bd = i;
                sd = i;
            }
        }

        profit += stocks[sd]-stocks[bd];
        System.out.println(profit);
    }
    public static void Buy_and_sell_stock_3(int[] price){
        int pibt = -price[0];
        int pist = 0;
        for(int i  = 1 ; i < price.length ;i++){
            int new_pibt = Math.max(pibt , pist- price[i]);
            int new_pist = Math.max(pist , price[i] + pibt );
            pibt = new_pibt;
            pist = new_pist;

        }
        System.out.println(pist);
    }
    public static void Buy_and_sell_stock_4(int[] price){
        int pibt = -price[0];
        int pist = 0;
        int pwcd = 0;
        for(int i = 1; i< price.length;i++){
            int new_pibt = Math.max(pibt, pwcd - price[i]);
            pwcd = pist;
            int new_pist = Math.max(pist , price[i]+ pibt);
            pibt = new_pibt;
            pist = new_pist;
        }
        System.out.println(pist);
    }


    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i< n;i++){
            arr[i] = sc.nextInt();
        }
        int res = Buy_and_sell_stock_1(arr);
    }
}
