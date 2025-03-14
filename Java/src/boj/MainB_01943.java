package boj;

import java.io.*;
import java.util.*;

public class MainB_01943 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01943.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = 3;
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] coins = new int[N][2];
			int sum = 0;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int value = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				
				sum += value*amount;
				coins[i][0] = value;
				coins[i][1] = amount;
				
			}
			int ans = solve(coins, sum);
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int solve(int[][] coins, int sum) {
		if(sum%2 == 1) return 0;
		int N = coins.length;
		
		boolean[][] dp = new boolean[N][sum+1];
		
		for(int i=0; i<=coins[0][1]; i++) {
			dp[0][0+i*coins[0][0]] = true;
		}
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<=sum/2; j++) {
				if(dp[i-1][j]) {
					for(int k=0; k<=coins[i][1]; k++) {
						if(j+coins[i][0]*k > sum) break;
						dp[i][j+coins[i][0]*k] = true;
						
					}
				}
			}
			
		}
		
		
		return dp[N-1][sum/2]? 1 : 0;
	}
}
