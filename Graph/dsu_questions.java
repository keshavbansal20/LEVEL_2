public class dsu_questions {
    int[] par, size;

    public int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length, m = grid[0].length;
        par = new int[n * m];
        size = new int[n * m];

        int maxSize = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }

        int[][] dir = { { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {

                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }
                    maxSize = Math.max(maxSize, size[p1]);
                }
            }

        return maxSize;
    }
    
    public String smallestEquivalentString(String s1 , String s2 , String baseStr){
        
        par =new int[26];
        for(int i = 0 ; i < 26 ;i++){
            par[i] = i;  //make eveyone one their own parent pr leader pr father
        }
        for(int i = 0 ; i < s1.length() ;i++){
            int p1 = findPar(s1.charAt(i)-'a');
            int p2 = findPar(s2.charAt(i)-'a');

            par[p1] = Math.min(p1 , p2);
            par[p2] = Math.min(p1 , p2);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<baseStr.length();i++){
            char ch  = (char)findPar(baseStr.charAt(i)-'a'+'a');
            sb.append(ch);
        }
        return sb.toString();
    }

    public boolean equationsPossible(String[] equations){
        par = new int[26];
        for (int i = 0; i < 26; i++)
        par[i] = i;
        for(String s:equations){
            char ch1 = s.charAt(0) , ch2 = s.charAt(1) , ch3 = s.charAt(3);
            int p1 = findPar(ch1-'a');
            int p2 = findPar(ch3-'a');
            if(ch2=='=' && p1!=p2){
                par[p1] = p2;
            }

        }

        for(String s:equations){
            char ch1 = s.charAt(0) , ch2 = s.charAt(1) , ch3 = s.charAt(3);
            int p1 = findPar(ch1-'a');
            int p2 = findPar(ch3-'a');
            if(ch2=='!' && p1==p2){
                return false;
            }
            
        }
        return true;
    }
}
