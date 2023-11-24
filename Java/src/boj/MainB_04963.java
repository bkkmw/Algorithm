package boj;

import java.io.*;
import java.util.*;

public class MainB_04963 {
	
	public static int[][] dir_yx = new int[][] {
		{-1,0}, {-1,+1}, {0,+1}, {+1,+1}, {+1,0}, {+1,-1}, {0,-1}, {-1,-1}
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_04963.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			if(W == 0) break;
			int[][] map = new int[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int ans = solve(map);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int solve(int[][] map) {
		int ret = 0, H = map.length, W = map[0].length;
		
		boolean[][] checked = new boolean[H][W];
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(checked[i][j] || map[i][j] == 0) continue;
				
				checked[i][j] = true;
				q.add(new int[] {i, j});
				
				bfs(map, q, H, W, checked);
				ret++;
			}
		}
		
		return ret;
	}
	
	public static void bfs(int[][] map, Queue<int[]> q, int H, int W, boolean[][] checked) {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<8; d++) {
				int ni = cur[0] + dir_yx[d][0], nj = cur[1] + dir_yx[d][1];
				if(ni<0 || ni > H-1 || nj<0 || nj > W-1) continue;
				if(checked[ni][nj] || map[ni][nj] == 0) continue;
				
				q.add(new int[] {ni, nj});
				checked[ni][nj] = true;
			}
		}
	}
}
