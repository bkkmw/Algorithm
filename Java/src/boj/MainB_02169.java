package boj;

import java.io.*;
import java.util.*;

public class MainB_02169 {

	// D R L
	public static int[][] dir_yx = new int[][] {{+1,0}, {0,+1}, {0,-1}};
	public static int INF = 100*1000*1000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_02169.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int ans = solve(map);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] map) {
		int N = map.length, M = map[0].length, ret = 0;
		int[][][] mem = init_cube(N, M, 3, -INF);
		
		// init first row
		for(int k=0; k<3; k++) mem[0][0][k] = map[0][0];
		for(int j=1; j<M; j++) 
			mem[0][j][1] = mem[0][j-1][1] + map[0][j];
		
		// start from second row
		for(int i=1; i<N; i++) {
			for(int d=0; d<3; d++) {
				if(d != 2) {
					for(int j=0; j<M; j++) {
						int pi = i-dir_yx[d][0], pj = j-dir_yx[d][1];
						
						if(pi<0 || pi>N-1 || pj<0 || pj>M-1) continue;
						
						int max = find_max(mem[pi][pj], d);
						mem[i][j][d] = max+map[i][j];
					}					
				}
				else {
					for(int j=M-1; j>-1; j--) {
						int pi = i-dir_yx[d][0], pj = j-dir_yx[d][1];
						
						if(pi<0 || pi>N-1 || pj<0 || pj>M-1) continue;
						
						int max = find_max(mem[pi][pj], d);
						mem[i][j][d] = max+map[i][j];
					}
				}
			}
		}
		return find_max(mem[N-1][M-1], 0);
	}
	
	public static int find_max(int[] mem, int d) {
		if(d==0) {
			return Math.max(mem[0], Math.max(mem[1], mem[2]));
		}
		else {
			return Math.max(mem[0], d==1? mem[1] : mem[2]);
		}
	}
	
	public static int[][][] init_cube(int N, int M, int D, int val) {
		int[][][] ret = new int[N][M][D];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<D; k++) {
					ret[i][j][k] = val;
				}
			}
		}
		return ret;
	}
}
