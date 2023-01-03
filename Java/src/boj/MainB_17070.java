package boj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainB_17070 {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/boj/input_17070.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long ans = solve(map);
		sb.append(ans);
		System.out.println(sb.toString());
	}
	
	static int[][] dir_yx = new int[][] {
		{0,-1}, {-1,0}, {-1,-1}
	};
	
	static boolean[][] movable = new boolean[][] {
		{true, false, true},
		{false, true, true},
		{true, true, true}
	};
	
	static long solve(int[][] map) {
		int N = map.length;
		long[][][] ret = new long[N][N][3];
		if(map[N-1][N-1] == 1) return 0;
		if(map[N-1][N-2] == 0) ret[N-1][N-2][0] = 1;
		if(map[N-2][N-1] == 0) ret[N-2][N-1][1] = 1;
		if(map[N-2][N-2] == 0 && map[N-1][N-2] == 0 && map[N-2][N-1] == 0)
			ret[N-2][N-2][2] = 1;
		
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>=0; j--) {
				for(int mv=0; mv<3; mv++) {
					if(ret[i][j][mv] == 0) continue;
					for(int nmv=0; nmv<3; nmv++) {
						if(!movable[mv][nmv]) continue; 
						int ny = i + dir_yx[nmv][0];
						int nx = j + dir_yx[nmv][1];
						if(check(map,i,j,nmv)) { 
							ret[ny][nx][nmv] += ret[i][j][mv];
						}
					}
				}
			}
		}
		return ret[0][1][0] + ret[0][1][2];
	}
	
	static int[][][] check_mat = new int[][][] {
		{{0, 0}, {0, -1}},
		{{0, 0}, {-1, 0}},
		{{0, 0}, {0, -1}, {-1, 0}, {-1, -1}}
	};
	
	static boolean check(int[][] map, int y, int x, int mv) {
		boolean ret = true;
		int N = map.length;
		for(int i=0; i<check_mat[mv].length; i++) {
			int ny = y + check_mat[mv][i][0];
			int nx = x + check_mat[mv][i][1];
			if(ny<0 || ny>N-1 || nx<0 || nx>N-1) return false;
			if(map[ny][nx] == 1) return false;
		}
		return ret;
	}
}
