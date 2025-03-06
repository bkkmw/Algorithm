package boj;

import java.io.*;
import java.util.*;

public class MainB_15989 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_15989.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int ans = solve(N);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int solve(int N) {
		int[][] dp = init_dp_arr(N);
		
		if(N<4) return dp[N][1] + dp[N][2] + dp[N][3];
		
		for(int i=4; i<=N; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		return dp[N][1] + dp[N][2] + dp[N][3];
	}
	
	public static int[][] init_dp_arr(int N) {
		if(N<4) N = 4;
		
		int[][] ret = new int[N+1][4];
		
		ret[1][1] = 1;
		ret[2][1] = 1;
		ret[2][2] = 1;
		ret[3][1] = 1;
		ret[3][2] = 1;
		ret[3][3] = 1;
		
		
		return ret;
	}
}
