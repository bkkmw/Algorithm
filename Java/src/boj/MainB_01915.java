package boj;

import java.io.*;
import java.util.*;

public class MainB_01915 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01915.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] mat = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				mat[i][j] = (line.charAt(j) - 48);
			}
		}
		
		int ans = solve(mat);
		System.out.println(ans);
	}
	
	public static int solve(int[][] mat) {
		int N = mat.length, M = mat[0].length, ret = 1;
		int[][] mem = new int[N+1][M+1];
		
		count_ones(mat, mem);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				int cnt = count_width(N, M, i, j, mem, ret);
				ret = ret<cnt? cnt : ret;
			}
		}
		
		return (ret-1)*(ret-1);
	}
	
	public static int count_width(int N, int M, int i, int j, int[][]mem, int max) {
		int ret = max;
		while(true) {
			if(i+ret > N || j+ret > M) break;
			int tl = mem[i][j];
			int tr = mem[i+ret][j];
			int bl = mem[i][j+ret];
			int br = mem[i+ret][j+ret];
			
			if(tl-tr-bl+br == (ret)*(ret)) ret++;
			else break;
		}
		return ret;
	}
	
	public static void count_ones(int[][] mat, int[][] mem) {
		int N = mat.length, M = mat[0].length;
		
		for(int i=N-1; i>=0; i--) {
			int row_cnt = 0;
			for(int j=M-1; j>=0; j--) {
				row_cnt += mat[i][j]; 
				mem[i][j] = row_cnt;
				mem[i][j] += mem[i+1][j];
			}
		}
	}

}
