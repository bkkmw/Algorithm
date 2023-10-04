package boj;

import java.util.*;
import java.io.*;

public class MainB_01256 {
	
	public static long[][] dp;
	public static long INF = 1000000001L;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01256.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		solve(N, M, K, sb);
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void solve(int N, int M, int K, StringBuilder sb) {
		int idx = N;
		dp = new long[N+1][M+1];
		
		while(true) {
			if(idx < 0) {
				sb.append("-1");
				return;
			}
//			double cnt = get_comb(N-idx + M-1, M-1);
			long cnt = get_comb_(N-idx, M-1);
			if(K<=cnt) 
				break;
			K -= cnt;
			idx--;
		}
		
		for(int i=0; i<idx; i++)
			sb.append("a");
		sb.append("z");
		
		int as = N-idx, zs = M-1;
		while(as+zs > 0) {
//			double cnt = get_comb(as+zs-1, as-1>zs? as-1 : zs);
			long cnt;
			if(as > 0)
				cnt = get_comb_(as-1, zs);
			else cnt = 0;
			if(cnt >= K) {
				sb.append("a");
				as--;
			}
			else {
				sb.append("z");
				zs--;
				K -= cnt;
			}
		}
	}
	
	public static long get_comb_(int N, int C) {
		if(N==0 || C==0) return 1;
		if(dp[N][C] != 0)
			return dp[N][C];
		
		return dp[N][C] = Long.min(get_comb_(N-1,C) + get_comb_(N, C-1), INF);
		
	}
	
//	public static double get_comb(int N, int C) {
//		double ret = 1;
//		for(int i=0; i<C; i++) {
//			ret *= (N-i);
//			ret /= (C-i);
//		}
//		return ret;
//	}
}
