package boj;

import java.io.*;
import java.util.*;

public class MainB_01489_Wrapper {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01489.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Integer[][] stats = new Integer[2][N];
		
		for(int i=0; i<2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				stats[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(stats);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(Integer[][] stats) {
		int ret = 0;
		int N = stats[0].length;
		int[][] mem = init_mat(N, -1);
				
		Arrays.sort(stats[0], (o1,o2) -> o2-o1);
		Arrays.sort(stats[1], (o1,o2) -> o2-o1);

		ret = recur(mem, stats, 0, 0, N);
		
		
		return ret;
	}
	
	public static int recur(int[][] mem, Integer[][] stats, int a_idx, int b_idx, int N) {
		if(a_idx == N || b_idx == N) {
			return 0;
		}
		
		if(mem[a_idx][b_idx] >= 0) return mem[a_idx][b_idx];
		
		if(stats[0][a_idx].intValue() > stats[1][b_idx].intValue()) {
			mem[a_idx][b_idx] = recur(mem, stats, a_idx+1, b_idx+1, N) + 2;
		}
		// WA : Requires value() for comparison
		else if(stats[0][a_idx].intValue() == stats[1][b_idx].intValue()) {
			int tie = recur(mem, stats, a_idx+1, b_idx+1, N) + 1;
			int skip = recur(mem, stats, a_idx, b_idx+1, N);
			
			mem[a_idx][b_idx] = Math.max(tie, skip);
		}
		else {
			mem[a_idx][b_idx] = recur(mem, stats, a_idx, b_idx+1, N);
		}
		
		return mem[a_idx][b_idx];
	}
	
	public static int[][] init_mat(int N, int val) {
		int[][] ret = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ret[i][j] = val;
		
		return ret;
	}
}
