package boj;

import java.io.*;
import java.util.*;

public class MainB_16137 {
	
	public static int[][] dir_yx = new int[][] { {-1,0}, {0,+1}, {+1,0}, {0,-1} };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("input/boj/input_16137.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(map, M);
		
		System.out.println(ans);
		
		br.close();
	}
	
	public static int solve(int[][] map, int M) {
		int N = map.length;
		int ret = 100;
		
		Queue<int[]> q = new LinkedList<int[]>();
		int[][][] visited = init_vol(N, N, 2, 100);
		
		// i, j, cnt, used
		visited[0][0][0] = 0;
		visited[0][0][1] = 0;
		q.add(new int[] {0, 0, 0, 0});
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			if(poll[0] + poll[1] == 2*N-2) {
				ret = Math.min(ret, poll[2]);
			}
			
			for(int d=0; d<4; d++) {
				int ni = poll[0] + dir_yx[d][0];
				int nj = poll[1] + dir_yx[d][1];
				
				if(ni<0 || ni >N-1 || nj<0 || nj>N-1) continue;
				
				if(map[ni][nj] == 1 && visited[ni][nj][poll[3]] > poll[2]+1) {
					visited[ni][nj][poll[3]] = poll[2]+1;
					q.add(new int[] {ni, nj, poll[2]+1, poll[3]});
				}
				
				// 오작교는 이처럼 매우 불안정하므로, 견우는 안전을 위해 두 번 연속으로 오작교를 건너지는 않기로 했다.
				if(map[poll[0]][poll[1]] != 1) continue;
				
				if(map[ni][nj] == 0) {
					if(poll[3] == 1) continue;
					int next_activated = find_next_activated(poll[2]+1, M);
					
					if(visited[ni][nj][1] > next_activated) {
						visited[ni][nj][1] = next_activated;
						q.add(new int[] {ni, nj, next_activated, 1});
					}
				}
				
				if(map[ni][nj] > 1) {
					int next_activated = find_next_activated(poll[2]+1, map[ni][nj]);
					
					if(visited[ni][nj][poll[3]] > next_activated) {
						visited[ni][nj][poll[3]] = next_activated;
						q.add(new int[] {ni, nj, next_activated, poll[3]});
					}
				}
			}
		}
		
		return ret;
	}
	
	public static int find_next_activated(int cur, int period) {
		if(cur % period == 0) return cur;
		return (cur/period + 1) * period;
	}
	
	public static int[][][] init_vol(int W, int H, int D, int val) {
		int[][][] ret = new int[W][H][D];
		
		for(int i=0; i<W; i++)
			for(int j=0; j<H; j++)
				for(int k=0; k<D; k++)
					ret[i][j][k] = val;
		
		return ret;
	}
}
