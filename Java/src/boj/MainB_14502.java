package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_14502 {
	static int[][] dir_yx = new int[][] {
		{-1,0},{0,+1},{+1,0},{0,-1}
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_14502.txt"));
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
	}
	
	static int solve(int[][] map) {
		int ret = 0;
		int N = map.length; int M = map[0].length;
		for(int i=0; i<N*M; i++) {
			if(map[i/M][i%M] != 0) continue;
			for(int j=i+1; j<N*M; j++) {
				if(map[j/M][j%M] != 0) continue;
				for(int k=j+1; k<N*M; k++) {
					if(map[k/M][k%M] != 0) continue;
					int temp = count(map, i, j, k);
					if(temp > ret) ret = temp;
				}
			}
		}
		return ret;
	}
	
	static int count(int[][] map, int i, int j, int k) {
		int ret = 0;
		int N = map.length; int M = map[0].length;
		int[][] temp = copy_map(map, N, M);
		temp[i/M][i%M] = 1;
		temp[j/M][j%M] = 1;
		temp[k/M][k%M] = 1;
		
		spread(temp, N, M);
		
		for(int y=0; y<N; y++) 
			for(int x=0; x<M; x++) 
				if(temp[y][x] == 0) ret++;
		
		return ret;
	}

	static void spread(int[][] map, int N, int M) {
		boolean[][] visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2 && visited[i][j] == false) {
					spread_virus(map, visited, i, j);
				}
			}
		}
	}
	
	static void spread_virus(int[][] map, boolean[][] visited, int i, int j) {
		Queue<int[]> q = new LinkedList<int[]>();
		int N = map.length; int M = map[0].length;
		q.add(new int[] {i, j});
		visited[i][j] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0]; int x = cur[1];
			map[y][x] = 2;
			for(int d=0; d<4; d++) {
				int ny = y + dir_yx[d][0];
				int nx = x + dir_yx[d][1];
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(map[ny][nx] != 0) continue;
				if(visited[ny][nx]) continue;
				q.add(new int[] {ny, nx});
				visited[ny][nx] = true;
			}
		}
	}
	
	static int[][] copy_map(int[][] map, int N, int M) {
		int[][] ret = new int[N][M];
		for(int i=0; i<N; i++) 
			for(int j=0; j<M; j++)
				ret[i][j] = map[i][j];
		
		return ret;
	}
}
