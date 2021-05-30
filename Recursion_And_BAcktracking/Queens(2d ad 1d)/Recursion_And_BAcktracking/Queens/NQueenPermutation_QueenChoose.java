package Recursion_And_BAcktracking.Queens(2d ad 1d).Recursion_And_BAcktracking.Queens;

public class NQueenPermutation_QueenChoose {
    public static boolean IsQueenSafe(int[][] chess, int row, int col) {
        // write your code here
        int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
         for(int r = 1; r<= chess.length ; r++){
             for(int i = 0 ; i <8 ;i++ ){
                 int nr = row + dir[i][0]*r;
                 int nc = col + dir[i][1]*r;
                 if(nr>0 && nr<chess.length && nc>0 && nc <chess.length){
                     if(chess[nr][nc]!=0){
                         return false;
                     }
                 }
             }
         }
         return true;
    }

    public static void nqueens(int qpsf, int tq, int[][] chess ) {
        // write your code here
        if(qpsf == tq){
            for(int i =0 ; i < chess.length ;i++){
                for(int j = 0; j < chess.length ; j++){
                    if(chess[i][j]==0){
                        System.out.print("-\t");
                    }
                    else{
                        System.out.print("q" + chess[i][j] + "\t");
                    }
                }
                System.out.println();   
            }
            System.out.println();
            return;
        }
        
        for(int i = 0; i < chess.length* chess.length ; i++){
            int r = i/chess.length;
            int c = i%chess.length;
            if(chess[r][c]==0 && IsQueenSafe(chess , r , c) == true){
                chess[r][c] = qpsf+1;
                nqueens(qpsf+1 , tq , chess );  
                chess[r][c] = 0;
            }
            
        }
       
        
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] chess = new int[n][n];

        nqueens(0, n, chess );
    }
}
