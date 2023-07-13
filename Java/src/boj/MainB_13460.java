package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_13460 {

	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N-2][M-2];
		int[][] ball_pos = new int[2][2];
		
		br.readLine();
		for(int i=0; i<N-2; i++) {
			String line = br.readLine();
			for(int j=0; j<M-2; j++) {
				char block = line.charAt(j+1);
				if(block == 'R') {
					ball_pos[0] = new int[] {i, j};
					map[i][j] = '.';
				} else if(block == 'B') {
					ball_pos[1] = new int[] {i, j};
					map[i][j] = '.';
				} else {
					map[i][j] = block;
				}
			}
		}
		
		int ans = solve(map, ball_pos);
		System.out.println(ans);
	}
	
	public static int solve(char[][] map, int[][] ball_pos) {
		int ret = -1;
		
		Queue<int[]> q = new LinkedList<int[]>();
		// count, red_y, red_x, blue_y, blue_x, prev_dir
		int[] info = new int[] {
			0, ball_pos[0][0], ball_pos[0][1], ball_pos[1][0], ball_pos[1][1], -1	
		};
		q.add(info);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] >= 10) break;
			
			for(int d=0; d<4; d++) {
				if(cur[5] > 0 && ((cur[5]+2) % 4 == d)) continue;
				int[] next = next_state(map, cur, d);
				
				if(next[0] == 1) 
					return cur[0]+1;
				if(next[0] == -1) continue;
				
				q.add(new int[] {cur[0]+1, next[1], next[2], next[3], next[4], d });
				
			}
		}
		
		return ret;
	}
	
	/** 
	 * 
	 * @return status, red_y, red_x, blue_y, blue_x
	 */
	public static int[] next_state(char[][] map, int[] state, int n_dir) {
		
		int[] blue = proceed_ball(map, state[3], state[4], n_dir);
		if(blue[0] == -1) return new int[] {-1};
		
		int[] red = proceed_ball(map, state[1], state[2], n_dir);
		if(red[0] == -1) return new int[] {1};
		
		if(red[0] == 0 && blue[0] == 0) return new int[] {-1};
		
		if(red[1] == blue[1] && red[2] == blue[2]) {
			if(is_red_closer(red[1], red[2], state[1], state[2], state[3], state[4])) {
				blue[1] -= dir_yx[n_dir][0];
				blue[2] -= dir_yx[n_dir][1];
			} else {
				red[1] -= dir_yx[n_dir][0];
				red[2] -= dir_yx[n_dir][1];
			}
		}
		
		return new int[] {0, red[1], red[2], blue[1], blue[2]};
	}
	
	// status, ypos, xpos
	public static int[] proceed_ball(char[][] map, int cy, int cx, int dir) {
		// -1(hole), 0(not moved), +1(moved)
		int status = 0;
		int N = map.length;
		int M = map[0].length;
		
		while(true) {
			int ny = cy + dir_yx[dir][0];
			int nx = cx + dir_yx[dir][1];
			
			if(ny < 0 || ny > N-1 || nx < 0 || nx > M-1) break;
			
			if(map[ny][nx] == '#') {
				break;
			} else if(map[ny][nx] == 'O') {
				status = -1;
				break;
			}
			
			status = 1;
			cy = ny;
			cx = nx;
		}
		
		return new int[] {status, cy, cx};
	}
	
	public static boolean is_red_closer(int src_y, int src_x, int red_y, int red_x, int blue_y, int blue_x) {
		int red_dist = Math.abs(src_y - red_y) + Math.abs(src_x - red_x);
		int blue_dist = Math.abs(src_y - blue_y) + Math.abs(src_x - blue_x);
		
		return red_dist < blue_dist;
	}
}
