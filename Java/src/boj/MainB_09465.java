package boj;

import java.io.*;
import java.util.*;

public class MainB_09465 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_09465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] scores = new int[2][N];
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					scores[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			
			int ans = solve(scores);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int solve(int[][] scores) {
		int N = scores[0].length;
		int[][] ret = new int[3][N]; 
		
		ret[0][0] = scores[0][0];
		ret[1][0] = scores[1][0];
		ret[2][0] = 0;
		
		for(int j=1; j<N; j++) {
			for(int i=0; i<2; i++) {
				ret[i][j] = Math.max(ret[(i+1)%3][j-1], ret[(i+2)%3][j-1]) + scores[i][j];
			}
			ret[2][j] = Math.max(ret[0][j-1], ret[1][j-1]);
		}
		
		return Math.max(ret[0][N-1], Math.max(ret[1][N-1], ret[2][N-1]));
	}
}
