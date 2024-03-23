package boj;

import java.io.*;
import java.util.*;

public class MainB_02615 {
	public static final int WIDTH = 19;
	public static final int HEIGHT = 19;
	public static final int[][] dir_yx = new int[][] {
		{0,+1}, {+1,0}, {+1, +1}, {+1, -1}
	};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input/boj/input_02615.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[][] map = new int[HEIGHT][WIDTH];
		for(int i=0; i<HEIGHT; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<WIDTH; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] ans = solve(map);
		sb.append(ans[0]);
		if(ans[0] != 0) {
			sb.append("\n").append(ans[1]).append(" ").append(ans[2]);
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	public static int[] solve(int[][] map) {
		// R, D, DR, DL
		int[][][] cnt = new int[HEIGHT][WIDTH][4];
		
		for(int i=HEIGHT-1; i>=0; i--) {
			for(int j=WIDTH-1; j>=0; j--) {
				for(int d=0; d<4; d++) {
					if(map[i][j] == 0) break;
					
					int ni = i+dir_yx[d][0], nj = j+dir_yx[d][1];
					if(ni<0 || ni>=HEIGHT || nj<0 || nj>=WIDTH) {
						cnt[i][j][d] = 1;
						continue;
					}
					
					if(map[ni][nj] == map[i][j]) {
						cnt[i][j][d] = cnt[ni][nj][d] + 1;
						if(cnt[i][j][d] == 5) {
							int pi = i-dir_yx[d][0], pj = j-dir_yx[d][1];
							
							if(pi<0 || pi>=HEIGHT || pj<0 || pj>=WIDTH || map[pi][pj] != map[i][j]) {
								if(d==3)
									return new int[] {map[i][j], i+4+1, j-4+1};
								else 
									return new int[] {map[i][j], i+1, j+1};
							}
						}
					} else {
						cnt[i][j][d] = 1;
					}
				}
			}
		}
		
		return new int[] {0};
	}
}
