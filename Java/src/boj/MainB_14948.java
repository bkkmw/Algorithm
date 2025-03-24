package boj;

import java.io.*;
import java.util.*;

public class MainB_14948 {
	
	static int INF_LEVEL = 1_000_000_001;
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_14948.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(map);
		
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(int[][] map) {
		int ret = 0;
		int N = map.length, M = map[0].length;
		int[][][] check = init_vol(N, M, 2, INF_LEVEL);
		Queue<int[]> q = new LinkedList<int[]>();
		
		check[0][0][0] = map[0][0];
		check[0][0][1] = map[0][0];
		
		// i, j, level, used
		q.add(new int[] {0, 0, map[0][0], 0});
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				for(int k=1; k<=2; k++) {
					if(poll[3] == 1 && k == 2) continue;
					
					int ni = poll[0] + k*dir_yx[d][0];
					int nj = poll[1] + k*dir_yx[d][1];
					
					if(ni<0 || ni>N-1 || nj<0 || nj>M-1) continue;
					int required_level = Math.max(map[ni][nj], poll[2]);
					
//					System.out.printf("%d %d : %d -> %d %d : %d / %d\n", poll[0], poll[1], required_level, ni, nj, check[ni][nj][0], check[ni][nj][1]);
					
					if(k==1 && check[ni][nj][poll[3]] > required_level) {
						check[ni][nj][poll[3]] = required_level;
						if(poll[3] == 0 && check[ni][nj][1] > required_level) {
							check[ni][nj][1] = required_level;
						}
						q.add(new int[] {ni, nj, required_level, poll[3]});
					} else if(k==2 && check[ni][nj][1] > required_level) {
						check[ni][nj][1] = required_level;
						q.add(new int[] {ni, nj, required_level, 1});
					}
				}
			}
		}
		
		return Math.min(check[N-1][M-1][0], check[N-1][M-1][1]);
	}
	
	public static int[][][] init_vol(int N, int M, int D, int val) {
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
