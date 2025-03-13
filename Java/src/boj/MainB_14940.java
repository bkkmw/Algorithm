package boj;

import java.io.*;
import java.util.*;

public class MainB_14940 {
	
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0, +1}, {+1, 0}, {0, -1}
	};
	
	static int INIT_VAL = -2;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_14940.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int[] dst_pos = new int[2];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) {
					dst_pos[0] = i;
					dst_pos[1] = j;
				}
			}
		}
		
		int[][] ans = solve(map, dst_pos);
		
		print_ans(ans);
		br.close();
	}
	
	public static int[][] solve(int[][] map, int[] dst_pos) {
		int N = map.length, M = map[0].length;
		int[][] ret = init_map(N, M, INIT_VAL);
		
		Queue<int[]> q = new LinkedList<int[]>();
		
		ret[dst_pos[0]][dst_pos[1]] = 0;
		q.add(new int[] {dst_pos[0], dst_pos[1], 0});
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				int ni = poll[0] + dir_yx[d][0];
				int nj = poll[1] + dir_yx[d][1];
				
				if(ni<0 || ni>N-1 || nj<0 || nj>M-1 || map[ni][nj]==0) continue;
				if(ret[ni][nj] != INIT_VAL && ret[ni][nj] <= poll[2]+1) continue;
				
				ret[ni][nj] = poll[2] + 1;
				q.add(new int[] {ni, nj, poll[2]+1});
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(ret[i][j] == INIT_VAL) {
					if(map[i][j] == 1)
						ret[i][j] = -1;
					else if(map[i][j] == 0)
						ret[i][j] = 0;
				}
				
			}
		}
		
		return ret;
	}
	
	public static void print_ans(int[][] ans) {
		StringBuilder sb = new StringBuilder();
		int N = ans.length, M = ans[0].length;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	public static int[][] init_map(int N, int M, int val) {
		int[][] ret = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				ret[i][j] = val;
			}
		}
		
		return ret;
	}
}
