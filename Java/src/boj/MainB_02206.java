package boj;

import java.io.*;
import java.util.*;

public class MainB_02206 {
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	public static int MAX_DIST = 1_000_001;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = line.charAt(j) == '0' ? true : false;
			}
		}
		
		int ans = solve(map);
		System.out.println(ans == MAX_DIST ? -1 : ans);
		br.close();
	}
	
	public static int solve(boolean[][] map) {
		int N = map.length, M = map[0].length;
		
		Queue<int[]> q = new LinkedList<int[]>();
		int[][][] check = init_cube(N, M, 2, MAX_DIST);
		
		q.add(new int[] {0, 0, 1, 0});
		check[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = poll[0] + dir_yx[d][0];
				int nj = poll[1] + dir_yx[d][1];
				int dist = poll[2] + 1;
				
				if(ni<0 || ni>N-1 || nj<0 || nj>M-1) continue;
				if(map[ni][nj] == false && poll[3] == 0) {
					if(check[ni][nj][1] > dist) {
						q.add(new int[] {ni, nj, dist, 1});
						check[ni][nj][1] = dist;
					}
					continue;
				}
				
				if(map[ni][nj]) {
					if(check[ni][nj][poll[3]] > dist) {
						q.add(new int[] {ni, nj, dist, poll[3]});
						check[ni][nj][poll[3]] = dist;
					}
				}
			}
			
		}
				
		return Math.min(check[N-1][M-1][0], check[N-1][M-1][1]);
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
