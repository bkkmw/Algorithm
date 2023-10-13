package boj;

import java.util.*;
import java.io.*;

public class MainB_16973 {
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_16973.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1? true : false;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int sr = Integer.parseInt(st.nextToken())-1;
		int sc = Integer.parseInt(st.nextToken())-1;
		int fr = Integer.parseInt(st.nextToken())-1;
		int fc = Integer.parseInt(st.nextToken())-1;
		
		int ans = solve(map, H, W, sr, sc, fr, fc);
		System.out.println(ans);
		br.close();
	}
	
	public static int solve(boolean[][] map, int H, int W, int sr, int sc, int fr, int fc) {
		int N = map.length, M = map[0].length;
		int ret = -1;
		
		int[][] visited = init_map(N, M, -1);
		Queue<int[]> q = new LinkedList<int[]>();
		
		q.add(new int[] {sr, sc, 0});
		visited[sr][sc] = 0;
		
		while(!q.isEmpty()) {
			int[] poll = q.poll();
			
			for(int d=0; d<4; d++) {
				int nr = poll[0]+dir_yx[d][0], nc = poll[1]+dir_yx[d][1], ncnt = poll[2]+1;
				
				if(nr<0 || nr+H>N || nc<0 || nc+W>M) continue;
				if(visited[nr][nc] != -1 && visited[nr][nc] <= ncnt) continue;
				if(check_wall(map, nr, nc, H, W)) continue;
				
				if(nr == fr && nc == fc)
					return ncnt;
				q.add(new int[] {nr, nc, ncnt});
				visited[nr][nc] = ncnt;
			}
		}
		
		return ret;
	}
	
	public static boolean check_wall(boolean[][] map, int r, int c, int H, int W) {
		boolean ret = false;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(map[r+i][c+j])
					return true;
			}
		}
		
		return ret;
	}
	
	public static int[][] init_map(int N, int M, int val) {
		int[][] ret = new int[N][M];
		for(int i=0; i<N; i++) 
			for(int j=0; j<M; j++)
				ret[i][j] = val;
		
		return ret;
	}
}
