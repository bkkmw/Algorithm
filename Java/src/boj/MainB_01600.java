package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_01600 {
	static int[][] hdir_yx = new int[][] {
		{-2,+1}, {-1,+2}, {+1,+2}, {+2,+1}, {+2,-1}, {+1,-2}, {-1,-2}, {-2,-1}
	};
	static int[][] mdir_yx = new int[][] {
		{-1,0}, {0,+1}, {+1,0}, {0,-1}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01600.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[H][W];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(K, map);
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	static int solve(int K, int[][] map) {
		if(map.length == 1 && map[0].length == 1) return 0;
		int ret = -1;
		int H = map.length;
		int W = map[0].length;
		int[][][] check = new int[H][W][K+1];
		Queue<int[]> que = new LinkedList<int[]>();
		que.add(new int[] {0, 0, 0, 0});
		// ypos, xpos, count, Kcount
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int ny, nx;
			if(cur[3] < K) {
				for(int d=0; d<8; d++) {
					ny = cur[0] + hdir_yx[d][0];
					nx = cur[1] + hdir_yx[d][1];
					updateQ(que, map, check, H, W, ny, nx, cur[2]+1, cur[3]+1);
				}
			}
			for(int d=0; d<4; d++) {
				ny = cur[0] + mdir_yx[d][0];
				nx = cur[1] + mdir_yx[d][1];
				updateQ(que, map, check, H, W, ny, nx, cur[2]+1, cur[3]);
			}
		}
		return get_min(check[H-1][W-1]);
	}
	
	static void updateQ(Queue<int[]> que, int[][] map, int[][][] check, int H, int W, int ny, int nx, int ncnt, int kcnt) {
		if(ny<0 || ny>H-1 || nx<0 || nx>W-1) return;
		if(map[ny][nx] == 1) return;
		if(check[ny][nx][kcnt] <= ncnt && check[ny][nx][kcnt] != 0) return;
		updateK(check[ny][nx], kcnt, ncnt);
		que.add(new int[] {ny, nx, ncnt, kcnt});
	}
	
	static void updateK(int[] arr, int kcnt, int ncnt) {
		for(int i=kcnt; i<arr.length; i++) {
			if(ncnt < arr[i] || arr[i] == 0) arr[i] = ncnt;
		}
	}
	
	static int get_min(int[] arr) {
		int ret = Integer.MAX_VALUE;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != 0) {
				ret = (ret < arr[i])? ret : arr[i];
			}
		}
		return (ret == Integer.MAX_VALUE)? -1 : ret;
	}
}
