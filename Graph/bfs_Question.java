import java.util.*;

public class bfs_Question {
    
    
    //rotten oranges
    public int orangesRotting(int[][] grid) {
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int freshOranges = 0, time = 0, n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    freshOranges++;
                else if (grid[i][j] == 2) {
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // mark them visited //not needed here 
                }
            }
        }

        if (freshOranges == 0)
            return 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rottedOrangeIDX = que.removeFirst();
                int sr = rottedOrangeIDX / m, sc = rottedOrangeIDX % m;
                for (int d = 0; d < 4; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                        if (--freshOranges == 0)
                            return time + 1;
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }
                }
            }
            time++;
        }

        return -1;
    }
        //wrong
        public class Solution {
            /**
             * @param rooms: m x n 2D grid
             * @return: nothing
             */
            public void wallsAndGates(int[][] rooms) {
                // write your code here
                if (rooms.length == 0 || rooms[0].length == 0)
            return;
                int n = rooms.length; int m = rooms[0].length;
                LinkedList<Integer> que = new LinkedList<>();
                int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
                for(int i = 0 ; i< n ; i++){
                    for(int j = 0 ; j < n;j++){
                        if(rooms[i][j]==0){
                            que.addLast(i*m+j);
                        }
                    }
                }
                // int level = 1;
                while(que.size()>0){
                    int size = que.size();
                    while(size-- >0){
                        int idx = que.removeFirst();
                        
                        int sr = idx/m , sc = idx%m;
                        for(int d = 0 ; d< 4;d++){
                            int r = sr + dir[d][0];
                            int c= sc + dir[d][1];
                            if(r>=0 && c>=0 && r<n && c<m && rooms[r][c]==2147483647){
                               rooms[r][c] = rooms[sr][sc] + 1;
                               que.addLast(r * m + c);
                            }
                        }   
                       
                    }
                     
                }
            }
        }

    
}
