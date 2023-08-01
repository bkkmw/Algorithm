package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014 {
	
	static int[][] dir_yx = new int[][] {
		{0,+1}, {+1,0}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) 
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solve(map, N, X);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static int solve(int[][] map, int N, int X) {
		int ret = 0;
		
		for(int r=0; r<N; r++) 
			ret += check_line(map, N, X, r, 0);
		
		for(int c=0; c<N; c++) 
			ret += check_line(map, N, X, c, 1);
		
		return ret;
	}
	
	static int check_line(int[][] map, int N, int X, int idx, int dir) {
		int ret = 1;
		int i, j;
		if(dir == 0) {
			i = idx; j = 0;
		}
		else {
			i = 0; j = idx;
		}
		
		int height = map[i][j];
		int consec = 1;
		
		i += dir_yx[dir][0];
		j += dir_yx[dir][1];
		
		while(i < N && j < N) {
			int diff = map[i][j] - height;
			if(Math.abs(diff) > 1) return 0;
			else if(diff == 0) consec ++;
			else if(diff == 1) {
				if(consec < X) return 0;
				else {
					height = map[i][j];
					consec = 1;
				}
			}
			else if(diff == -1) {
				int next = next_consec(map, N, i, j, dir);
				if(next < X) return 0;
				else {
					int[] temp = to_next_pos(i, j, dir, next);
					i = temp[0]; j = temp[1];
					height = map[i][j];
					consec = next - X;
				}
			}
			i += dir_yx[dir][0];
			j += dir_yx[dir][1];
		}
		return ret;
	}
	
	static int next_consec(int[][] map, int N, int i, int j, int dir) {
		int ret = 1;
		int height = map[i][j];
		i += dir_yx[dir][0];
		j += dir_yx[dir][1];
		while(i < N && j < N) {
			int diff = map[i][j] - height;
			if(diff != 0) break;
			ret ++;
			i += dir_yx[dir][0];
			j += dir_yx[dir][1];
		}
		return ret;
	}
	
	static int[] to_next_pos(int i, int j, int dir, int consec) {
		int[] ret = new int[] {i, j};
		for(int c=0; c<consec-1; c++) {
			ret[0] += dir_yx[dir][0];
			ret[1] += dir_yx[dir][1];
		}
		return ret;
	}
}
