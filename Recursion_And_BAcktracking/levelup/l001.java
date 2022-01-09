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

    public static class pathPair{
        int len = 0 ;
        String psf = "";
        int count = 0;
        
        pathPair(int len , String psf , int count){
            this.len = len;
            this.psf = psf;
            this.count = count;
        }
    }

    public static pathPair floodfill_longest(int sr ,int sc , int er , int ec  , int[][] dir ,String[] Sdit , boolean[][] vis){
        
        pathPair myAns = new pathPair(0 , "" , 0);
        vis[sr][sc] = true;
        for(int d = 0 ; d < dir.length ; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r>=0 && c>=0 && r <= er && c <= ec  &&   !vis[r][c] ){
                pathPair recAns = floodfill_longest(r, c, er, ec, dir, Sdit, vis);
                if(recAns.len+1>myAns.len){
                    myAns.len = recAns.len+1;
                    myAns.psf = recAns.psf+Sdit[d];
                    myAns.count +=1;
                }
            }
        }
        
        vis[sr][sc] = false;

        return myAns;
        
    }

    public static pathPair floodfill_shortest(int sr , int sc , int er  , int ec , int[][] dir , String[] Sdit , boolean[][] vis){
        if(sr==er && sc==ec){
            return new pathPair(0 , "" , 1);
        }
        pathPair myAns = new pathPair((int)1e8 , "" , 0);
        vis[sr][sc] = true;
        for(int d = 0 ; d < dir.length ; d++){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];
            if(r>=0 && c>=0 &&  r<= er && c<=ec && !vis[r][c]){
                pathPair recAns = floodfill_shortest(r, c, er, ec, dir, Sdit, vis);
                if(recAns.len+1<myAns.len){
                    myAns.len = recAns.len+1;
                    myAns.psf = Sdit[d] + recAns.psf;
                }
            }
        }
        vis[sr][sc] = false;
        return myAns;
    }


    public static void main(String[] args){
        int[][] dir = {{ 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 }};
        String[] Sdir = {  "l", "r", "d", "u", "w", "s", "n", "e"};

        int n = 4 , m = 4;
        boolean[][] vis = new boolean[n][m];
        // System.out.println(floodfill(0, 0, n-1,m-1, dir, Sdir, vis, ""));
        // pathPair  longPath = floodfill_longest( 0 , 0 , n-1 , m-1  , dir , Sdir , vis );
        // System.out.println(longPath.len+" ,  "+longPath.psf + "  , " + longPath.count);

        pathPair  shortpath = floodfill_shortest( 0 , 0 , n-1 , m-1  , dir , Sdir , vis );
        System.out.println(shortpath.len+" ,  "+shortpath.psf + "  , " + shortpath.count);
    

    }
}
