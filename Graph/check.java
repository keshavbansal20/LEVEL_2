package Graph;
import java.util.*;
import java.util.*;
public class check {
    public static void main(String[] args){
        Scanner scn  = new Scanner(System.in);
		int n = scn.nextInt();
		int m = scn.nextInt();
	    char [][] graph  = new char[n][m];
	    for(int i =0; i<n;i++){
			
	        for(int j=0;j<m;j++){
	            graph[i][j] = scn.nextLine().charAt(0);
	        }
	    }
	    	    for(int i =0; i<n;i++){
			
	        for(int j=0;j<m;j++){
	            System.out.println(graph[i][j]); 
	        }
	    }
    }
}
