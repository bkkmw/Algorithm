package boj;

import java.io.*;
import java.util.*;

public class MainB_01489_Primitive {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_01489.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] stats = new int[2][N];
		
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
	
	public static int solve(int[][] stats) {
		int ret = 0;
		int N = stats[0].length;
		int[][] mem = init_mat(N, -1);
				
		Arrays.sort(stats[0]);
		Arrays.sort(stats[1]);

		ret = recur(mem, stats, N-1, N-1);
		
		
		return ret;
	}
	
	public static int recur(int[][] mem, int[][] stats, int a_idx, int b_idx) {
		if(a_idx == -1 || b_idx == -1) {
			return 0;
		}
		
		if(mem[a_idx][b_idx] >= 0) return mem[a_idx][b_idx];
		
		if(stats[0][a_idx] > stats[1][b_idx]) {
			mem[a_idx][b_idx] = recur(mem, stats, a_idx-1, b_idx-1) + 2;
		} else if(stats[0][a_idx] == stats[1][b_idx]) {
			int tie = recur(mem, stats, a_idx-1, b_idx-1) + 1;
			int skip = recur(mem, stats, a_idx, b_idx-1);
			
			mem[a_idx][b_idx] = Math.max(tie, skip);
		} else {
			mem[a_idx][b_idx] = recur(mem, stats, a_idx, b_idx-1);
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
