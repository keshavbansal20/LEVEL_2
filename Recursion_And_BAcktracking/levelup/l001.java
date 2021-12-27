// import java.util.* ;

public class l001 {
    public static int floodfill(int sr , int sc , int er , int ec , int[][] dir , String[] Sdir , boolean[][] vis , String PSF){
        if(sr==er && sc==ec){
            System.out.println(PSF);
            return 1;
        }
        int count = 0;
        vis[sr][sc] = true;
        for(int d = 0 ; d < dir.length ; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if( r>=0 && c>=0 && r<= er && c<=ec && !vis[r][c]){
                count += floodfill(r, c, er, ec ,dir , Sdir , vis , PSF + Sdir[d] + " "  );
            }
        }
        vis[sr][sc]= false;
        return count;
    }
    public static void main(String[] args){
        int[][] dir = {{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 }};
        String[] Sdir = {  "l", "r", "d", "u", "w", "s", "n", "e"};

        int n = 4 , m = 4;
        boolean[][] vis = new boolean[n][m];
        System.out.println(floodfill(0, 0, n-1,m-1, dir, Sdir, vis, ""));

    }
}
