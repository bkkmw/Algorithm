package boj;

import java.io.*;
import java.util.*;

public class MainB_01577 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_01577.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		int[][] disconnected = new int[K][4];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j=0; j<4; j++) 
				disconnected[i][j] = Integer.parseInt(st.nextToken());
		}
		
		long ans = solve(N, M, disconnected);
		System.out.println(ans);
		br.close();
	}
	
	public static long solve(int N, int M, int[][] disconnected) {
		long ret = 0;
		long[][] mem = new long[M+1][N+1];
		boolean[][][] valid = init_tensor(M+1, N+1, 2, true);
		int K = disconnected.length;
		
		for(int i=0; i<K; i++) {
			update_valid_road(valid, disconnected[i][0], disconnected[i][1], disconnected[i][2], disconnected[i][3]);
		}
		
		
		mem[0][0] = 1;
		
		for(int i=0; i<M+1; i++) {
			for(int j=0; j<N+1; j++) {
				
				if(j>0 && valid[i][j][0])
					mem[i][j] += mem[i][j-1];
				
				if(i>0 && valid[i][j][1])
					mem[i][j] += mem[i-1][j];
				
//				System.out.printf("%d %d : %d\n", i, j, mem[i][j]);
			}
		}
		
		return mem[M][N];
	}
	
	public static void update_valid_road(boolean[][][] valid, int a, int b, int c, int d) {
		if(a != c) {
			// left
			valid[b][Math.max(a, c)][0] = false;
		} else if(b != d) {
			// down
			valid[Math.max(b, d)][a][1] = false;
		}
	}
	
	public static boolean[][][] init_tensor(int M, int N, int D, boolean val) {
		boolean[][][] ret = new boolean[M][N][D];
		
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				for(int k=0; k<D; k++)
					ret[i][j][k] = val;
		return ret;
	}
}
