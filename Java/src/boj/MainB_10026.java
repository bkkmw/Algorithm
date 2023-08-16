package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class MainB_10026 {
	static int[][] dir = new int[][] {
		{-1,0}, {0,1}, {1,0}, {0,-1}
	};
	static boolean[][] gr_map = new boolean[][] {
		{true, false, false}, {false, true, true}, {false, true, true} 
	};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_10026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<N; j++) {
				if(line.charAt(j) == 'B') map[i][j] = 0;
				else if(line.charAt(j) == 'G') map[i][j] = 1;
				else if(line.charAt(j) == 'R') map[i][j] = 2;
			}
		}
		
		int[] ans = solve(map, N);
		sb.append(ans[0]).append(" ").append(ans[1]);
		System.out.println(sb);
		br.close();
	}
	
	static int[] solve(int[][] map, int N) {
		int[] ret = new int[2];
		
		ret[0] = cnt_bound(map, N, false);
		ret[1] = cnt_bound(map, N, true);
		return ret;
	}
	
	static int cnt_bound(int[][] map, int N, boolean merge) {
		int ret = 0;
		int[][] check = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(check[i][j] == 0) {
					check(map, check, i, j, merge);
					ret++;
				}
			}
		}
		return ret;
	}
	
	static void check(int[][] map, int[][] check, int i, int j, boolean merge) {
		int N = map.length;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {i,j});
		check[i][j] = 1;
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int y = now[0];		int x = now[1];
			for(int d=0; d<4; d++) {
				int ny = y + dir[d][0];
				int nx = x + dir[d][1];
				
				if(ny<0 || ny>=N || nx<0 || nx>=N || check[ny][nx] == 1) 
					continue;
				if(!in_range(map[ny][nx], map[y][x], merge))
					continue;
				q.add(new int[] {ny, nx});
				check[ny][nx] = 1;
			}
		}
	}
	
	static boolean in_range(int src, int dst, boolean merge) {
		if(merge) return gr_map[src][dst];
		return (src == dst);
	}
	
}
