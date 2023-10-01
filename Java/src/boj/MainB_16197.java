package boj;

import java.util.*;
import java.io.*;

public class MainB_16197 {
	
	public static int[][] dir_yx = new int[][] { {-1,0}, {0,+1}, {+1,0}, {0,-1} };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16197.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		int[][] balls = new int[2][2];
		int b_idx = 0;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				char status = line.charAt(j);
				if(status == 'o') {
					balls[b_idx][0] = i;
					balls[b_idx++][1] = j;
				}
				map[i][j] = status == '#' ? false : true;
			}
		}
		
		int ans = solve(map, balls);
		System.out.println(ans>10? -1 : ans);
	}
	
	public static int solve(boolean[][] map, int[][] balls) {
		int ret = -1;
		int N = map.length, M = map[0].length;
		// a_pos, b_pos, {prev. dir, count}
		Queue<int[][]> q = new LinkedList<int[][]>();
		int[][] next_pos = new int[2][2];
		
		q.add(new int[][] { balls[0], balls[1], {-1, 0}});
		
		while(!q.isEmpty()) {
			int[][] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				// should not exclude direction
//				if(poll[2][0] != -1 && (poll[2][0]+2) % 4 == d)
//					continue;
				
				int fell = 0;
				int moved = 0;
				for(int i=0; i<2; i++) {
					int ny = poll[i][0] + dir_yx[d][0];
					int nx = poll[i][1] + dir_yx[d][1];
					
					if(ny<0 || ny>N-1 || nx<0 || nx>M-1) {
						fell ++;
						continue;
					}
					
					if(!map[ny][nx]) {
						next_pos[i][0] = poll[i][0];
						next_pos[i][1] = poll[i][1];
					}
					else {
						moved++;
						next_pos[i][0] = ny;
						next_pos[i][1] = nx;
					}
				}
				if(fell > 1) continue;
				else if(fell == 1)
					return poll[2][1]+1;
				else if(next_pos[0][0] == next_pos[1][0] && next_pos[0][1] == next_pos[1][1]) continue;
				else if(moved > 0 && poll[2][1] < 9){
					q.add(new int[][] {
						{next_pos[0][0], next_pos[0][1]},
						{next_pos[1][0], next_pos[1][1]},
						{d, poll[2][1]+1}});
				}
			}
		}
		
		
		return ret;
	}
	
}
