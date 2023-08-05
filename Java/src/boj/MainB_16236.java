package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_16236 {
	static int[][] dir = new int[][] {
		{-1,0}, {0,-1}, {0,1}, {1,0}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int[] shark = new int[4];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark[0] = i;
					shark[1] = j;
					shark[2] = 2;
					shark[3] = 0;
					map[i][j] = 0;
				}
			}
		}
		int ans = solve(map, shark);
		System.out.println(ans);
		br.close();
		
	}
	
	static int solve(int[][] map, int[] shark) {
		int ret = 0;
		int[] dst = new int[2];
		
		while((dst = search(map, shark[0], shark[1], shark[2]))[0] != -1) {
			kill(map, dst[0], dst[1], shark);
			ret += dst[2]-1;
		}
		return ret;
	}
	
	static int[] search(int[][] map, int sr, int sc, int sw) {
		int[] ret = new int[] {-1,-1, 0};
		int[][] check = new int[map.length][map[0].length];
		Queue<int[]> q = new LinkedList<int[]>();
		int dist = Integer.MAX_VALUE;
		
		q.add(new int[] {sr, sc});
		check[sr][sc] = 1;
		
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int y = now[0];		int x = now[1];
			
			for(int d=0; d<4; d++) {
				int ny = y + dir[d][0];
				int nx = x + dir[d][1];
				if(check[y][x]+1 > dist) break;
				if(ny<0 || ny>=map.length || nx<0 || nx>=map[0].length || check[ny][nx] != 0) 
					continue;
				if(map[ny][nx] > sw) continue;
				if(map[ny][nx] != 0 && map[ny][nx] < sw) {
					check[ny][nx] = check[y][x] + 1;
					dist = check[ny][nx];
					if(ret[0] == -1) ret = new int[] {ny, nx, check[ny][nx]};
					else if(ret[0] > ny) ret = new int[] {ny, nx, check[ny][nx]};
					else if(ret[0] == ny)
						if(ret[1] > nx) ret = new int[] {ny, nx, check[ny][nx]};
					continue;
				}
				q.add(new int[] {ny, nx});
				check[ny][nx] = check[y][x] + 1;
			}
		}
		return ret;
	}
	
	static void kill(int[][] map, int i, int j, int[] shark) {
		shark[0] = i;
		shark[1] = j;
		if(++shark[3] == shark[2]) {
			shark[2]++;
			shark[3] = 0;
		}
		map[i][j] = 0;
	}
}
