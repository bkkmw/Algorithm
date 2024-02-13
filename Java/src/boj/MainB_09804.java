package boj;

import java.io.*;
import java.util.*;

public class MainB_09804 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_09084.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) 
				coins[i] = Integer.parseInt(st.nextToken());
			
			int M = Integer.parseInt(br.readLine());
			
			int ans = solve(coins, M);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int solve(int[] coins, int M) {
		int N = coins.length;
		int[] count = new int[M+1];
		
		for(int i=0; i<N; i++) {
			int cur = 1;
			if(coins[i] > M) continue;
			count[coins[i]] += 1;
			while(cur+coins[i] <= M) {
				count[cur+coins[i]] += (count[cur]);
				cur++;
			}
		}
		
		return count[M];
	}
}
