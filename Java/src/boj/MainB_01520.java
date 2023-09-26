package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_01520 {
	
	public static int[][] dir_yx = new int[][] {
		{-1, 0}, {0, +1}, {+1, 0}, {0, -1}
	};
	 

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/boj/input_01520.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = solve(map, M, N);
		System.out.println(ans);
	}
	
	public static int solve(int[][] map, int M, int N) {
		int[][] check = new int[M][N];
		boolean[][] path = new boolean[M][N];
		
		check[M-1][N-1] = 1;
		recur(map, check, path, M, N, 0, 0);
		
		return check[0][0];
	}
	
	public static boolean recur(int[][] map, int[][] check, boolean[][] path, int M, int N, int i, int j) {
		
		if(i == M-1 && j == N-1) {
			return true;
		}
		
		
		path[i][j] = true;
		for(int d=0; d<4; d++) {
			int ny = i + dir_yx[d][0];
			int nx = j + dir_yx[d][1];

			if(ny < 0 || ny > M-1 || nx <0 || nx > N-1)
				continue;
			
			if(map[ny][nx] >= map[i][j])
				continue;
			
			if(check[ny][nx] > 0)
				check[i][j] += check[ny][nx];
			else if(check[ny][nx] < 0)
				continue;
			else {
				if(recur(map, check, path, M, N, ny, nx))
					check[i][j] += check[ny][nx];
				else
					check[ny][nx] = -1;
			}
		}
		path[i][j] = false;
		
		return check[i][j] <= 0 ? false : true;
		
	}
	
	
}
