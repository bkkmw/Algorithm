package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861 {
	static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	static int[][] distance = null;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/swea/input_1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			distance = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] ans = solve(map,N);
			sb.append("#").append(tc).append(" ");
			sb.append(ans[0]).append(" ");
			sb.append(ans[1]).append("\n");
			
		}
		System.out.println(sb.toString());
	}
	
	static int[] solve(int[][] map, int N) {
		int[] ret = new int[2];
		int max = 0;
		int key = 1001;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int temp = travel__(map, i, j, N, 1);
				if(temp > max) {
					max = temp; 	key = map[i][j];
				}
				else if(temp == max && map[i][j] < key) {
					key = map[i][j];
				}
			}
		}
		ret[0] = key;	ret[1] = max;		
		return ret;
	}
	
	static int travel(int[][]map, int y, int x, int N, int cnt) {
		int ret = 0;
		for(int d=0; d<4; d++) {
			int ny = y + dir_yx[d][0];
			int nx = x + dir_yx[d][1];
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
			if(map[ny][nx] == map[y][x] + 1) {
				int temp = travel(map, ny, nx, N, cnt+1);
				if(temp > ret) ret = temp;
			}
			else if(cnt > ret) ret = cnt;
		}
		return ret;
	}
	
	static int travel_(int[][] map, int y, int x, int N, int cnt) {
		for(int d=0; d<4; d++) {
			int ny = y + dir_yx[d][0];
			int nx = x + dir_yx[d][1];
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
			if(map[ny][nx] == map[y][x] + 1) {
				return travel_(map,ny,nx,N,cnt+1);
			}
		}
		return cnt;
	}
	
	static int travel__(int[][] map, int y, int x, int N, int cnt) {
		if(distance[y][x] != 0) return distance[y][x]+1;
		for(int d=0; d<4; d++) {
			int ny = y + dir_yx[d][0];
			int nx = x + dir_yx[d][1];
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
			if(map[ny][nx] == map[y][x] + 1) {
				int ret = travel__(map,ny,nx,N,cnt+1);
				distance[y][x] = ret - cnt + 1;
				return ret;
			}
		}
		return cnt;
	}
}
