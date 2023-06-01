package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainB_01194 {
	
	static int[][] dir_yx = new int[][] {
		{-1,0},{0,+1},{+1,0},{0,-1}
	};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_01194.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		int[] door = new int[6];
		int[] src = new int[2];
		
		for(int i=0; i<6; i++) door[i] = 1;
		
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				char temp = line.charAt(j);
				map[i][j] = temp;
				if(temp>64 && temp<71) door[temp-65] = 0;
				if(temp == 48) {
					src[0] = i; src[1] = j;
				}
			}
		}
		
		int mask = 0;
		for(int i=0; i<6; i++) mask = mask | (door[i] << i);
		int ans = solve(map, src, mask);
		System.out.println(ans);
		
	}
	
	static int solve(char[][] map, int[] src, int mask) {
		int ret = Integer.MAX_VALUE;
		int N = map.length; int M = map[0].length;
		Queue<int[]> q = new LinkedList<int[]>();
		int[][][] check = new int[N][M][64];
		
		init_check(check, N, M, 64, -1);
		
		q.add(new int[] {src[0], src[1], 0, mask});
		check[src[0]][src[1]][mask] = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0]; int x = cur[1];
			int cnt = cur[2]; int c_mask = cur[3];
			
			for(int d=0; d<4; d++) {
				int ny = y + dir_yx[d][0];
				int nx = x + dir_yx[d][1];
				int ncnt = cnt + 1;
				if(ny<0 || ny>=N || nx<0 || nx>=M) continue;
				if(map[ny][nx] == '#') continue;
				if(check[ny][nx][c_mask] != -1 && check[ny][nx][c_mask] <= ncnt) continue;
				
				if(map[ny][nx] == '1' && ncnt < ret) ret = ncnt;
				else if(map[ny][nx] > 64 && map[ny][nx] < 71 ) {
					// check key
					int door = map[ny][nx] - 65;
					if(((c_mask >> door)&1) == 1) {
						q.add(new int[] {ny,nx, ncnt, c_mask});
						check[ny][nx][c_mask] = ncnt;
					}
				}
				else if(map[ny][nx] > 96 && map[ny][nx] < 103) {
					// add key
					int n_mask = c_mask | (1 << (map[ny][nx]-97));
					q.add(new int[] {ny, nx, ncnt, n_mask});
					check[ny][nx][n_mask] = ncnt;
				}
				else {	// blank or starting point					
					q.add(new int[] {ny, nx, ncnt, c_mask});
					check[ny][nx][c_mask] = ncnt;
				}
			}
		}
		return (ret==Integer.MAX_VALUE) ? -1 : ret;
	}
	
	static void init_check(int[][][] check, int H, int W, int D, int val) {
		for(int i=0; i<H; i++) 
			for(int j=0; j<W; j++)
				for(int k=0; k<D; k++)
					check[i][j][k] = val;
	}
}
